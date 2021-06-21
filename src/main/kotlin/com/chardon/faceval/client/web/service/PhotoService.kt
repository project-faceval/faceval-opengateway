package com.chardon.faceval.client.web.service

import com.chardon.faceval.client.web.client.MainServiceClient
import com.chardon.faceval.entity.PhotoInfo
import com.chardon.faceval.entity.PhotoInfoUpdate
import com.chardon.faceval.entity.PhotoInfoUpload
import com.chardon.faceval.entity.PhotoInfoUploadBase64
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Service
class PhotoService {

    @Autowired
    private lateinit var client: MainServiceClient

    fun getPhoto(photoId: Long?, userId: String?, attachBase: Boolean = false): List<PhotoInfo> {
        return client.getPhoto(photoId, userId, attachBase)
    }

    fun addPhoto(newPhoto: PhotoInfoUploadBase64, attachBase: Boolean = false): PhotoInfo {
        return client.addPhoto(newPhoto, attachBase)
    }

    fun updatePhoto(updatedPhoto: PhotoInfoUpdate, attachBase: Boolean = false): PhotoInfo {
        return client.updatePhoto(updatedPhoto, attachBase)
    }

    fun removePhoto(userName: String, password: String,
                    photoId: Long, fakeDelete: Boolean = true): Map<String, String> {
        return client.removePhoto(userName, password, photoId, fakeDelete)
    }
}