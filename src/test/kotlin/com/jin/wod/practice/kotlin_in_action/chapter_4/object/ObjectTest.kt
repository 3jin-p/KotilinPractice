package com.jin.wod.practice.kotlin_in_action.chapter_4.`object`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ObjectTest {

    @Test
    fun test() {
        Singleton.scaleUp()
        val instance = Singleton

        assertThat(instance.prop).isEqualTo(1)
    }
}