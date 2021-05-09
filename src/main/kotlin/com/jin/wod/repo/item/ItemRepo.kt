package com.jin.wod.repo.item

import com.jin.wod.domain.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepo: JpaRepository<Item, Long> {
}