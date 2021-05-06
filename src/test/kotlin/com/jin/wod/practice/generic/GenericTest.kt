package com.jin.wod.practice.generic

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GenericTest {

    @Test
    fun testGeneric() {
        val stackString: GenericStack<String> = GenericStack("1","2","3")
        assertThat(stackString.pop()).isEqualTo("1")
        assertThat(stackString.peek()).isEqualTo("2")
        // stackString.push(3) -> type miss matching
        stackString.push("4")
    }
}