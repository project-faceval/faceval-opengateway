package com.chardon.faceval.client.web.controller

import com.chardon.faceval.client.web.service.FaceDetectionService
import com.chardon.faceval.client.web.service.FaceScoringService
import com.chardon.faceval.client.web.util.PosSetBuilderFactory
import com.chardon.faceval.client.web.util.ScoringProcessor
import com.chardon.faceval.entity.DetectionResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/eval")
class PhotoEvalController {

    @Autowired
    private lateinit var detectionService: FaceDetectionService

    @Autowired
    private lateinit var scoringService: FaceScoringService

    @Autowired
    private lateinit var factory: PosSetBuilderFactory

    @Autowired
    private lateinit var scoringProcessor: ScoringProcessor

    @PostMapping("/detect")
    fun detectOnly(@RequestParam("ext") extension: String,
                   @RequestParam("bimg") image: MultipartFile): DetectionResult {
        return detectionService.detect(extension, image)
    }

    @PostMapping("/scoring")
    fun scoringOnly(@RequestParam("ext") extension: String,
                    @RequestParam("bimg") image: MultipartFile,
                    @RequestBody detectionResult: DetectionResult): List<Double> {
        val builder = factory.getBuilder()
        for (facePos in detectionResult.face) {
            builder.addPos("face", listOf(facePos.startX, facePos.startY, facePos.lengthX, facePos.lengthY))
        }

        for (eyePos in detectionResult.eye) {
            builder.addPos("eye", listOf(eyePos.startX, eyePos.startY, eyePos.lengthX, eyePos.lengthY))
        }

        for (nosePos in detectionResult.nose) {
            builder.addPos("nose", listOf(nosePos.startX, nosePos.startY, nosePos.lengthX, nosePos.lengthY))
        }

        for (mouthPos in detectionResult.mouth) {
            builder.addPos("mouth", listOf(mouthPos.startX, mouthPos.startY, mouthPos.lengthX, mouthPos.lengthY))
        }

        return scoringProcessor.getScore(scoringService.scoring(extension, image, builder.toString()))
    }

    @PostMapping("/")
    fun eval(@RequestParam("ext") extension: String,
             @RequestParam("bimg") image: MultipartFile): List<Double> {
        val detectionResult = detectionService.detect(extension, image)
        return scoringOnly(extension, image, detectionResult)
    }
}