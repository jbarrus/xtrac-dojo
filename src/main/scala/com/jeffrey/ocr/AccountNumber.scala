package com.jeffrey.ocr

class AccountNumber(digits: List[Digit], ambiguities: List[List[Digit]] = List()) {

    lazy val isValid = AccountNumber.isValid(digits)
    lazy val isLegal = AccountNumber.isLegal(digits)

    override def toString = {
        val digitString = digits.map(_.toChar).mkString

        val errorMessage =
            if (!ambiguities.isEmpty) " AMB [ " + ambiguities.map(_.map(_.toChar).mkString).mkString(", ") + " ]"
            else if (!isValid) " ERR"
            else if (!isLegal) " ILL"
            else ""

        digitString + errorMessage
    }
}

object AccountNumber {
    def apply(accountNumber: String) = {
        parse(accountNumber)
    }

    def checkSum(digits: List[Digit]) = {
        val each =
            for (i <- 1 to digits.length)
            yield digits(digits.length - i).number.get * i

        each.sum % 11
    }

    def isValid(digits: List[Digit]) = checkSum(digits) == 0

    def isLegal(digits: List[Digit]) = digits.forall(_.isLegal)

    def parse(input: String): AccountNumber = {
        val lines = input.split("\n").toList
        val digitLines = parseDigitInputs(lines)
        val digits = digitLines.map(lines => Digit(lines))

        if (isLegal(digits) && isValid(digits)) {
            new AccountNumber(digits)
        } else {
            findAlternativeDigits(digits) match {
                case h :: Nil => new AccountNumber(h)
                case h :: t => new AccountNumber(h, t)
                case Nil => new AccountNumber(digits)
            }
        }
    }

    private def findAlternativeDigits(digitsList:List[Digit]): List[List[Digit]] = {
        (for {
            i <- 0 until digitsList.size
            a <- digitsList(i).alternatives
            n = digitsList.updated(i, a)
            if (isLegal(n) && isValid(n))
        } yield {
            digitsList.updated(i, a)
        }).toList
    }

    /**
     * Split into list of strings that represent each digit
     */
    private def parseDigitInputs(lines: List[String]): List[List[String]] = {
        lines.map(_.grouped(3).toList)
        .transpose
    }

}