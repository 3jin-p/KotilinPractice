package com.jin.wod.service.item

import com.jin.wod.domain.Item
import com.jin.wod.repo.item.ItemRepo
import com.jin.wod.rest.dto.ItemDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Service
class ItemService(
    val itemRepo: ItemRepo
) {

    @Transactional
    fun save(itemSaveRequest: ItemDto) {
        itemRepo.save(itemSaveRequest.toEntity());
    }
}