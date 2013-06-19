package com.jeffrey.ocr

import scala.collection.mutable

trait Digit {
    val marks: Set[DigitMark]
    val number: Option[Int]
    val isLegal = true

    def alternatives = Digit.getAlternatives(this)

    def toChar = {
        number match {
            case Some(n) => (48 + n).toChar
            case None => '?'
        }
    }

    override def toString = {
        (if (marks.contains(TopMark)) " _ " else "   ") + "\n" +
                (if (marks.contains(TopLeftMark)) "|" else " ") +
                (if (marks.contains(MiddleMark)) "_" else " ") +
                (if (marks.contains(TopRightMark)) "|" else " ") + "\n" +
                (if (marks.contains(BottomLeftMark)) "|" else " ") +
                (if (marks.contains(BottomMark)) "_" else " ") +
                (if (marks.contains(BottomRightMark)) "|" else " ") + "\n"
    }
}
case object Zero extends Digit {
    val number = Some(0)
    val marks = Set[DigitMark](
        TopMark, TopRightMark, BottomRightMark, BottomMark,
        BottomLeftMark, TopLeftMark
    )
}
case object One extends Digit {
    val number = Some(1)
    val marks = Set[DigitMark](TopRightMark, BottomRightMark)
}
case object Two extends Digit {
    val number = Some(2)
    val marks = Set[DigitMark](TopMark, TopRightMark, MiddleMark, BottomLeftMark, BottomMark)
}
case object Three extends Digit {
    val number = Some(3)
    val marks = Set[DigitMark](TopMark, TopRightMark, MiddleMark, BottomRightMark, BottomMark)
}
case object Four extends Digit {
    val number = Some(4)
    val marks = Set[DigitMark](TopLeftMark, TopRightMark, MiddleMark, BottomRightMark)
}
case object Five extends Digit {
    val number = Some(5)
    val marks = Set[DigitMark](TopMark, TopLeftMark, MiddleMark, BottomRightMark, BottomMark)
}
case object Six extends Digit {
    val number = Some(6)
    val marks = Set[DigitMark](TopMark, TopLeftMark, MiddleMark, BottomLeftMark, BottomRightMark, BottomMark)
}
case object Seven extends Digit {
    val number = Some(7)
    val marks = Set[DigitMark](TopMark, TopRightMark, BottomRightMark)
}
case object Eight extends Digit {
    val number = Some(8)
    val marks = Set[DigitMark](TopMark, TopLeftMark, MiddleMark, TopRightMark, BottomLeftMark, BottomRightMark,
                               BottomMark)
}
case object Nine extends Digit {
    val number = Some(9)
    val marks = Set[DigitMark](TopMark, TopLeftMark, MiddleMark, TopRightMark, BottomRightMark, BottomMark)
}

case class InvalidDigit(marks: Set[DigitMark]) extends Digit {
    val number = None
    override val isLegal = false
}

object Digit {
    val allDigits = Set[Digit](Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine)

    def apply(lines: List[String]) = {
        val marks = parseDigitMarks(lines)
        allDigits.find(_.marks == marks).getOrElse(InvalidDigit(marks))
    }

    private def parseDigitMarks(lines: List[String]): Set[DigitMark] = {
        val marks = new mutable.HashSet[DigitMark]

        if (lines(0)(1) == '_')
            marks += TopMark
        if (lines(1)(0) == '|')
            marks += TopLeftMark
        if (lines(1)(2) == '|')
            marks += TopRightMark
        if (lines(1)(1) == '_')
            marks += MiddleMark
        if (lines(2)(0) == '|')
            marks += BottomLeftMark
        if (lines(2)(2) == '|')
            marks += BottomRightMark
        if (lines(2)(1) == '_')
            marks += BottomMark

        return marks.toSet
    }

    /**
     * Return a list of any digits that the given digit can mutate into
     * with only one digit mark change
     */
    def getAlternatives(digit: Digit): List[Digit] = {
        allDigits.filter(d => {
            val intersect = (d.marks intersect digit.marks)
            math.abs(intersect.size - math.max(d.marks.size, digit.marks.size)) == 1
        }).toList
    }
}

trait DigitMark
case object TopMark extends DigitMark
case object TopRightMark extends DigitMark
case object BottomRightMark extends DigitMark
case object BottomMark extends DigitMark
case object BottomLeftMark extends DigitMark
case object TopLeftMark extends DigitMark
case object MiddleMark extends DigitMark
