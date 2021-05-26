package com.jin.wod.practice.kotlin_in_action.chapter_4.delegate

class Slack: Message {
    override fun send(): String = "Slack Message"
}