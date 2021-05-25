package com.jin.wod.practice.kotlin_in_action.chapter_4.abstract_property

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AbstractPropertyTest {

    lateinit var user: User

    @Test
    fun testAllocateAbstractFieldDefaultConstructor() {
        user = PrivateUser("김개똥")
        assertThat(user.name).isEqualTo("김개똥")
    }

    @Test
    fun testNoInitianlizeAbstractFieldWithCustomGetter() {
        user = SubscribingUser("김", "개똥")
        assertThat(user.name).isEqualTo("김개똥")
        // 매번 Getter 호출 전 계산해서 값을 보여준다.
    }

    @Test
    fun testCustomSetAbstractField() {
        user = FacebookUser(1)
        assertThat(user.name).isEqualTo("1로 찾아낸 페이스북에 설정된 이름")
        // 계산한 값을 저장해서 가지고 있다가 보여준다.
    }
}