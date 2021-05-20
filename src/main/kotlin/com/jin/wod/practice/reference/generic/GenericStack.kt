package com.jin.wod.practice.reference.generic

class GenericStack<E>(vararg items: E) {

    private val elements = items.toMutableList();

    fun push(element: E) = elements.add(element);

    fun pop(): E = elements.removeAt(elements.size - 1)

    fun peek(): E = elements.last()

    fun isEmpty():Boolean = elements.isEmpty()

    fun size():Int = elements.size
}