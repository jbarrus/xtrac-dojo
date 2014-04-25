package com.jeffrey.romannumerals

class Converter {
    def toDecimal(numeral: String) = {
        1
    }

    def toRoman(decimal: Int) = {

        def helper(n: Int, acc: String): String = {
            n match {
                case 0 => acc
                case 9 => helper(0, acc + "IX")
                case 4 => helper(0, acc + "IV")
                case i: Int if (i >= 100) => helper(i - 100, acc + "C")
                case i: Int if (i >= 90) => helper(i - 90, acc + "XC")
                case i: Int if (i >= 50) => helper(i - 50, acc + "L")
                case i: Int if (i >= 40) => helper(i - 40, acc + "XL")
                case i: Int if (i >= 10) => helper(i - 10, acc + "X")
                case i: Int if (i >= 5) => helper(i - 5, acc + "V")
                case _ => helper(n - 1, acc + "I")
            }
        }
        helper(decimal, "");
    }
}
