package com.example.cardgameapp

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.emptyLongSet
import com.example.cardgameapp.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    private lateinit var deckOfCards: MutableList<Card>

    private var cardCount = 0
    private var cardsLeft = 43
    private lateinit var currentPile : ImageView
    private var pileCount = 1
    private lateinit var currentCard: Card
    private var currentCardResId = 0
    private var gameRunning = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)

        setContentView(binding.root)

//      Creates a shuffled list of all the cards
        createShuffledDeck()

//      Creates a layout with a card on each pile
        createStartLayout()

//      Game starts
//        while (gameRunning) {
            currentPile = determinePile(pileCount)
            currentCard = deckOfCards[cardCount]
            deckOfCards.removeFirst()

            openPile()
//        }


//      showCard(deckOfCards[cardCount])

        binding.buttonHigher.setOnClickListener {
            val guess = "higher"

            checkCorrectGuess(guess)

        }


        binding.buttonLower.setOnClickListener {
            val guess = "lower"

            checkCorrectGuess(guess)

        }
    }

    private fun checkCorrectGuess(guess: String) {
        // check guess
        val correctGuess = checkGuess(guess)
        if (correctGuess) {
            currentCard = deckOfCards[0]
            openPile()
        } else {
            currentCard = deckOfCards[0]
            closePile()
            pileCount++
            openPile()
        }
        deckOfCards.removeFirst()
    }

    private fun closePile() {
        when (pileCount) {
            1 -> binding.imageViewOne.setImageResource(R.drawable.backside_card)
            2 -> binding.imageViewTwo.setImageResource(R.drawable.backside_card)
            3 -> binding.imageViewThree.setImageResource(R.drawable.backside_card)
            4 -> binding.imageViewFour.setImageResource(R.drawable.backside_card)
            5 -> binding.imageViewFive.setImageResource(R.drawable.backside_card)
            6 -> binding.imageViewSix.setImageResource(R.drawable.backside_card)
            7 -> binding.imageViewSeven.setImageResource(R.drawable.backside_card)
            8 -> binding.imageViewEight.setImageResource(R.drawable.backside_card)
            9 -> binding.imageViewNine.setImageResource(R.drawable.backside_card)
        }
    }
    private fun openPile() {
        currentCardResId = showCard(currentCard.name)

        when (pileCount) {
            1 -> binding.imageViewOne.setImageResource(currentCardResId)
            2 -> binding.imageViewTwo.setImageResource(currentCardResId)
            3 -> binding.imageViewThree.setImageResource(currentCardResId)
            4 -> binding.imageViewFour.setImageResource(currentCardResId)
            5 -> binding.imageViewFive.setImageResource(currentCardResId)
            6 -> binding.imageViewSix.setImageResource(currentCardResId)
            7 -> binding.imageViewSeven.setImageResource(currentCardResId)
            8 -> binding.imageViewEight.setImageResource(currentCardResId)
            9 -> binding.imageViewNine.setImageResource(currentCardResId)
        }
    }

    private fun checkGuess(guess: String) : Boolean {
        var correctGuess = false

        if (currentCard.value == 1) {

            if (deckOfCards[0].value == 1) {
                Log.e("!!!", "false, ${deckOfCards[0].name} is lower than ${currentCard.name} ")
                correctGuess = false
            } else {
                Log.e("!!!", "true, ${deckOfCards[0].name} is higher or lower than ${currentCard.name}")
                correctGuess = true
            }

        } else if (deckOfCards[0].value == 1) {

            Log.e("!!!", "true, ${deckOfCards[0].name} is higher or lower than ${currentCard.name}")
            correctGuess = true

        } else if (deckOfCards[0].value == currentCard.value) {
            Log.e("!!!", "false, ${deckOfCards[0].name} is the same value as ${currentCard.name}")
            correctGuess = false

        } else if (deckOfCards[0].value < currentCard.value) {

            if (guess == "higher") {
                Log.e("!!!", "false, ${deckOfCards[0].name} is lower than ${currentCard.name}")
                correctGuess = false
            } else if (guess == "lower") {
                Log.e("!!!", "true, ${deckOfCards[0].name} is lower than ${currentCard.name}")
                correctGuess = true
            }

        } else if (deckOfCards[0].value > currentCard.value) {

            if (guess == "higher") {
                Log.e("!!!", "true, ${deckOfCards[0].name} is lower than ${currentCard.name}")
                correctGuess = true
            } else if (guess == "lower") {
                Log.e("!!!", "false, ${deckOfCards[0].name} is lower than ${currentCard.name}")
                correctGuess = false
            }
        }
        return correctGuess
    }

    //
