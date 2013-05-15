package com.jeffrey.bowling

class BowlingGame
{
    val rolls = new Array[Int](20)
    var currentRoll = 0

    def roll(pins: Int) {
        rolls(currentRoll) = pins
        if (pins == 10 && currentRoll % 2 == 0)
            currentRoll += 1
        currentRoll += 1
    }

    def score = {
        val frames = new Array[Int](10)

        10.to(1, -1) foreach (i => {
            var frameScore = 0
            var firstRoll = getRoll(2*i-1)
            var secondRoll = getRoll(2*i)

            if (firstRoll == 10) {
                frameScore += frames(i)
            } else if (firstRoll + secondRoll == 10) {
                frameScore += getRoll(2*i+1)
            }
            frameScore += firstRoll + secondRoll

            frames(i-1) = frameScore
        })

        frames.sum
    }

    def getRoll(index: Int) = {
        rolls(index-1)
    }
}
