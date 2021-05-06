package com.jin.wod.practice.spclass.dataclass

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DataClassTest {
    var person = Person("John")

    @Test
    fun test() {
        assertThat(person.name).isEqualTo("John")
        assertThat(person.age).isEqualTo(0)
        print(person.toString())
    }

    @Test
    fun testCopy() {
        val copiedPerson = person.copy();
        assertThat(person).isEqualTo(copiedPerson)
        assertThat(person == copiedPerson).isTrue()
        assertThat(person === copiedPerson).isFalse()
        copiedPerson.age = 10
        assertThat(person).isNotEqualTo(copiedPerson)
        assertThat(person.name).isEqualTo(copiedPerson.name)
        assertThat(person.age).isNotEqualTo(copiedPerson.age)
    }

    @Test
    fun testDestructing() {
        person.age = 10
        val (name, age) = person
        assertThat(name).isEqualTo("John")
        assertThat(age).isEqualTo(10)
    }
}