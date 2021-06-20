package com.chardon.faceval.client.web.controller

import com.chardon.faceval.client.web.service.FaceDetectionService
import com.chardon.faceval.client.web.service.FaceScoringService
import com.chardon.faceval.client.web.util.PosSetBuilderFactory
import com.chardon.faceval.client.web.util.ScoringProcessor
import com.chardon.faceval.entity.DetectionModelBase64
import com.chardon.faceval.entity.DetectionResult
import com.chardon.faceval.entity.ScoringModelBase64
import com.chardon.faceval.util.detectionmodelutils.Utils.toPosSet
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
    fun detectOnly(detection: DetectionModelBase64): DetectionResult {
        return detectionService.detect(detection)
    }

    @PostMapping("/scoring")
    fun scoringOnly(scoring: ScoringModelBase64): List<Double> {
        return scoringProcessor.getScore(scoringService.scoring(scoring))
    }

    @PostMapping("/")
    fun eval(detection: DetectionModelBase64): List<Double> {
        return scoringOnly(
            ScoringModelBase64(
                bimg = detection.bimg,
                ext = detection.ext,
                posSet = detectionService.detect(detection).toPosSet()
            )
        )
    }
}