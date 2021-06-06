package com.chardon.faceval.client.web.util

import com.chardon.posset.PosSetBuilder
import org.springframework.stereotype.Component
import java.util.*

@Component
class PosSetBuilderFactory {

    fun getBuilder(): PosSetBuilder {
        val instance = PosSetBuilder()
        instance.addKeys(listOf("face", "eyes", "nose", "mouth"))
        return instance
    }
}