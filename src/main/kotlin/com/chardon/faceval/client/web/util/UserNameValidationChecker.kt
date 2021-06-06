package com.chardon.faceval.client.web.util

import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class UserNameValidationChecker {

    private val pattern = Pattern.compile("^[^*]{5,30}$").toRegex()

    fun check(userName: String): Boolean {
        return pattern.matches(userName)
    }
}