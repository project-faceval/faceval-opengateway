package com.chardon.faceval.client.web.util

import com.chardon.faceval.entity.ScoringResult
import org.springframework.stereotype.Component
import java.util.*
import kotlin.random.Random

@Component
class ScoringProcessor {

    private val scale = 1.2

    private val baseScore = 6.0

    protected fun scoringSingleFace(face: List<Double>,
                                    eyes: Pair<List<Double>, List<Double>>?,
                                    nose: List<Double>?,
                                    mouth: List<Double>?): Double {
        // Face part
        val faceScore = face[1] - face[0]

        if (eyes == null || nose == null || mouth == null) {
            if (face.isEmpty()) {
                return baseScore + 2 * scale
            }

            val weighted = baseScore + faceScore * scale
            return when {
                weighted < 0 -> 0.0
                weighted > 10 -> 10.0
                else -> weighted
            }
        }

        // Eyes part
        var eyesScore = 0.0


        val eyesList = listOf(eyes.first, eyes.second)
        for (item in eyesList) {
            eyesScore += item[2] + item[1] * 0.5 - item[0] * 0.5 - item[3]
        }

        eyesScore /= 2

        // Nose part
        val noseScore = nose[2] + nose[1] - nose[0] - nose[3]

        // Mouth part
        val mouthScore = mouth[1] + mouth[2] + mouth[3] - mouth[0] - mouth[5] - mouth[4]

        val rawWeight = faceScore * 0.5 + eyesScore * 0.2 + noseScore * 0.1 + mouthScore * 0.1

        val rawScore = baseScore + rawWeight * scale

        return when {
            rawScore < 0 -> 0.0
            rawScore > 10 -> 10.0
            else -> rawScore
        }
    }

    private val seed = Random(129)

    fun getScore(scoringResult: ScoringResult): List<Double> {
        val faceCount = scoringResult.face.count()

        if (faceCount <= 0) {
            // If there is no face at all... random a score (because I don't you where are you!!!)
            return listOf(seed.nextDouble(8.0, 9.8))
        }

        val scores = LinkedList<Double>()

        for (offset in 0 until faceCount) {
            if (scoringResult.eye.size <= offset * 2 + 1 ||
                scoringResult.nose.size <= offset ||
                scoringResult.mouth.size <= offset) {
                scores.add(scoringSingleFace(scoringResult.face[offset], null, null, null))
            }

            try {
                scores.add(
                    scoringSingleFace(
                        scoringResult.face[offset],
                        Pair(scoringResult.eye[offset * 2], scoringResult.eye[offset * 2 + 1]),
                        scoringResult.nose[offset],
                        scoringResult.mouth[offset]
                    )
                )
            } catch (e: Exception) {
                scores.add(scoringSingleFace(scoringResult.face[offset], null, null, null))
            }
        }

        return scores
    }
}