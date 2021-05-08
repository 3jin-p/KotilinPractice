package com.jin.wod.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Size(
    @Column(name = "size_name")
    val name: String,
    @Column(name = "size_value")
    val value: Int
) {
}