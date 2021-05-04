package com.jin.wod

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WodApplication

fun main(args: Array<String>) {
	runApplication<WodApplication>(*args)
}
