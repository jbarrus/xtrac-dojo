package com.jeffrey.refactoring

case class Movie (val title: String, val priceCode: Int)

object PriceCode {
    val CHILDRENS = 2
    val REGULAR = 0
    val NEW_RELEASE = 1
}