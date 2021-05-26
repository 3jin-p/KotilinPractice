package com.jin.wod.practice.kotlin_in_action.chapter_4.delegate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DelegateTest {

    @Test
    fun testDelegate() {
        val slack = Slack()
        val slackProvider = MessageProvider("안녕?", slack)
        assertThat(slackProvider.send()).isEqualTo("Slack Message")
        assertThat(slackProvider.provideMessage()).isEqualTo("안녕? Slack Message")

        val teams = MsTeams()
        val teamsProvider = MessageProvider("Hello", teams)
        assertThat(teamsProvider.send()).isEqualTo("Teams Message")
        assertThat(teamsProvider.provideMessage()).isEqualTo("Hello Teams Message")

        val messageProvider = MessageProvider("Bojour", teamsProvider)
        assertThat(messageProvider.send()).isEqualTo("Teams Message")
    }
}