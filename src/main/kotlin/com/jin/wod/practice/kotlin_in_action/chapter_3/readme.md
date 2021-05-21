Function
--
3장은 코틀린의 함수를 다룬다.

1. Default Value  
Kotlin은 메서드가 기본 값을 지닐 수 있다.  
   ``` kotlin
   public fun <T> Iterable<T>.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((T) -> CharSequence)? = null): String {
    return joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()
   }
   ```  
    위 코드는 Kotlin 의 Collection 에서만 제공하는 메서드다.
    각 인자들은 모두 기본값을 지니고 있어, 아래와 같이 인자가 없이도 필요한 인자만 사용해도 호출이 가능하다. 
    
    ``` kotlin
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
    ```

2. 최상위 함수  
Kotlin 에서는 Class가 아닌 함수나 프로퍼티도 최상위 값이 될 수 있다.  
최상위 함수는 해당 패키지를 Import 하면 사용이 가능하다.  
   ``` kotlin
   package com.jin.wod.practice.kotlin_in_action.chapter_3.superfun
   
   fun add(a: Int, b: Int): Int = a + b
   fun minus(a: Int, b: Int): Int = a - b
   ```
   ``` kotlin
    import com.jin.wod.practice.kotlin_in_action.chapter_3.superfun.add
    import com.jin.wod.practice.kotlin_in_action.chapter_3.superfun.minus
   
    @Test
    fun testSuperFunction() {
        /*
            java 의 static 메서드만 존재하는 util class 들이 주로 여기 해당한다.
            코틀린은 클래스 외에도 함수나 프로퍼티가 최상위에 올 수 있다
         */
        assertThat(add(1, 2)).isEqualTo(3)
        assertThat(minus(2, 1)).isEqualTo(1)
    }
   ```
   위 예제와 같이 사용할 수 있으며, 최상위 함수는 java 의 static 메서드만 존재하는 클래스(Utils) 따위를 완전히 대체할 수 있다.  


3. 확장 함수  
1번 예제에서 살펴본 Collection.joinToString() 메서드를 생각해보자.  
Kotlin 은 어떻게 java의 기본 라이브러리를 사용하면서 더 강력하고 확장된 기능을 제공할 수 있을까?  
확장 함수는 함수를 확장 받는 수신객체와 함수의 구현으로 이루어져 있다. 
