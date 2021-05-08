package com.jin.wod.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Stock(
    @Column(name = "stock")
    val value: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Stock) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}