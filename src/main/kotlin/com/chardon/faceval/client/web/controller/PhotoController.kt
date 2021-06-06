package com.chardon.faceval.client.web.controller

import com.chardon.faceval.client.web.service.PhotoService
import com.chardon.faceval.entity.PhotoInfo
import com.chardon.faceval.entity.PhotoInfoUpdate
import com.chardon.faceval.entity.PhotoInfoUpload
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController("/photo")
class PhotoController {

    @Autowired
    private lateinit var photoService: PhotoService

    @GetMapping("/{photo_id}/{user_id}")
    fun getPhoto(@PathVariable("photo_id") photoId: String,
                 @PathVariable("user_id") userId: String): PhotoInfo {
        val photoIdCleaned = if (photoId == "*") null else photoId
        val userIdCleaned = if (userId == "*") null else userId

        return photoService.getPhoto(photoIdCleaned, userIdCleaned)
    }

    @PostMapping("/")
    fun createPhoto(@RequestBody newPhoto: PhotoInfoUpload<MultipartFile>): PhotoInfo {
        return photoService.addPhoto(newPhoto)
    }

    @PutMapping("/")
    fun updatePhoto(@RequestBody updatedPhoto: PhotoInfoUpdate): PhotoInfo {
        return photoService.updatePhoto(updatedPhoto)
    }

    @DeleteMapping("/")
    fun deletePhoto(@RequestParam("id") userName: String,
                    @RequestParam("password") password: String,
                    @RequestParam("photo_id") photoId: String): Map<String, String> {
        return photoService.removePhoto(userName, password, photoId)
    }
}