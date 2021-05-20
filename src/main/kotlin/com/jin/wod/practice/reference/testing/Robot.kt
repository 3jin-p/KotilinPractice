package com.jin.wod.practice.reference.testing

class Robot(var movingStrategy: MovingStrategy) {
    fun move():String {
        return movingStrategy.move();
    }

    fun changeMovingStrategy(movingStrategy: MovingStrategy) {
        this.movingStrategy = movingStrategy;
    }
}