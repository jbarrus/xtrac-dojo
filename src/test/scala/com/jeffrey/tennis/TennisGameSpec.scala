package com.jeffrey.tennis

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class TennisGameSpec extends FunSpec with ShouldMatchers {
    trait WithDefaultPlayers {
        val game = new TennisGame("player1", "player2")
    }

    describe("A TennisGame") {
        it("should have an initial score of zero") {
            new WithDefaultPlayers {
                game.score should be("Love all")
            }

        }

        it("should have player one score 15 when player two scores") {
            new WithDefaultPlayers {
                game.wonPoint("player1")
                game.score should be("Fifteen Love")
            }
        }

        it("should have player two score 15 when player two scores") {
            new WithDefaultPlayers {
                game.wonPoint("player2")
                game.score should be("Love Fifteen")
            }
        }

        it("should declare a winner if player one scores four times") {
            new WithDefaultPlayers {
                game.wonPoint("player1")
                game.wonPoint("player1")
                game.wonPoint("player1")
                game.wonPoint("player1")

                game.score should be ("Win for player1")
            }
        }

        it("should have a score of deuce if both players score four times") {
            new WithDefaultPlayers {
                game.wonPoint("player1")
                game.wonPoint("player1")
                game.wonPoint("player1")

                game.wonPoint("player2")
                game.wonPoint("player2")
                game.wonPoint("player2")

                game.score should be ("Deuce")
            }
        }

        it("should handle advantage player 1") {
            new WithDefaultPlayers {
                game.wonPoint("player1")
                game.wonPoint("player1")
                game.wonPoint("player1")

                game.wonPoint("player2")
                game.wonPoint("player2")
                game.wonPoint("player2")

                game.wonPoint("player1")

                game.score should be ("Advantage player1")
            }
        }

        it("should handle a longer game with player 1 winning") {
            val game = new TennisGame("Bjorn Borg", "John McEnroe")

            game.wonPoint("Bjorn Borg")
            game.wonPoint("Bjorn Borg")
            game.wonPoint("Bjorn Borg")

            game.wonPoint("John McEnroe")
            game.wonPoint("John McEnroe")
            game.wonPoint("John McEnroe")

            game.wonPoint("Bjorn Borg")
            game.wonPoint("Bjorn Borg")

            game.score should be("Win for Bjorn Borg")
        }
    }
}
