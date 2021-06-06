Lamda
--

**1. Lamda 를 변수에 저장**  
Kotlin 은 Lamda 식을 변수에 저장할 수 있다.

````kotlin
val sum = { x: Int, y: Int -> x + y }

println(sum(1,2))  // 3
````

run() 라는 인자로 받은 람다를 실행해주는 함수가 존재한다.
````kotlin
run { println(10) } // 10
````

**2. Kotlin 의 Lamda 문법**  
````kotlin
1. people.maxBy({p:Person->p.age }) // Original
2. people.maxBy() { p: Person -> p.age } // 맨 뒤 인자가 람다식 이라면, 람다를 괄호 밖으로 뺄 수 있다.
3. people.maxBy { p: Person -> p.age } // 람다가 함수의 유일한 인자일 경우 빈괄호를 생략해도 된다.
4. people.maxBy { p -> p.age } // 컴파일러가 타입을 추론할 수 있으므로 타입을 명시할 필요가 없다.
5. people.maxBy{ it.age } // 람다의 파라미터가 하나뿐이고 그 타입을 컴파일러가 추론할 수 있는 경우 디폴트 이름인 it를 바로 쓸 수 있다.
6. people.maxBy(Person::age) // 프로퍼티나 메소드를 단 하나만 호출할때, 멤버참조를 사용하는 방법
````

**3. Lamda의 변수 접근**  
람다를 함수 안에서 정의하면, 함수의 파라미터 뿐 아니라 람다 앞에서 정의된 변수들을 모두 사용할 수 있다.  
Java 와 가장 큰 차이점은 **final 이 아닌 변수에도** 접근할 수 있다는 점이다.  

````kotlin
fun printErrorCounts (responses: Collection<String>) {
    var clientErrorCount = 0
    var serverErrorCount = 0
    
    responses.forEach {
        if (it.startsWith("4")) {
            clientErrorCount++
        } else if (it.startsWith("5")) {
            serverErrorCount++
        }
    }
    
    printlin("clientError : $clientErrorCount, serverError: $serverErrorCount")
}
````
위 예제에서 람다식 안에서 변경되고 있는 람다 외부 변수 clientErrorCount, serverErrorCount 를 **람다가 포획한 변수** 라고 한다.  
이와 같은일이 가능한 이유는 Kotlin 이 Java 에서 사용가능한 교묘한 속임수를 자동으로 구현해주기 때문이다.  
람다가 외부 변수를 변경하려 하면, 람다는 외부 변수를 래핑하여 해당 래핑 참조를 final 로 만들고 래핑 인스턴스 내부 필드륿 변경한다.  

**4. Collection 함수**  
코틀린의 Collection 들은 자바8의 스트림 함수들과 동일한 기능을 하는 함수들이 구현되어 있다.  
ex ) map, filter, all, any, count, find, groupBy .... 자세한 내용은 따로 찾아보도록 한다.  

**5. flatMap과 flatten 중첩된 컬렉션 안의 원소 처리**  
flatMap 함수는 람다를 컬렉션의 모든 객체에 적용하고, 결과로 얻어지는 리스트를 하나의 리스트로 모은다.  
````kotlin
val strings = listOf("abc", "def", "fed")
println(strings.flatMap { it.toList() }) //String.toList() 로 각 원소를 리스트로 만든뒤, 하나로 합친다.
// [a, b, c, d, e, f, f, e, d]
````
flatten 함수는 원소의 가공없이 펼치기만 하는데 사용한다. 
````kotlin
val stringListOfList = listOf(listOf("a","b"), listOf("c", "d"))
println(stringListOfList.flatten())
// [a, b, c, d]
````

**6. 컬렉션의 Lazy 연산**  
앞에서 소개한 컬렉션 함수들은, 매 연산마다 즉시 결과컬렉션을 생성한다.  
Sequence 를 사용하면 필요할때만 연산을 하고 컬렉션 생성을 한번만 할 수 있다.  
````kotlin
people.map(Person::name).filter { it.startsWith("A") }
````
코틀린 라이브러리 문서를 참조하면, map() 과 filter() 는 각각 리스트를 반환한다고 적혀있다.  
이는 컬렉션 연산의 연쇄가 여러개의 리스트를 만든다는 뜻이다.  
원소가 많은 경우 이는 성능에 큰 결함이 될 수 있다.  
Sequence 를 이용하여 이를 개선할 수 있다.  
````kotlin
people.asSequence()
    .map(Person::name)
    .filter { it.startsWith("A") }
    .toList()
````
Sequence 는 iterater 메서드만을 가지고 있다. 원소를 순차적으로 소비한다면, 시퀀스를 그대로 사용하여도 좋고  
Index 로 원소에 접근하는 등의 행위가 필요하다면 toList() 로 변환하여 사용하면 된다.  

Sequence 는 필요하기 전까지 연산을 수행하지 않는다. 즉 toList() 와 같이 최종 연산을 하지 않으면 연산도 하지 않으며, Sequence 의 참조만 반환한다.  

````kotlin
listOf(1, 2, 3).asSequence()
    .map(it * it) 
    .filter { print("$it"); it % 2 == 0 } // 이 코드는 아무것도 출력하지 않는다. 

listOf(1, 2, 3).asSequence()
    .map(it * it)
    .filter { print("$it"); it % 2 == 0 }
    .toList() // toList() 하는 시점에서 모든 연산결과가 계산된다
// 1, 4, 9
````
Sequence 가 Java의 스트림과 동일하다고 생각하면 이해가 쉽다.  
  
**7. 수신 객체 지정 람다**  
with 과 apply 는 동일하지만 apply 는 꼭 수신객체를 리턴 해야한다.
````kotlin
fun createAlphabet(): String {
    val stringBuilder = StringBuilder()
    return with(stringBuilder) { // 수신 객체 지정 (stringBuilder)
        for (letter in 'A'..'Z') {
            this.append(letter) // this 로 수신 객체에 접근
        }
        append("Now I know the Alphabet") // this 명시없이 stringBuilder 호출
        this.toString() // Lambda 값 반환
    }
}
````
