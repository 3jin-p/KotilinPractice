package com.jin.wod.practice.reference

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BasicGrammerTest {

    @Test
    fun testStringTemplate() {
        var a = 1
        val template = "a is $a"
        assertThat(template).isEqualTo("a is 1");

        a = 2
        val template2 = "${template.replace("is","was")}, but now is $a"
        assertThat(template2).isEqualTo("a was 1, but now is 2");
    }

    fun largerOf(a:Int, b:Int): Int = if (a > b) a else b

    @Test
    fun testSimpleConditionalExpression() {
        assertThat(largerOf(1,2)).isEqualTo(2);
    }

    @Test
    fun listLambdas() {
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        assertThat(fruits.filter{it.startsWith("a")}).isEqualTo(listOf("avocado","apple"))
        assertThat(fruits.filter { it.startsWith("a")}
            .map { it.toUpperCase() })
        .isEqualTo(listOf("AVOCADO", "APPLE"))
    }

}