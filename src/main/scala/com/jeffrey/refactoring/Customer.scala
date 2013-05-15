package com.jeffrey.refactoring

import scala.collection.mutable.ListBuffer

class Customer(val name: String) {
    val rentals = ListBuffer[Rental]()

    def addRental(rental: Rental) {
        rentals += rental
    }

    def statement: String = {
        var result: String = "Rental Record for " + name + "\n"
        var totalAmount: Double = 0
        var frequentRenterPoints: Int = 0

        rentals.foreach(each => {
            var thisAmount: Double = 0;

            each.movie.priceCode match {
                case PriceCode.REGULAR => {
                    thisAmount += 2
                    if (each.daysRented > 2)
                        thisAmount += (each.daysRented - 2) * 1.5
                }
                case PriceCode.NEW_RELEASE => {
                    thisAmount += each.daysRented * 3
                }
                case PriceCode.CHILDRENS => {
                    thisAmount += 1.5
                    if (each.daysRented > 3)
                        thisAmount += (each.daysRented - 3) * 1.5
                }
            }

            frequentRenterPoints += 1

            if (each.movie.priceCode == PriceCode.NEW_RELEASE && each.daysRented > 1)
                frequentRenterPoints += 1

            result += "\t" + each.movie.title + "\t" + thisAmount + "\n";

            totalAmount += thisAmount;
        })

        result += "You owed " + totalAmount + "\n"
        result += "You earned " + frequentRenterPoints + " frequent renter points\n"

        println(result)
        result
    }
}