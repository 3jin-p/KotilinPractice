Kotlin 타입 시스템
--
1. Null Safety  
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
   
 1-1 안전한 호출 연산자: ?.  
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

1-2 앨비스 연산자: ?:
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

1-3 안전한 캐스트: as?  
as? 연산자는 어떤 값을 지정한 타입으로 캐스트한다.  
as?는 값을 대상 타입으로 변환할 수 없으면 null을 반환한다.
주로 as? 연산자를 사용한 후 앨비스 연산자를 사용하는 패턴으로 이용한다.

1-4 let 을 이용한 안전한 호출  
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
1-5 나중에 초기화 할 프로퍼티
보통 JUnit 을 사용하면, 테스트 전에 null 이 할당되어 있다.  
@Before 를 이용하여 필요한 프로퍼티들을 생성한다.  
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
  
1-6 nullable 타입의 확장함수  
Kotlin에는 String? 의 확장함수 isNullOrBlank() 라는 함수가 있다.  
이 함수는 런타임에서 null.isNullOrBlank() 로 실행이 되어도 예외가 발생하지 않는다.  
Nullable 값에 확장함수를 정의하고, 내부에서 명시적으로 null 체크와 처리를 해주면, 유연하게 확장함수를 사용할 수 있다.
  
