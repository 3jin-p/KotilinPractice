package com.jin.wod.domain

import javax.persistence.*

@Entity
class ItemStock(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Embedded
    val size: Size,
    @Embedded
    val stock: Stock,
    @ManyToOne(fetch = FetchType.LAZY)
    val item: Item
) {
}