//        binding.drawPile.setOnClickListener {
//
//            binding.textViewCardCount.text = cardsLeft.toString()
//
//            if (cardsLeft > 0) {
//                cardsLeft--
//            }
//
//            if (count < deckOfCards.size) {
//                showCard(deckOfCards[count])
//            } else {
//                count = 0
//                cardsLeft = 43
//                showCard(deckOfCards[count])
//            }
//            count++
//
//            if (cardsLeft < 2) {
//                binding.drawPileBottomCard.visibility = View.GONE
//            } else {
//                binding.drawPileBottomCard.visibility = View.VISIBLE
//            }
//            if (cardsLeft == 0) {
//                binding.drawPileTopCard.visibility = View.GONE
//            } else {
//                binding.drawPileTopCard.visibility = View.VISIBLE
//            }
//        }


    private fun determinePile(pile : Int) : ImageView {
        lateinit var currentPile : ImageView

        when (pile) {
            1 -> currentPile = binding.imageViewOne
            2 -> currentPile = binding.imageViewOne
            3 -> currentPile = binding.imageViewOne
            4 -> currentPile = binding.imageViewOne
            5 -> currentPile = binding.imageViewOne
            6 -> currentPile = binding.imageViewOne
            7 -> currentPile = binding.imageViewOne
            8 -> currentPile = binding.imageViewOne
            9 -> currentPile = binding.imageViewOne
        }

        return currentPile
    }

    private fun createStartLayout() {
        binding.imageViewOne.setImageResource(R.drawable.backside_card)
        binding.imageViewTwo.setImageResource(R.drawable.backside_card)
        binding.imageViewThree.setImageResource(R.drawable.backside_card)
        binding.imageViewFour.setImageResource(R.drawable.backside_card)
        binding.imageViewFive.setImageResource(R.drawable.backside_card)
        binding.imageViewSix.setImageResource(R.drawable.backside_card)
        binding.imageViewSeven.setImageResource(R.drawable.backside_card)
        binding.imageViewEight.setImageResource(R.drawable.backside_card)
        binding.imageViewNine.setImageResource(R.drawable.backside_card)


        binding.imageDrawPileTopCard.setImageResource(R.drawable.backside_card)
        binding.imageBottomOfDrawPile.setImageResource(R.drawable.backside_card)
    }

    private fun createShuffledDeck() {
        deckOfCards = mutableListOf(
            Card("h1", 1,R.drawable.hearts_1),
            Card("h2", 2,R.drawable.hearts_2),
            Card("h3", 3,R.drawable.hearts_3),
            Card("h4", 4,R.drawable.hearts_4),
            Card("h5", 5,R.drawable.hearts_5),
            Card("h6", 6,R.drawable.hearts_6),
            Card("h7", 7,R.drawable.hearts_7),
            Card("h8", 8,R.drawable.hearts_8),
            Card("h9", 9,R.drawable.hearts_9),
            Card("h10", 10,R.drawable.hearts_10),
            Card("h11", 11,R.drawable.hearts_jack),
            Card("h12", 12,R.drawable.hearts_queen),
            Card("h13", 13,R.drawable.hearts_king),
            Card("s1", 1,R.drawable.spades_1),
            Card("s2", 2,R.drawable.spades_2),
            Card("s3", 3,R.drawable.spades_3),
            Card("s4", 4,R.drawable.spades_4),
            Card("s5", 5,R.drawable.spades_5),
            Card("s6", 6,R.drawable.spades_6),
            Card("s7", 7,R.drawable.spades_7),
            Card("s8", 8,R.drawable.spades_8),
            Card("s9", 9,R.drawable.spades_9),
            Card("s10", 10,R.drawable.spades_10),
            Card("s11", 11,R.drawable.spades_jack),
            Card("s12", 12,R.drawable.spades_queen),
            Card("s13", 13,R.drawable.spades_king),
            Card("c1", 1, R.drawable.clubs_1),
            Card("c2", 2, R.drawable.clubs_2),
            Card("c3", 3, R.drawable.clubs_3),
            Card("c4", 4, R.drawable.clubs_4),
            Card("c5", 5, R.drawable.clubs_5),
            Card("c6", 6, R.drawable.clubs_6),
            Card("c7", 7, R.drawable.clubs_7),
            Card("c8", 8, R.drawable.clubs_8),
            Card("c9", 9, R.drawable.clubs_9),
            Card("c10", 10, R.drawable.clubs_10),
            Card("c11", 11, R.drawable.clubs_jack),
            Card("c12", 12, R.drawable.clubs_queen),
            Card("c13", 13, R.drawable.clubs_king),
            Card("d1", 1, R.drawable.diamonds_1),
            Card("d2", 2, R.drawable.diamonds_2),
            Card("d3", 3, R.drawable.diamonds_3),
            Card("d4", 4, R.drawable.diamonds_4),
            Card("d5", 5, R.drawable.diamonds_5),
            Card("d6", 6, R.drawable.diamonds_6),
            Card("d7", 7, R.drawable.diamonds_7),
            Card("d8", 8, R.drawable.diamonds_8),
            Card("d9", 9, R.drawable.diamonds_9),
            Card("d10", 10, R.drawable.diamonds_10),
            Card("d11", 11, R.drawable.diamonds_jack),
            Card("d12", 12, R.drawable.diamonds_queen),
            Card("d13", 13, R.drawable.diamonds_king)
        )
        deckOfCards.shuffle()
    }

    private fun showCard(card : String) : Int {
        var i : Int

            when (card) {
            "h1" -> i = R.drawable.hearts_1
            "h2" -> i = R.drawable.hearts_2
            "h3" -> i = R.drawable.hearts_3
            "h4" -> i = R.drawable.hearts_4
            "h5" -> i = R.drawable.hearts_5
            "h6" -> i = R.drawable.hearts_6
            "h7" -> i = R.drawable.hearts_7
            "h8" -> i = R.drawable.hearts_8
            "h9" -> i = R.drawable.hearts_9
            "h10" -> i = R.drawable.hearts_10
            "h11" -> i = R.drawable.hearts_jack
            "h12" -> i = R.drawable.hearts_queen
            "h13" -> i = R.drawable.hearts_king
            "s1" -> i = R.drawable.spades_1
            "s2" -> i = R.drawable.spades_2
            "s3" -> i = R.drawable.spades_3
            "s4" -> i = R.drawable.spades_4
            "s5" -> i = R.drawable.spades_5
            "s6" -> i = R.drawable.spades_6
            "s7" -> i = R.drawable.spades_7
            "s8" -> i = R.drawable.spades_8
            "s9" -> i = R.drawable.spades_9
            "s10" -> i = R.drawable.spades_10
            "s11" -> i = R.drawable.spades_jack
            "s12" -> i = R.drawable.spades_queen
            "s13" -> i = R.drawable.spades_king
            "d1" -> i = R.drawable.diamonds_1
            "d2" -> i = R.drawable.diamonds_2
            "d3" -> i = R.drawable.diamonds_3
            "d4" -> i = R.drawable.diamonds_4
            "d5" -> i = R.drawable.diamonds_5
            "d6" -> i = R.drawable.diamonds_6
            "d7" -> i = R.drawable.diamonds_7
            "d8" -> i = R.drawable.diamonds_8
            "d9" -> i = R.drawable.diamonds_9
            "d10" -> i = R.drawable.diamonds_10
            "d11" -> i = R.drawable.diamonds_jack
            "d12" -> i = R.drawable.diamonds_queen
            "d13" -> i = R.drawable.diamonds_king
            "c1" -> i = R.drawable.clubs_1
            "c2" -> i = R.drawable.clubs_2
            "c3" -> i = R.drawable.clubs_3
            "c4" -> i = R.drawable.clubs_4
            "c5" -> i = R.drawable.clubs_5
            "c6" -> i = R.drawable.clubs_6
            "c7" -> i = R.drawable.clubs_7
            "c8" -> i = R.drawable.clubs_8
            "c9" -> i = R.drawable.clubs_9
            "c10" -> i = R.drawable.clubs_10
            "c11" -> i = R.drawable.clubs_jack
            "c12" -> i = R.drawable.clubs_queen
            "c13" -> i = R.drawable.clubs_king
            else -> i = R.drawable.backside_card
        }
        return i
    }
}