package com.chardon.faceval.client.web.service

import com.chardon.faceval.client.web.client.MainServiceClient
import com.chardon.faceval.entity.AuthInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class AuthService {

    @Autowired
    private lateinit var client: MainServiceClient

    fun authenticate(userName: String, password: String): Map<String, String> {
        return client.authenticate(AuthInfo(userName, password))
    }
}