package com.chardon.faceval.client.web.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient("FV_SERVICE")
@Service
@RequestMapping("/auth")
interface AuthService {

    @PostMapping("/")
    fun authenticate(@RequestParam("id") userName: String,
                     @RequestParam("password") password: String): Map<String, String>
}