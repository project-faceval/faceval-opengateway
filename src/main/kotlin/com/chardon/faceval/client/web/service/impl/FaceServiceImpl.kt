package com.chardon.faceval.client.web.service.impl

import com.chardon.faceval.client.web.client.FaceDetectionClient
import com.chardon.faceval.client.web.client.FaceScoringClient
import com.chardon.faceval.client.web.service.FaceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class FaceServiceImpl : FaceService {

    @Autowired
    private lateinit var detectionClient: FaceDetectionClient

    @Autowired
    private lateinit var scoringClient: FaceScoringClient

    override fun detect(ext: String, bimg: MultipartFile): Map<String, List<List<Int>>> {
        return detectionClient.detect(ext, bimg)
    }

    override fun scoring(ext: String, bimg: MultipartFile, posSet: String): Map<String, List<Double>> {
        return scoringClient.scoring(ext, bimg, posSet)
    }
}