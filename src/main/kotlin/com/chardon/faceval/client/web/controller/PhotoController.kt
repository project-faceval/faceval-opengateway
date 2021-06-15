package com.chardon.faceval.client.web.controller

import com.chardon.faceval.client.web.service.PhotoService
import com.chardon.faceval.entity.PhotoInfo
import com.chardon.faceval.entity.PhotoInfoUpdate
import com.chardon.faceval.entity.PhotoInfoUpload
import com.chardon.faceval.entity.PhotoInfoUploadBase64
import feign.FeignException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/photo")
class PhotoController {

    @Autowired
    private lateinit var photoService: PhotoService

    @GetMapping("/")
    fun getPhoto(@RequestParam("photo_id") photoId: Long?,
                 @RequestParam("user_id") userId: String?): ResponseEntity<List<PhotoInfo>> {
        return try {
            ResponseEntity(photoService.getPhoto(photoId, userId), HttpStatus.OK)
        } catch (e: FeignException.NotFound) {
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createPhoto(@RequestBody newPhoto: PhotoInfoUploadBase64): PhotoInfo {
        return photoService.addPhoto(newPhoto)
    }

    @PutMapping("")
    fun updatePhoto(@RequestBody updatedPhoto: PhotoInfoUpdate): PhotoInfo {
        return photoService.updatePhoto(updatedPhoto)
    }

    @DeleteMapping("")
    fun deletePhoto(@RequestParam("id") userName: String,
                    @RequestParam("password") password: String,
                    @RequestParam("photo_id") photoId: Long): Map<String, String> {
        return photoService.removePhoto(userName, password, photoId)
    }
}