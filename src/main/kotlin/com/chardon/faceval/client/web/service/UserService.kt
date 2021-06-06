package com.chardon.faceval.client.web.service

import com.chardon.faceval.entity.UserInfo
import com.chardon.faceval.entity.UserInfoUpload
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@FeignClient("FV_SERVICE")
@Service("/user")
interface UserService {

    @GetMapping("/")
    fun getUser(@RequestParam("id") userName: String): UserInfo

    @PostMapping("/")
    fun createUser(@RequestBody newUser: UserInfoUpload): UserInfo

    @PutMapping("/")
    fun updateUser(@RequestBody updatedUserInfo: UserInfoUpload): UserInfo

    @DeleteMapping("/")
    fun removeUser(@RequestParam("id") userName: String,
                   @RequestParam("password") password: String): Map<String, String>

    @PatchMapping("/")
    fun updatePassword(@RequestParam("id") userName: String,
                       @RequestParam("password") oldPassword: String,
                       @RequestParam("new_password") newPassword: String): Map<String, String>
}