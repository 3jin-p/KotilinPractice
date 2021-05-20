package com.jin.wod.practice.kotlin_in_action.chapter_3

import com.jin.wod.practice.kotlin_in_action.chapter_3.superfun.add
import com.jin.wod.practice.kotlin_in_action.chapter_3.superfun.minus
import com.jin.wod.practice.kotlin_in_action.chapter_3.extendfun.prefixFirstChar
import com.jin.wod.practice.kotlin_in_action.chapter_3.middle_call.plus
import com.jin.wod.practice.kotlin_in_action.chapter_3.varag.appendAll
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Chapter3LearningTest {

    lateinit var list: List<String>

    @BeforeEach
    fun setUp() {
        list = listOf("노가리", "한치", "땅콩", "먹태")
    }

    @Test
    fun testJoinToString() {
        // 코틀린에서는 함수가 인자가 디폴트값을 지닐 수 있다
        assertThat(list.joinToString(";", "ㅋㅋ", "맛나", 3))
            .isEqualTo("ㅋㅋ노가리;한치;땅콩;...맛나")
    }

    @Test
    fun testSuperFunction() {
        /*
            java 의 static 메서드만 존재하는 util class 들이 주로 여기 해당한다.
            코틀린은 클래스 외에도 함수나 프로퍼티가 최 상위에 올 수 있다
         */
        assertThat(add(1, 2)).isEqualTo(3)
        assertThat(minus(2, 1)).isEqualTo(1)
    }

    @Test
    fun testExtendFunction() {
        // 확장함수를 받는 객체를 수신객체라고 한다. 해당 객체의 사용자는 메서드가 확장함수인지 아닌지 알 필요가 전혀 없다.
        assertThat("한치두치세치네치".prefixFirstChar("prefix_")).isEqualTo("prefix_한")
    }

    @Test
    fun testAsteriskParamVarag(){
        // 자바의 ... 에 해당하는 varag 는 *Array 표현으로 배열의 모든 원소를 풀어줄 수 있다
        assertThat(
            appendAll(*list.toTypedArray())).isEqualTo("노가리한치땅콩먹태")

    }

    @Test
    fun testInfixFun() {
        // 단일 인자 함수의 경우 infix 키워드로 선언을 하면 아래와 같이 중위 호출을 할 수 있다.
        assertThat(1 plus 2).isEqualTo(3)
    }

    //ToDo Kotlin 정규식은 따로 집중적으로 다루어 보기

    class Person(val firstName: String, val lastName: String) {}

    @Test
    fun testLocalFunction() {
        // 코틀린은 함수안에 함수를 생성할 수 있다. 이로인해 중복 코드를 많이 줄일 수 있고
        // 로컬함수는 상위의 인자에 접근할 수 있다.
        // 상위 함수가 확장함수라면 로컬함수는 수신객체를 따로 명시하지 않더라도 수신객체의 프로퍼티에도 접근할 수 있다
        fun showNameIfNotEmpty(person: Person) {
            fun validateNotEmpty(value: String, fieldName: String) {
                if (value.isEmpty()) {
                    println("$fieldName cannot be Empty")
                }
            }
            validateNotEmpty(person.firstName, "firstName")
            validateNotEmpty(person.lastName, "lastName")
        }
    }
}