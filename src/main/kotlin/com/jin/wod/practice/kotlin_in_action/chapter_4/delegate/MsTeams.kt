package com.jin.wod.practice.kotlin_in_action.chapter_4.delegate

class MsTeams: Message {
    override fun send(): String = "Teams Message"
}