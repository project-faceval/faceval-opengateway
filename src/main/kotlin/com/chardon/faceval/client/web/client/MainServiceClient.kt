package com.chardon.faceval.client.web.client

import com.chardon.faceval.entity.*
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@FeignClient("fv-service")
interface MainServiceClient {

    // User
    @GetMapping("/user/")
    fun getUser(@RequestParam("id") userName: String): UserInfo

    @PostMapping("/user/")
    fun createUser(@RequestBody newUser: UserInfoUpload): UserInfo

    @PutMapping("/user/")
    fun updateUser(@RequestBody updatedUserInfo: UserInfoUpload): UserInfo

    @DeleteMapping("/user/")
    fun removeUser(@RequestParam("id") userName: String,
                   @RequestParam("password") password: String): Map<String, String>

    @PatchMapping("/user/")
    fun updatePassword(@RequestParam("id") userName: String,
                       @RequestParam("password") oldPassword: String,
                       @RequestParam("new_password") newPassword: String): Map<String, String>

    // Auth
    @PostMapping("/auth/")
    fun authenticate(@RequestParam("id") userName: String,
                     @RequestParam("password") password: String): Map<String, String>

    // Photo
    @GetMapping("/blog/")
    fun getPhoto(@RequestParam("id") photoId: Long?,
                 @RequestParam("user_id") userId: String?): List<PhotoInfo>

    @PostMapping("/blog/")
    fun addPhoto(@RequestBody newPhoto: PhotoInfoUpload<MultipartFile>): PhotoInfo

    @PutMapping("/blog/")
    fun updatePhoto(@RequestBody updatedPhoto: PhotoInfoUpdate): PhotoInfo

    @DeleteMapping("/blog/")
    fun removePhoto(@RequestParam("id") userName: String,
                    @RequestParam("password") password: String,
                    @RequestParam("photo_id") photoId: Long,
                    @RequestParam("fake_delete") fakeDelete: Boolean = true): Map<String, String>
}