package com.chardon.faceval.client.web.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
interface FaceService {

    fun detect(ext: String, bimg: MultipartFile): Map<String, List<List<Int>>>

    fun scoring(ext: String, bimg: MultipartFile, posSet: String): Map<String, List<Double>>
}