Class
--

**1. Interface**  
Kotlin 의 인터페이스는 추상 프로퍼티를 지닐 수 있다.

``` kotlin
interface User {
    val name: String
}

class PrivateUser(override val name: String): User {
}

// 계산한 값을 저장해서 가지고 있다가 보여준다.
class FacebookUser(accountId: Long): User {
    override val name = getFacebookName(accountId)

    private fun getFacebookName(accountId: Long): String {
        return accountId.toString() +  "로 찾아낸 페이스북에 설정된 이름";
    }
}

// 매번 Getter 호출 전 계산해서 값을 보여준다.
class SubscribingUser(val firstName: String, val lastName: String): User {
    override val name
        get() = firstName + lastName;
}
```

**2. Sealed Class**
``` kotlin
// sealed class 를 확장하는 클래스는 sealed class 내부에 있어야 한다.
// 상속하는 클래스의 종류를 제한할 수 있다.
sealed class SealedSuper {
    class Sub(val name: String): SealedSuper() {}

    class Sub2(val name: String): SealedSuper() {}
}
```

**3. Data Class**  
Data Class 는 getter, setter, toString, equalsAndHashcode가 구현되어있다.  
Data Class 는 기본적으로 val 필드만을 지니게 해 불변객체를 지향하도록 하여  
Value Object 로 사용하라고 권한다.
``` kotlin
data class Address(val zipCode: String, val city: String) {}

class DataClassTest {
    lateinit var addr: Address

    @BeforeEach
    fun setUp() {
        addr = Address("KS013", "SEOUL")
    }

    @Test
    fun dataClassHasEqualsMethod() {
        val seoul = Address("KS013", "SEOUL")
        assertTrue(addr == seoul)
    }

    @Test
    fun dataClassHasHashCodeMethod() {
        val addressSet = setOf<Address>(addr)
        assertTrue(addressSet.contains(Address("KS013", "SEOUL")))
    }

    @Test
    fun dataClassCopyTest() {
        val modifiedAddress = addr.copy(zipCode = "MODIFIED")
        assertTrue(modifiedAddress == Address("MODIFIED", "SEOUL"))
        assertFalse(addr == modifiedAddress)
    }
}
```

**4. Object Class**  
Object Class 는 어플리케이션에서 Singleton 으로 구현된다.
``` kotlin
    object Singleton {
        var prop = 0
    
        fun scaleUp() {
            this.prop += 1
        }
    }

    @Test
    fun test() {
        Singleton.scaleUp()
        val instance = Singleton

        assertThat(instance.prop).isEqualTo(1)
    }
```

**5. Companion object**  
Companion Object 는 자바의 Static 과 유사한 기능을 한다. 

```kotlin
    class CompanionObject {
        companion object{
            val TEST = "test"
            fun method(i:Int) = i + 10
        }
    }

    @Test
    fun test() {
        assertThat(CompanionObject.TEST).isEqualTo("test")
        assertThat(CompanionObject.method(1)).isEqualTo(11)
    }
```

