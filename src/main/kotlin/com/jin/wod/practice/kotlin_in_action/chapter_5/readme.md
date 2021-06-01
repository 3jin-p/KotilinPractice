Kotlin 타입 시스템
--
### 1.Null Safety  
코틀린은 기본적으로 프로퍼티나 변수에 Null 할당을 지원하지 않는다.  
코틀린에서 변수에 null 을 할당하기 위해서는 아래와 같이 선언해야한다.  
``` kotlin
var name: String?
   
fun strLenSafe(s: String? 》 = ...
```  
nullable 변수는 non nullable 변수에 할당할 수 없다.
``` kotlin
val x: String? = null
var y:String = x
   
ERROR: Type mismatch: inferred type is String? but String was expected
```
   
**1-1. 안전한 호출 연산자: ?.**   
 ?.은 null 검사와 메소드 호출을 한 번의 연산으로 수행한다.
``` kotlin
fun printAUCaps(s:String?) {
   val alICaps:String? = s?.toUpperCase() println(allCaps》
}

java 
--> if (s != null) { allCaps = s.toUpperCase() } else { allCaps = null }

fun foo() {
   // 호출 연산자 연쇄
   val country = this.company?.address?.country
}
```

**1-2. 앨비스 연산자: ?:**. 
이항 연산자로 좌항을 계산한 값이 널인지 검사한다.  
좌항 값이 널이 아니 면 좌항 값을 결과로 하고，좌항 값이 널이면 우항 값을 결과로 한다
``` kotlin
fun foo(s: String?) {
   val t: String = s?:""  <- s가 null 이면 "" 아니면 s 
}
```

앨비스 연산자의 항에는 식도 올 수 있다. 
예를들면 throw 나 return 으로 활용할 수 있다.
``` kotlin
fun printAddress() {
   val addr = person.company?.address 
            ?: throw IllegalArgumentException("No address")
   
   with (addr) {
      println(zipCode)
      println(city) 
   }          
}
```
**1-3. 안전한 캐스트: as?**      
as? 연산자는 어떤 값을 지정한 타입으로 캐스트한다.  
as?는 값을 대상 타입으로 변환할 수 없으면 null을 반환한다.
주로 as? 연산자를 사용한 후 앨비스 연산자를 사용하는 패턴으로 이용한다.

**1-4 let 을 이용한 안전한 호출**.   
nullable 변수에 let 함수를 지정하면, 수신객체가 null 이 아닐때에만 전달 받은 람다가 실행된다.
``` kotlin
class Car(var energy: Int?) {
    var price: Int = 10

    fun addPrice(price: Int?) {
        price?.let { price -> this.price += price }
    }
    
    fun move(): Int {
        return energy ?: throw IllegalAccessException("에너지가 없으면 이동할 수 없습니다.")
    }
}
```
``` kotlin
    @Test
    fun nullSafetyLet() {
        val car = Car(null)
        car.addPrice(10)
        assertThat(car.price).isEqualTo(20)

        car.addPrice(null)
        car.addPrice(null) 
        assertThat(car.price).isEqualTo(20)
    }
```
**1-5. 나중에 초기화 할 프로퍼티**  
보통 JUnit 을 사용하면, 테스트 전에 null 이 할당되어 있다가, @Before 를 이용하여 필요한 프로퍼티들을 생성한다.  
그러기 위해서는 nullable 로 값을 선언해야하는데, 이러면 해당 프로퍼티를 사용할때마다 null 단언 !! 를 사용해주어야한다.  
그래서 코틀린은 나중에 초기화하는 프로퍼티 lateinit 이라는 선언을 제공한다.  
lateinit 프로퍼티는 반드시 var 이어야한다.
``` kotlin

    lateinit var list: List<String>

    @BeforeEach
    fun setUp() {
        list = listOf("노가리", "한치", "땅콩", "먹태")
    }
```
  
**1-6. nullable 타입의 확장함수**  
Kotlin에는 String? 의 확장함수 isNullOrBlank() 라는 함수가 있다.  
이 함수는 런타임에서 null.isNullOrBlank() 로 실행이 되어도 예외가 발생하지 않는다.  
Nullable 값에 확장함수를 정의하고, 내부에서 명시적으로 null 체크와 처리를 해주면, 유연하게 확장함수를 사용할 수 있다.
  
### 2. Kotlin Primitive Type  
Kotlin 은 원시타입과 래퍼타입을 구분하지 않는다.
코틀린은 런타임에서 원시타입과 래퍼타입을 가장 효율적이게 지정한다.  
nullable 한 프로퍼티와 제너릭 파라미터는 항상 래퍼 타입으로 지정된다.  

**2-1. 숫자변환**
Kotlin은 Java와 달리 숫자에 대한 자동 변환을 제공하지 않는다. 
``` kotlin
fun foo() {
    var a: Long = 1L
    var b: Int = 1
    val c = a + b --> Compile Error  
}
```
그로인해 코틀린은 모든 숫자 원시타입에 대하여 변환 함수를 제공한다. (toInt(), toLong...) 
코틀린에서 수 변환은 항상 명시적으로 지정해주어야 한다. 

**2-2 Unit 타입**
Unit 타입은 Java 의 Void 와 대응된다.  
거의 모든 기능이 유사하지만, 2가지 차이점이 있다.    
1. void와 달리 Unit을 타입 인자로 쓸 수 있다. 
2. Unit 타입에 속한 값은 단 하나뿐이며 Unit 타입의 함수는 Unit 값을 묵시적 으로 반환한다. 

이 두 특성은 제네릭 파라미터를 반환하는 함수를 오버라이드하면서 반환 타입으로 Unit을 쓸 때 유용하다.
``` kotlin
interface Processor<T> { 
    fun process(): T
}
class NoResultProcessor : Processor<Unit> { 
    override fun process() { <- 무언가를 리턴할 필요가 없다.
    }
}
```

**2-3.Nothing**   
Nothing 역시 void 와 비슷하지만 의미적으로 차이가 있다.  
Nothing 은 반환타입으로만 사용되는, 해당 함수가 정상적으로 종료되지 않는다는 명시적인 표현이다.  
``` kotlin
fun fail(message: String):Nothing { 
    throw IllegalStateException (message)
} <- 명시적으로 정상적인 종료 케이스가 없는 함수라는걸 알 수 있다.

val address = company.address ?: fail ("No address")
```



