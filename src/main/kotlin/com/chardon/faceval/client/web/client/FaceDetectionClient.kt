package com.chardon.faceval.client.web.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.multipart.MultipartFile

@FeignClient("FV_CV_SERVICE")
interface FaceDetectionClient {

    @PostMapping("/")
    fun detect(ext: String, bimg: MultipartFile): Map<String, List<List<Int>>>
}