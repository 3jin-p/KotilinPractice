package com.jin.wod.practice.reference

import com.jin.wod.practice.reference.testing.Robot
import com.jin.wod.practice.reference.testing.RunStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RobotTest {

    @Test
    fun test() {
        val robot = Robot(RunStrategy());
        assertThat(robot.move()).isEqualTo("Run");
    }
}