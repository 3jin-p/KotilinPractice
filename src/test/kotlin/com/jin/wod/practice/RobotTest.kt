package com.jin.wod.practice

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RobotTest {

    @Test
    fun test() {
        val robot = Robot(RunStrategy());
        assertThat(robot.move()).isEqualTo("Run");
    }
}