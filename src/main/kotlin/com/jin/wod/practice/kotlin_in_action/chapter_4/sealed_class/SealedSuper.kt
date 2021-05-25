package com.jin.wod.practice.kotlin_in_action.chapter_4.sealed_class

// sealed class 를 확장하는 클래스는 sealed class 내부에 있어야 한다.
// 상속하는 클래스의 종류를 제한할 수 있다.
sealed class SealedSuper {
    class Sub(val name: String): SealedSuper() {}

    class Sub2(val name: String): SealedSuper() {}
}