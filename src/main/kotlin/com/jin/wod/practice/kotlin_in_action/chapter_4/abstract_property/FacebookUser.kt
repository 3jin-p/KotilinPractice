package com.jin.wod.practice.kotlin_in_action.chapter_4.abstract_property

class FacebookUser(accountId: Long): User {
    override val name = getFacebookName(accountId)

    private fun getFacebookName(accountId: Long): String {
        return accountId.toString() +  "로 찾아낸 페이스북에 설정된 이름";
    }
}