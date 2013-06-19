package com.jeffrey.refactoring

import scala.collection.mutable.ListBuffer

class Customer(val name: String) {
    val rentals = ListBuffer[Rental]()

    def addRental(rental: Rental) {
        rentals += rental
    }

    def calculateFrequentRenterPoints = {
        rentals.foldLeft(0)((total, rental) => {
            if (rental.movie.priceCode == PriceCode.NEW_RELEASE && rental.daysRented > 1) {
                total + 2
            } else {
                total + 1
            }
        })
    }

    def calculateTotalCharge = {
        rentals.foldLeft(0.0)(_ + _.calculateCharge)
    }

    def calculateLineItems: List[String] = {
        rentals.foldRight(List[String]())((rental, lines) => {
            val line: String = "\t" + rental.movie.title + "\t" + rental.calculateCharge
            line :: lines
        })
    }

    def statement: String = {
        "Rental Record for " + name + "\n" +
                calculateLineItems.mkString("\n") + "\n" +
                "You owed " + calculateTotalCharge + "\n" +
                "You earned " + calculateFrequentRenterPoints + " frequent renter points\n"
    }
}