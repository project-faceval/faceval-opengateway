package com.chardon.faceval.client.web.service

import com.chardon.faceval.client.web.client.MainServiceClient
import com.chardon.faceval.entity.UserInfo
import com.chardon.faceval.entity.UserInfoUpload
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@Service
class UserService {

    @Autowired
    private lateinit var client: MainServiceClient

    fun getUser(userName: String): UserInfo {
        return client.getUser(userName)
    }

    fun createUser(newUser: UserInfoUpload): UserInfo {
        return client.createUser(newUser)
    }

    fun updateUser(updatedUserInfo: UserInfoUpload): UserInfo {
        return client.updateUser(updatedUserInfo)
    }

    fun removeUser(userName: String, password: String): Map<String, String> {
        return client.removeUser(userName, password)
    }

    fun updatePassword(userName: String, oldPassword: String, newPassword: String): Map<String, String> {
        return client.updatePassword(userName, oldPassword, newPassword)
    }
}