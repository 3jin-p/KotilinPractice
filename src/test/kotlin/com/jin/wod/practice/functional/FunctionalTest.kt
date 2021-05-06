package com.jin.wod.practice.functional

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FunctionalTest {

    @Test
    fun test() {
        val calculator = Calculator()
        assertThat(calculator.add(1,2)).isEqualTo(3)
        assertThat(calculator.minus(2,1)).isEqualTo(1)
        assertThat(Calculator.calculate(4,2) {a, b -> a / b}).isEqualTo(2)
    }

    @Test
    fun testFunctionReturn() {
        val func = operation()
        assertThat(func("Hello, ")).isEqualTo("Hello, World")
    }

    fun operation(): (String) -> String {
        return ::addWorld
    }

    fun addWorld(x: String) = x + "World"
}