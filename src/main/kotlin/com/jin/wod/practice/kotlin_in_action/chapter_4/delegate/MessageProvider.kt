package com.jin.wod.practice.kotlin_in_action.chapter_4.delegate

class MessageProvider(
        private val message: String,
        var messageType: Message): Message by messageType
{
    fun provideMessage(): String = message + " " + messageType.send()
}