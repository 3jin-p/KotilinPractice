package com.jin.wod.practice.spclass.sealedclass

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SealedClassTest {

    @Test
    fun testSealed() {
        val dog = Animal.Dog("삽살개")
        val cat = Animal.Cat("길냥이")

        dog.grawl()
        cat.grawl()

        assertThat(dog.greet(cat)).isEqualTo("Hi~ Cat, I'm 삽살개")
        assertThat(cat.greet(dog)).isEqualTo("Hi~ Dog, I'm 길냥이")
    }
}