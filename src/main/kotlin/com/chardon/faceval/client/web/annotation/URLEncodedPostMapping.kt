package com.chardon.faceval.client.web.annotation

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * \@PostMapping with MediaType.APPLICATION_FORM_URLENCODED_VALUE
 *
 * @author chardon
 * @since Webclient 0.2.0
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@RequestMapping(method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
annotation class URLEncodedPostMapping(
    /**
     * Alias for [RequestMapping.value].
     */
    vararg val value: String = [],
    /**
     * Alias for [RequestMapping.name].
     */
    val name: String = "",
    /**
     * Alias for [RequestMapping.path].
     */
    val path: Array<String> = [],
    /**
     * Alias for [RequestMapping.params].
     */
    val params: Array<String> = [],
    /**
     * Alias for [RequestMapping.headers].
     */
    val headers: Array<String> = [],
    /**
     * Alias for [RequestMapping.produces].
     */
    val produces: Array<String> = []
)

