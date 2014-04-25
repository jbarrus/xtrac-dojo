package com.jeffrey.romannumerals

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.prop.TableDrivenPropertyChecks

@RunWith(classOf[JUnitRunner])
class ConverterSpec extends FunSpec with ShouldMatchers with TableDrivenPropertyChecks {
    trait Fixture {
        val converter = new Converter;
    }

    val romanNumeralTuples = Table(
        ("decimal", "romanNumeral"),
        (1, "I"),
        (2, "II"),
        (3, "III"),
        (10, "X"),
        (20, "XX"),
        (30, "XXX"),
        (11, "XI"),
        (9, "IX"),
        (5, "V"),
        (6, "VI"),
        (7, "VII"),
        (8, "VIII"),
        (16, "XVI"),
        (4, "IV"),
        (14, "XIV"),
        (19, "XIX"),
        (39, "XXXIX"),
        (50, "L"),
        (40, "XL"),
        (41, "XLI"),
        (49, "XLIX"),
        (100, "C"),
        (90, "XC"),
        (91, "XCI"),
        (99, "XCIX"),
        (149, "CXLIX"),
        (294, "CCXCIV")
    )

    describe("A Converter") {
        it("should convert using table") {
            new Fixture {
                forAll(romanNumeralTuples) {(decimal, romanNumeral) =>
                    converter.toRomanNumerals(decimal) should be(romanNumeral)
                }
            }
        }

        it("should return an I when given a decimal 1") {
            new Fixture {
                converter.toRoman(1) should be("I");
            }
        }

        it("should return II when given a decimal 2") {
            new Fixture {
                converter.toRoman(2) should be("II");
            }
        }
    }
}
