package com.jin.wod.practice.kotlin_in_action.chapter_5

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class NullableTest {

    @Test
    fun elvisTest() {
        var text: String? = "Hello World"
        var foo: String = text ?: ""
        assertThat(foo).isEqualTo(text)

        text = null
        foo = text ?: ""
        assertThat(foo).isEqualTo("");
    }

    @Test
    fun elvisThrowTest() {
        val emptyEnergyCar: Car = Car(null)
        assertThatThrownBy { emptyEnergyCar.move() }
                .isInstanceOf(IllegalAccessException::class.java)

        val car: Car = Car(10)
        assertThat(car.move()).isEqualTo(10)
    }

    @Test
    fun nullSafetyLet() {
        val car = Car(null)
        car.addPrice(10)
        assertThat(car.price).isEqualTo(20)

        car.addPrice(null)
        car.addPrice(null)
        assertThat(car.price).isEqualTo(20)
    }
}