package com.chardon.faceval.client.web.controller

import com.chardon.faceval.client.web.service.AuthService
import com.chardon.faceval.client.web.service.UserService
import com.chardon.faceval.client.web.util.UserNameValidationChecker
import com.chardon.faceval.entity.UserInfo
import com.chardon.faceval.entity.UserInfoUpload
import feign.FeignException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception

@RestController
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var authService: AuthService

    @Autowired
    private lateinit var checker: UserNameValidationChecker

    @PostMapping("/login")
    fun login(@RequestParam("username") userName: String,
              @RequestParam("password") password: String): ResponseEntity<UserInfo> {

        var result: Map<String, String>? = null

        try {
            result = authService.authenticate(userName, password)

            if (result["status"] != "OK") {
                return ResponseEntity(null, HttpStatus.FORBIDDEN)
            }
        } catch (e: FeignException.Unauthorized) {
            return ResponseEntity(null, HttpStatus.UNAUTHORIZED)
        } catch (e: FeignException.Forbidden) {
            return ResponseEntity(null, HttpStatus.FORBIDDEN)
        }

        val userInfo = userService.getUser(userName)

        return ResponseEntity(userInfo, HttpStatus.OK)
    }

//    @PostMapping("/logout")
//    fun logout(@RequestParam("username") userName: String): Boolean {
//        return true
//    }

    @GetMapping("/check/{username}")
    fun userNameCheck(@PathVariable("username") userName: String): String {
        if (!checker.check(userName)) {
            return "INVALID"
        }

        try {
            val user = userService.getUser(userName)

            if (user.id == userName) {
                return "EXISTS"
            }
        } catch (e: Exception) {
        }

        return "OK"
    }

    @GetMapping("/user/{username}")
    fun getUser(@PathVariable("username") userName: String): ResponseEntity<UserInfo> {
        if (userNameCheck(userName) != "OK") {
            return ResponseEntity(null, HttpStatus.FORBIDDEN)
        }

        return ResponseEntity(userService.getUser(userName), HttpStatus.OK)
    }

    @PostMapping("/user")
    fun createUser(@RequestBody newUser: UserInfoUpload): ResponseEntity<UserInfo> {
        return ResponseEntity(userService.createUser(newUser), HttpStatus.CREATED)
    }

    @PutMapping("/user")
    fun updateUser(@RequestBody updatedUser: UserInfoUpload): UserInfo {
        return userService.updateUser(updatedUser)
    }

    @DeleteMapping("/user")
    fun deleteUser(@RequestParam("username") userName: String,
                   @RequestParam("password") password: String): Map<String, String> {
        return userService.removeUser(userName, password)
    }

    @PatchMapping("/user")
    fun updatePassword(@RequestParam("username") userName: String,
                       @RequestParam("password") oldPassword: String,
                       @RequestParam("new_password") newPassword: String): Map<String, String> {
        return userService.updatePassword(userName, oldPassword, newPassword)
    }
}