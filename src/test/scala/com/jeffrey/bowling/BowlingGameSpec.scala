package com.jeffrey.bowling

import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class BowlingGameSpec extends FunSpec with ShouldMatchers {
    trait WithGame {
        val game = new BowlingGame

        def rollMany(rolls: Int, pins: Int) {
            for (i <- 1 to rolls) {
                game.roll(pins)
            }
        }

        def rollStrike {
            game.roll(10)
        }

        def rollSpare {
            game.roll(5)
            game.roll(5)
        }
    }

    describe("A BowlingGame") {
        it("scores zero for an all gutter ball game") {
            new WithGame {
                rollMany(20, 0)
                game.score should be(0)
            }
        }

        it("scores 20 if all ones are bowled") {
            new WithGame {
                rollMany(20, 1)
                game.score should be(20)
            }
        }

        it("adds the next bowl for a spare") {
            new WithGame {
                rollSpare
                game.roll(3)
                rollMany(17, 0)
                game.score should be(16)
            }
        }

        it("adds the next frame for a strike") {
            new WithGame {
                rollStrike
                game.roll(5)
                game.roll(3)
                rollMany(16, 0)
                game.score should be(26)
            }
        }
    }
}
