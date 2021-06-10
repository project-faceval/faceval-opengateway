package com.chardon.faceval.client.web.service

import com.chardon.faceval.entity.ScoringResult
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@FeignClient("fv-ml-service")
interface FaceScoringService {

    @PostMapping("/")
    fun scoring(@RequestParam("ext") extension: String,
                @RequestParam("bimg") image: MultipartFile,
                @RequestParam("posSet") posSet: String): ScoringResult
}