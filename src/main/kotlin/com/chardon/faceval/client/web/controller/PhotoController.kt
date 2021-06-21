package com.chardon.faceval.client.web.controller

import com.chardon.faceval.client.web.service.PhotoService
import com.chardon.faceval.entity.PhotoInfo
import com.chardon.faceval.entity.PhotoInfoUpdate
import com.chardon.faceval.entity.PhotoInfoUploadBase64
import feign.FeignException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/photo")
class PhotoController {

    @Autowired
    private lateinit var photoService: PhotoService

    @GetMapping("/")
    fun getPhoto(@RequestParam("photo_id") photoId: Long?,
                 @RequestParam("user_id") userId: String?,
                 @RequestParam("attach_base") attachBase: Boolean = false): ResponseEntity<List<PhotoInfo>> {
        return try {
            val photos = photoService.getPhoto(photoId, userId, attachBase)
            ResponseEntity(photos, HttpStatus.OK)
        } catch (e: FeignException.NotFound) {
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/")
    fun createPhoto(
        newPhoto: PhotoInfoUploadBase64,
        @RequestParam("attach_base") attachBase: Boolean = false
    ): PhotoInfo {
        return photoService.addPhoto(newPhoto, attachBase)
    }

    @PutMapping("/")
    fun updatePhoto(updatedPhoto: PhotoInfoUpdate,
                    @RequestParam("attach_base") attachBase: Boolean = false): PhotoInfo {
        return photoService.updatePhoto(updatedPhoto, attachBase)
    }

    @DeleteMapping("/")
    fun deletePhoto(@RequestParam("id") userName: String,
                    @RequestParam("password") password: String,
                    @RequestParam("photo_id") photoId: Long): Map<String, String> {
        return photoService.removePhoto(userName, password, photoId)
    }

//    private fun PhotoInfo.stripBase(): PhotoInfo {
//        return this.let {
//            PhotoInfo(
//                base = "",
//                dateAdded = it.dateAdded,
//                description = it.description,
//                id = it.id,
//                positions = it.positions,
//                score = it.score,
//                tag = it.tag,
//                title = it.title,
//            )
//        }
//    }
}