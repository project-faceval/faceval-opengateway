package com.chardon.faceval.client.web.service

import com.chardon.faceval.entity.PhotoInfo
import com.chardon.faceval.entity.PhotoInfoUpdate
import com.chardon.faceval.entity.PhotoInfoUpload
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@FeignClient("FV_SERVICE")
@Service
@RequestMapping("/blog")
interface PhotoService {

    @GetMapping("/")
    fun getPhoto(@RequestParam("id") photoId: String?,
                 @RequestParam("user_id") userId: String?): PhotoInfo

    @PostMapping("/")
    fun addPhoto(@RequestBody newPhoto: PhotoInfoUpload<MultipartFile>): PhotoInfo

    @PutMapping("/")
    fun updatePhoto(@RequestBody updatedPhoto: PhotoInfoUpdate): PhotoInfo

    @DeleteMapping("/")
    fun removePhoto(@RequestParam("id") userName: String,
                    @RequestParam("password") password: String,
                    @RequestParam("photo_id") photoId: String,
                    @RequestParam("fake_delete") fakeDelete: Boolean = true): Map<String, String>
}