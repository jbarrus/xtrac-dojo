package com.jeffrey.refactoring

case class Rental(val movie: Movie, val daysRented: Int) {
    def calculateCharge: Double = {
        var charge: Double = 0.0
        movie.priceCode match {
            case PriceCode.REGULAR => {
                charge += 2
                if (daysRented > 2) {
                    charge += (daysRented - 2) * 1.5
                }
            }
            case PriceCode.NEW_RELEASE => {
                charge += daysRented * 3
            }
            case PriceCode.CHILDRENS => {
                charge += 1.5
                if (daysRented > 3) {
                    charge += (daysRented - 3) * 1.5
                }
            }
        }
        charge
    }
}