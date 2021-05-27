package com.chardon.faceval.client.web.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service

@FeignClient("FV_SERVICE")
@Service
interface UserService {
}