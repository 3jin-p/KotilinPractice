package com.jin.wod.practice.kotlin_in_action.chapter_4.data_class

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DataClassTest {
    lateinit var addr: Address

    @BeforeEach
    fun setUp() {
        addr = Address("KS013", "SEOUL")
    }

    @Test
    fun dataClassHasEqualsMethod() {
        val seoul = Address("KS013", "SEOUL")
        assertTrue(addr == seoul)
    }

    @Test
    fun dataClassHasHashCodeMethod() {
        val addressSet = setOf<Address>(addr)
        assertTrue(addressSet.contains(Address("KS013", "SEOUL")))
    }

    @Test
    fun dataClassCopyTest() {
        val modifiedAddress = addr.copy(zipCode = "MODIFIED")
        assertTrue(modifiedAddress == Address("MODIFIED", "SEOUL"))
        assertFalse(addr == modifiedAddress)
    }
}