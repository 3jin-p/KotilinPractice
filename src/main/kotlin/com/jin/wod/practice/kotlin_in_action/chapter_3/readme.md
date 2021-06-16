Function
--
3장은 코틀린의 함수를 다룬다.

### 기본 값
  
**Kotlin은 메서드가 기본 값을 지닐 수 있다.**  
``` kotlin
public fun <T> Iterable<T>.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "",
   limit: Int = -1, truncated: CharSequence = "...", transform: ((T) -> CharSequence)? = null): String {
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
### 최상위 함수
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


### 확장 함수
1번 예제에서 살펴본 Collection.joinToString() 메서드를 생각해보자.  
Kotlin 은 어떻게 java의 기본 라이브러리를 사용하면서 더 강력하고 확장된 기능을 제공할 수 있을까?  
확장 함수는 함수를 확장 받는 수신객체와 함수의 구현으로 이루어져 있다.
   ``` kotlin
      package com.jin.wod.practice.kotlin_in_action.chapter_3.extendfun
   
      fun String.prefixFirstChar(prefix: String): String =  prefix + get(0)
   ```
함수 명이 매끄럽지 않지만, 예제니 무시하고 넘어가도록 하고...  
위 함수는 String 의 첫 글자를 가져와서 입력한 prefix 를 붙이는 함수다.

실행은 다음과 같다.
   ``` kotlin
      import com.jin.wod.practice.kotlin_in_action.chapter_3.extendfun.prefixFirstChar
   
       @Test
       fun testExtendFunction() {
           // 확장함수를 받는 객체를 수신객체라고 한다. 해당 객체의 사용자는 메서드가 확장함수인지 아닌지 알 필요가 전혀 없다.
           assertThat("한치두치세치네치".prefixFirstChar("prefix_")).isEqualTo("prefix_한")
       }
   ```
import 만 받으면 마치 String 객체의 함수처럼 사용할 수 있다.  
코틀린은 확장함수를 통해서 콜렉션과 같은 기존의 Java Api 에 추가적인 기능을 제공한다.  
  

### varag  
varag는 가변인자 즉 Java의 ... 파라미터에 해당한다. 인자 개수를 유동적으로 호출 시 지정할 수 있다.    
Java 와의 차이점은, Asterisk 와 배열을 파라미터로 넘기면 배열의 모든 인자를 풀어서 전달하는 기능이 있다.  
   ``` kotlin
    @Test
    fun testAsteriskParamVarag(){
        // 자바의 ... 에 해당하는 varag 는 *Array 표현으로 배열의 모든 원소를 풀어줄 수 있다
        assertThat(
            appendAll(*list.toTypedArray())).isEqualTo("노가리한치땅콩먹태")

    }
   ```
추가적으로, varag 파라미터가 제너릭 타입일 경우, Array<out T> 타입은 Asterisk 문자 없이 전달할 수 있다.  
  

### 중위 함수  
중위 함수는 변수들 가운데에서 호출되는 함수를 뜻하며, infix 지정자를 통해서 구현할 수 있다.
   ``` kotlin
   infix fun Int.plus(other: Int): Int = toInt() + other
    
   @Test
    fun testInfixFun() {
        // 단일 인자 함수의 경우 infix 키워드로 선언을 하면 아래와 같이 중위 호출을 할 수 있다.
        assertThat(1 plus 2).isEqualTo(3)
    }
   ```
infix 함수는 반드시 단일 인자 함수어야만 한다.  
     

### 로컬 함수  
Kotlin 은 함수안에서 또 함수를 만들 수 있다.
   ``` kotlin
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
   ```
   로컬 함수를 이용하면 반복되는 코드를 많이 줄일 수 있다.  
   자세한 예제는 Kotlin in Action 3Chapter 에 잘 나와 있다.
   
