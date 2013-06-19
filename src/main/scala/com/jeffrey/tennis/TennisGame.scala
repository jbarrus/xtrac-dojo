package com.jeffrey.tennis

class TennisGame(val player1: String, val player2: String) {
    var player1Score = 0
    var player2Score = 0

    def wonPoint(player: String) {
        if (player == player1)
            player1Score += 1
        else if (player == player2)
            player2Score += 1
    }

    def isDeuce = {
        player1Score == player2Score && player1Score > 2
    }

    def formatAdvantage = {
        if (player1Score > player2Score) {
            "Advantage " + player1
        } else {
            "Advantage " + player2
        }
    }

    def hasAdvantage = {
        player1Score != player2Score &&
        player1Score > 2 && player2Score > 2
    }

    def hasWinner = {
        player1Score > 3 && player1Score - player2Score > 1 ||
            player2Score > 3 && player2Score - player1Score > 1
    }

    def formatWinner = {
        if (player1Score > player2Score)
            "Win for " + player1
        else
            "Win for " + player2
    }

    def formatPoints(points: Int) = {
        points match {
            case 0 => "Love"
            case 1 => "Fifteen"
            case 2 => "Thirty"
            case 3 => "Forty"
        }
    }

    def score = {
        if (hasWinner) {
            formatWinner
        } else if (isDeuce) {
            "Deuce"
        } else if (hasAdvantage) {
            formatAdvantage
        } else if (player1Score == player2Score) {
            formatPoints(player1Score) + " all"
        } else {
            formatPoints(player1Score) + " " + formatPoints(player2Score)
        }
    }
}
