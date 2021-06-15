package com.chardon.faceval.client.web.service

import com.chardon.faceval.entity.DetectionModelBase64
import com.chardon.faceval.entity.DetectionResult
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@FeignClient("fv-cv-service")
interface FaceDetectionService {

    @PostMapping("/")
    fun detect(@RequestBody detection: DetectionModelBase64): DetectionResult
}