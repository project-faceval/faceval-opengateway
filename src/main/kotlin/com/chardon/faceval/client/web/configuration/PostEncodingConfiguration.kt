package com.chardon.faceval.client.web.configuration

import feign.form.FormEncoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Scope

@Configuration
class PostEncodingConfiguration {

    @Autowired
    private lateinit var converters: ObjectFactory<HttpMessageConverters>

    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Primary
    @Bean
    fun feignFormEncoder() = FormEncoder(SpringEncoder(converters))
}