package com.jin.wod.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Color(
    @Column(name = "color_name")
    val name: String,
    codeParam: String
) {
    @Column(name = "color_code")
    private val code: String

    companion object {
        private const val COLOR_CODE_LENGTH: Int = 7
    }

    init {
        validateCodeParam(codeParam)
        code = codeParam
    }

    private fun validateCodeParam(codeParam: String) {
        if (!codeParam.startsWith("#")) {
            throw IllegalArgumentException("색상 코드는 # 으로 시작되어야 합니다.")
        }
        if (codeParam.length != COLOR_CODE_LENGTH) {
            throw IllegalArgumentException("색상 코드는 # 포함 총 7자리 입니다")
        }
    }
}