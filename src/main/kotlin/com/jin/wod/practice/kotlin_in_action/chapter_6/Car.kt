package com.jin.wod.practice.kotlin_in_action.chapter_6

class Car(var energy: Int?) {
    var price: Int = 10

    fun addPrice(price: Int?) {
        price?.let { price -> this.price += price }
    }
    
    fun move(): Int {
        return energy ?: throw IllegalAccessException("에너지가 없으면 이동할 수 없습니다.")
    }
}