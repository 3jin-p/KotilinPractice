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

