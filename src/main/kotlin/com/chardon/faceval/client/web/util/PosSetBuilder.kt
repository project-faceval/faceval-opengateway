package com.chardon.faceval.client.web.util

import org.springframework.stereotype.Component
import java.util.*

@Component
class PosSetBuilder {
    companion object {
        fun fromMap(posMap: Map<String, List<List<Int>>>): String {
            val builder = PosSetBuilder()

            for (key in posMap.keys) {
                builder.addKey(key)

                if (posMap[key] == null) {
                    continue
                }

                for (pos in posMap[key]!!) {
                    builder.addPos(key, pos)
                }
            }

            return builder.toString()
        }
    }

    private val posMap: MutableMap<String, MutableList<List<Int>>> = TreeMap()

    fun addKey(key: String) {
        if (posMap.contains(key)) return

        posMap[key] = LinkedList()
    }

    fun addKeys(keys: Iterable<String>) {
        for (key in keys) {
            this.addKey(key)
        }
    }

    fun addPos(key: String, value: List<Int>) {
        posMap[key]?.add(value)
    }

    override fun toString(): String {
        val sb = StringBuilder()

        for (poses in posMap.values) {
            for (pos in poses) {
                for (coordination in pos) {
                    sb.append(coordination).append(",")
                }
                sb.deleteCharAt(sb.length - 1).append("|")
            }
            sb.deleteCharAt(sb.length - 1).append("&")
        }
        sb.deleteCharAt(sb.length - 1)

        return sb.toString()
    }
}