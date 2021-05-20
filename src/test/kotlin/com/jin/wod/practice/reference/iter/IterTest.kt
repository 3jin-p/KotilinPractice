package com.jin.wod.practice.reference.iter

import org.junit.jupiter.api.Test

class IterTest {

    @Test
    fun test() {
        val zoo = Zoo(listOf(Animal("늑대"), Animal("개"), Animal("소")))
        for (animal in zoo) {
            animal.bark()
        }
    }
}