package com.chardon.faceval.client.web.service

import com.chardon.faceval.client.web.client.MainServiceClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Service
class AuthService {

    @Autowired
    private lateinit var client: MainServiceClient

    fun authenticate(userName: String, password: String): Map<String, String> {
        return client.authenticate(userName, password)
    }
}