package com.jin.wod.practice.variables

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VariableTest {

    @Test
    fun testVarAndVal() {
        var a: String = "mutable"
        val b: String = "immutable"
        a = "change"
        assertThat(a).isEqualTo("change")
        // b = "chagne" --> ide catch

        var e: Int; // no initialize
        //print(e) // ide catch .. Must be initialize before read in function
    }

    @Test
    fun testNullSafety() {
        var neverNull: String = "cannot be null"
        // neverNull = null -> ide catch
        var nullable: String? = "can be null"
        nullable = null
        testInferredNullable(neverNull)
        // testInferredNullable(nullable) -> cannot
    }

    fun testInferredNullable(notNull: String): Int {
        return notNull.length
    }
}