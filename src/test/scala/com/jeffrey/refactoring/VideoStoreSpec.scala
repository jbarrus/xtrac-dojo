package com.jeffrey.refactoring

import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class VideoStoreTest extends FunSpec with ShouldMatchers {
    trait WithCustomer {
        val customer = new Customer("Fred")
    }

    describe("A Video Store") {
        it("generates a statement for a single new release rental") {
            new WithCustomer {
                customer.addRental(Rental(Movie("The Cell", PriceCode.NEW_RELEASE), 3))
                customer.statement should be
                    "Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n"
            }
        }

        it("generates a statement for two new release rentals") {
             new WithCustomer {
                 customer.addRental(Rental(Movie("The Cell", PriceCode.NEW_RELEASE), 3))
                 customer.addRental(Rental(Movie("The Tigger Movie", PriceCode.NEW_RELEASE), 3))
                 customer.statement should be
                     "Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n"
             }
        }

        it("generates a statement for a children rental") {
            new WithCustomer {
                customer.addRental(Rental(Movie("The Tigger Movie", PriceCode.CHILDRENS), 3))
                customer.statement should be
                    "Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n"
            }
        }

        it("generates a statement for multiple regular rentals") {
            new WithCustomer {
                customer.addRental(Rental(Movie("Plan 9 from Outer Space", PriceCode.REGULAR), 1))
                customer.addRental(Rental(Movie("8 1/2", PriceCode.REGULAR), 2))
                customer.addRental(Rental(Movie("Eraserhead", PriceCode.REGULAR), 3))
                customer.statement should be
                    "Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n"
            }
        }
    }

    describe("The VideoStore Rental Object") {
        trait WithRental {
            val movie = Movie("The Game", PriceCode.NEW_RELEASE)
            val rental = Rental(movie, 3)
        }

        it("should calculate a new release rental correctly") {
            new WithRental {
                rental.calculateCharge should be(9)
            }
        }
    }
}