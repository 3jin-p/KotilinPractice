package com.jin.wod.rest

import com.jin.wod.rest.dto.TestDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/test")
    fun test(): TestDto {
        return TestDto("test", "zz")
    }
}