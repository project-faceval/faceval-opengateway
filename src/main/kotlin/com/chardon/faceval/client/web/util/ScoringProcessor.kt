package com.chardon.faceval.client.web.util

import com.chardon.faceval.entity.ScoringResult
import org.springframework.stereotype.Component
import java.util.*

@Component
class ScoringProcessor {

    private val scale = 1.2

    private val baseScore = 6.0

    protected fun scoringSingleFace(face: List<Double>,
                                    eyes: Pair<List<Double>, List<Double>>,
                                    nose: List<Double>,
                                    mouth: List<Double>): Double {
        // Face part
        val faceScore = face[1] - face[0]

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

    fun getScore(scoringResult: ScoringResult): List<Double> {
        val faceCount = scoringResult.face.count()

        val scores = LinkedList<Double>()

        for (offset in 0 until faceCount) {
            scores.add(
                scoringSingleFace(
                    scoringResult.face[offset],
                    Pair(scoringResult.eye[offset * 2], scoringResult.eye[offset * 2 + 1]),
                    scoringResult.nose[offset],
                    scoringResult.mouth[offset]
                )
            )
        }

        return scores
    }
}