package com.jin.wod.practice.reference.spclass.objectclass

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ObjectClassTest {

    @Test
    fun test() {
        makeJuice("오렌지주스", 10, 30)
    }

    fun makeJuice(name: String, sugar: Int, water: Int) {
        val juiceDetail = object {
            var juiceName = name
            var sugarPercentage = sugar / (sugar + water)
            var waterPercentage = water / (sugar + water)
        }

        val totalSize = sugar + water
        print("create new juice - $totalSize")
    }

    @Test
    fun companionTest() {
        assertThat(add(1, 2)).isEqualTo(3)
        assertThat(minus(2, 1)).isEqualTo(1)
    }

    // similar with Java static method
    companion object Caculator {
        fun add(a: Int, b: Int): Int {
            return a + b
        }

        fun minus(a: Int, b: Int): Int {
            return a - b
        }
    }
}