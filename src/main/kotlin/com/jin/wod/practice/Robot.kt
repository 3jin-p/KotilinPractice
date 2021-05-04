package com.jin.wod.practice

import com.jin.wod.practice.MovingStrategy

class Robot(var movingStrategy: MovingStrategy) {
    fun move():String {
        return movingStrategy.move();
    }

    fun changeMovingStrategy(movingStrategy: MovingStrategy) {
        this.movingStrategy = movingStrategy;
    }
}