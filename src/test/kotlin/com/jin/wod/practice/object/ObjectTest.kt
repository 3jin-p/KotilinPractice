package com.jin.wod.practice.`object`

import com.jin.wod.practice.singleton.Singleton
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ObjectTest {
    @Test
    fun testObjectIsSingleton() {
        var obj = Singleton
        var obj2 = Singleton
        obj2.name = "김치"

        assertThat(obj).isEqualTo(obj2)
        assertThat(obj.name).isEqualTo("김치")
    }
}