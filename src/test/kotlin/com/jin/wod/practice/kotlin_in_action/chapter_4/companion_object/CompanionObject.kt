package com.jin.wod.practice.kotlin_in_action.chapter_4.companion_object

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CompanionObjectTest {

    @Test
    fun test() {
        assertThat(CompanionObject.TEST).isEqualTo("test")
        assertThat(CompanionObject.method(1)).isEqualTo(11)
    }
}