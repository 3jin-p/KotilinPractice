package com.jin.wod.practice.spclass.sealedclass

sealed class Animal(val name:String) {
    class Cat(name: String): Animal(name = name)
    class Dog(name: String): Animal(name = name)

    fun grawl() = print("${name} !! ${name} !!")

    fun greet(animal: Animal): String {
        return when (animal) {
            is Cat -> "Hi~ Cat, I'm ${this.name}"
            is Dog -> "Hi~ Dog, I'm ${this.name}"
        }
    }
}