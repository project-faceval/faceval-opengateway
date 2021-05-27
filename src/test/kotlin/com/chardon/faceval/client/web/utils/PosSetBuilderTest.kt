package com.chardon.faceval.client.web.utils

import com.chardon.faceval.client.web.util.PosSetBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*


class PosSetBuilderTest {

    private lateinit var input1: MutableMap<String, List<List<Int>>>

    private lateinit var expected1: String

    @BeforeEach
    fun setup() {
        input1 = TreeMap()

        input1["face"] = listOf(
            listOf(100, 300, 500),
            listOf(200, 200, 200)
        )

        input1["nose"] = listOf(
            listOf(150, 330, 500),
            listOf(250, 200, 200)
        )

        expected1 = "100,300,500|200,200,200&150,330,500|250,200,200"
    }

    @Test
    fun convert1() {
        Assertions.assertEquals(expected1, PosSetBuilder.fromMap(input1))
    }
}