package com.jin.wod.rest

import com.jin.wod.TestBase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
abstract class RestApiTestBase: TestBase() {
}