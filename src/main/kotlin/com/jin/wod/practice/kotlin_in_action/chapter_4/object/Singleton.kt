package com.jin.wod.practice.kotlin_in_action.chapter_4.`object`

object Singleton {
    var prop = 0

    fun scaleUp() {
        this.prop += 1
    }
}