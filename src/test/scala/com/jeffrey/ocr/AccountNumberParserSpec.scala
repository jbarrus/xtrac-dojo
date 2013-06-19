package com.jeffrey.ocr

import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.prop.TableDrivenPropertyChecks
import java.io.File

@RunWith(classOf[JUnitRunner])
class AccountNumberParserSpec extends FunSpec with ShouldMatchers with TableDrivenPropertyChecks {
    val accountNumbers = Table(("inputFile", "result"),
                                   ("zero.txt", "000000000"),
                                   ("one.txt", "711111111"),
                                   ("two.txt", "322222222"),
//                                   ("three.txt", "333393333"),
                                   ("four.txt", "444444444 ERR"),
//                                   ("five.txt", "555555555 AMB ['555655555', '559555555']"),
//                                   ("six.txt", "666666666 AMB ['666566666', '686666666']"),
                                   ("seven.txt", "777777177"),
//                                   ("eight.txt", "888888888 AMB ['888886888', '888888880', '888888988']"),
//                                   ("nine.txt", "999999999 AMB ['899999999', '993999999', '999959999']"),
                                   ("case3_test1.txt", "000000051")
                                   //("case3_test2.txt", "49006771? ILL"),
                                   //("case3_test3.txt", "1234?678? ILL")
    )

    describe("An AccountNumberParser") {
        it("takes a file and produces an account number") {
            forAll(accountNumbers) {
                (inputFile: String, result:String) => {
                    val file = new File("src/test/resources/com/jeffrey/ocr/" + inputFile)

                    val accountNumber = AccountNumberParser.parse(file)
                    accountNumber.toString should be(result)
                }
            }
        }
    }
}
