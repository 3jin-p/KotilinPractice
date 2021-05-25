package com.jin.wod.practice.kotlin_in_action.chapter_4.abstract_property

class SubscribingUser(val firstName: String, val lastName: String): User {
    override val name
        get() = firstName + lastName;
}