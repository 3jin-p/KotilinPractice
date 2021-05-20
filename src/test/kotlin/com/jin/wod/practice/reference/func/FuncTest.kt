package com.jin.wod.practice.reference.func

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FuncTest {

    infix fun Int.times(str:String) = str.repeat(this)
    infix fun String.onto(other: String) = Pair(this, other)

    @Test
    fun testInfixFunc() {
        assertThat(2 times "Hi").isEqualTo("HiHi")
        assertThat( "Hello" onto "World").isEqualTo("(Hello,World)")
    }

    fun appendAll(vararg messages: String):String {
        var result = ""
        for (message in messages) {
            result += message;
        }
        return result
    }

    @Test
    fun testVarage() {
        assertThat(appendAll("1","2","3","4","5","6","7","8","9")).isEqualTo("123456789")
    }
}