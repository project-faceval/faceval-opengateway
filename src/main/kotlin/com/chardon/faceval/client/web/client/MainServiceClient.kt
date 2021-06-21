package com.chardon.faceval.client.web.client

import com.chardon.faceval.client.web.configuration.PostEncodingConfiguration
import com.chardon.faceval.entity.*
import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@FeignClient("fv-service")
interface MainServiceClient {

    // User
    @GetMapping("/user/")
    fun getUser(@RequestParam("id") userName: String): UserInfo

    @PostMapping("/user/")
    fun createUser(newUser: UserInfoUpload): UserInfo

    @PutMapping("/user/")
    fun updateUser(updatedUserInfo: UserInfoUpload): UserInfo

    @DeleteMapping("/user/")
    fun removeUser(@RequestParam("id") userName: String,
                   @RequestParam("password") password: String): Map<String, String>

    @PatchMapping("/user/")
    fun updatePassword(@RequestParam("id") userName: String,
                       @RequestParam("password") oldPassword: String,
                       @RequestParam("new_password") newPassword: String): Map<String, String>

    // Auth
    @PostMapping("/auth/")
    fun authenticate(authInfo: AuthInfo): Map<String, String>

    // Photo
    @GetMapping("/photo/")
    fun getPhoto(@RequestParam("id") photoId: Long?,
                 @RequestParam("user_id") userId: String?,
                 @RequestParam("attach_base") attachBase: Boolean = false): List<PhotoInfo>

    @PostMapping("/photo/")
    fun addPhoto(newPhoto: PhotoInfoUploadBase64,
                 @RequestParam("attach_base") attachBase: Boolean = false): PhotoInfo

    @PutMapping("/photo/")
    fun updatePhoto(updatedPhoto: PhotoInfoUpdate,
                    @RequestParam("attach_base") attachBase: Boolean = false): PhotoInfo

    @DeleteMapping("/photo/")
    fun removePhoto(@RequestParam("id") userName: String,
                    @RequestParam("password") password: String,
                    @RequestParam("photo_id") photoId: Long,
                    @RequestParam("fake_delete") fakeDelete: Boolean = true): Map<String, String>
}