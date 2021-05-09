package com.jin.wod.rest.dto

import com.jin.wod.domain.Color
import com.jin.wod.domain.Item
import com.jin.wod.domain.ItemType

data class ItemDto(
    val id: Long?,
    var name: String,
    var type: ItemType,
    var color: Color
) {
    fun toEntity(): Item {
        return Item(id, name, type, color)
    }
}