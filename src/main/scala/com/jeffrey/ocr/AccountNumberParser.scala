package com.jeffrey.ocr

import java.io.File
import scala.io.Source
import scala.collection.mutable

object AccountNumberParser {
    def parse(inputFile: File) = {
        AccountNumber(Source.fromFile(inputFile).getLines.mkString("\n"))
    }
}
