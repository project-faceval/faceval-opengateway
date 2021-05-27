package com.chardon.faceval.client.web.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.multipart.MultipartFile

@FeignClient("FV_ML_SERVICE")
interface FaceScoringClient {

    @PostMapping("/")
    fun scoring(ext: String, bimg: MultipartFile, posSet: String): Map<String, List<Double>>
}