package com.jin.wod.practice.functional

class Calculator {

    fun add(x: Int, y: Int): Int {
       return calculate(x, y) {a, b -> a + b}
    }

    fun minus(x: Int, y: Int): Int {
        return calculate(x, y) {a, b -> a - b}
    }

    companion object CaculMachine {
        fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int) :Int {
            return operation(x,y)
        }
    }
}