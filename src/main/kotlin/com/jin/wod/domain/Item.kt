package com.jin.wod.domain

import javax.persistence.*

@Entity
class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,
    var name: String,
    @Enumerated(value = EnumType.STRING)
    var type: ItemType,
    @Embedded
    var color: Color,
    @OneToMany(mappedBy = "item_id", cascade = [CascadeType.ALL])
    var stocks: List<ItemStock> = ArrayList()
) {
}