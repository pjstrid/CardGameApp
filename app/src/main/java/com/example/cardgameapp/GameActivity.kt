package com.example.cardgameapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cardgameapp.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    private lateinit var deckOfCards: MutableList<Card>

    private var cardCount = 0
    private var cardsLeft = 43
    private lateinit var currentPile : ImageView
    private var pileCount = 1
    private lateinit var currentCard: Card
    private var gameRunning = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)

        setContentView(binding.root)

//      Creates a shuffled list of all the cards
        createShuffledDeck()

//      Creates a layout with a card on each pile
        createStartLayout()
        binding.textViewCardCount.text = cardsLeft.toString()

//      Game starts
            currentPile = determinePile(pileCount)
            currentCard = deckOfCards[cardCount]
            deckOfCards.removeFirst()

            openPile()

        binding.buttonHigher.setOnClickListener {

            val guess = "higher"
            checkCorrectGuess(guess)
            updateDrawPile()
            if (cardsLeft == 0) {
                gameRunning = false
                Toast.makeText(this, "You won!", Toast.LENGTH_LONG).show()
            } else if (pileCount == 10) {
                Toast.makeText(this, "You lost!", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonLower.setOnClickListener {

            val guess = "lower"
            checkCorrectGuess(guess)
            updateDrawPile()
            if (cardsLeft == 0) {
                gameRunning = false
                Toast.makeText(this, "You won!", Toast.LENGTH_LONG).show()
            } else if (pileCount == 10) {
                Toast.makeText(this, "You lost!", Toast.LENGTH_LONG).show()
            }
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

        when (pileCount) {
            1 -> binding.imageViewOne.setImageResource(currentCard.resId)
            2 -> binding.imageViewTwo.setImageResource(currentCard.resId)
            3 -> binding.imageViewThree.setImageResource(currentCard.resId)
            4 -> binding.imageViewFour.setImageResource(currentCard.resId)
            5 -> binding.imageViewFive.setImageResource(currentCard.resId)
            6 -> binding.imageViewSix.setImageResource(currentCard.resId)
            7 -> binding.imageViewSeven.setImageResource(currentCard.resId)
            8 -> binding.imageViewEight.setImageResource(currentCard.resId)
            9 -> binding.imageViewNine.setImageResource(currentCard.resId)
        }
    }

    private fun checkGuess(guess: String) : Boolean {
        var correctGuess = false

        if (currentCard.value == 1) {

            if (deckOfCards[0].value == 1) {
                Log.e("!!!", "false, ${deckOfCards[0].name} is the same value as ${currentCard.name} ")
                correctGuess = false
            } else {
                Log.i("!!!", "true, ${deckOfCards[0].name} is higher or lower than ${currentCard.name}")
                correctGuess = true
            }

        } else if (deckOfCards[0].value == 1) {

            Log.i("!!!", "true, ${deckOfCards[0].name} is higher or lower than ${currentCard.name}")
            correctGuess = true

        } else if (deckOfCards[0].value == currentCard.value) {
            Log.e("!!!", "false, ${deckOfCards[0].name} is the same value as ${currentCard.name}")
            correctGuess = false

        } else if (deckOfCards[0].value < currentCard.value) {

            if (guess == "higher") {
                Log.e("!!!", "false, ${deckOfCards[0].name} is lower than ${currentCard.name}")
                correctGuess = false
            } else if (guess == "lower") {
                Log.i("!!!", "true, ${deckOfCards[0].name} is lower than ${currentCard.name}")
                correctGuess = true
            }

        } else if (deckOfCards[0].value > currentCard.value) {

            if (guess == "higher") {
                Log.i("!!!", "true, ${deckOfCards[0].name} is lower than ${currentCard.name}")
                correctGuess = true
            } else if (guess == "lower") {
                Log.e("!!!", "false, ${deckOfCards[0].name} is lower than ${currentCard.name}")
                correctGuess = false
            }
        }
        return correctGuess
    }

    private fun updateDrawPile() {

        if (cardsLeft > 0) {
            cardsLeft--
        }

        if (cardsLeft < 2) {
            binding.drawPileBottomCard.visibility = View.GONE
        } else {
            binding.drawPileBottomCard.visibility = View.VISIBLE
        }
        if (cardsLeft == 0) {
            binding.drawPileTopCard.visibility = View.GONE
        } else {
            binding.drawPileTopCard.visibility = View.VISIBLE
        }

        binding.textViewCardCount.text = cardsLeft.toString()
    }

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
            Card("Ace of Hearts", 1,R.drawable.hearts_1),
            Card("2 of Hearts", 2,R.drawable.hearts_2),
            Card("3 of Hearts", 3,R.drawable.hearts_3),
            Card("4 of Hearts", 4,R.drawable.hearts_4),
            Card("5 of Hearts", 5,R.drawable.hearts_5),
            Card("6 of Hearts", 6,R.drawable.hearts_6),
            Card("7 of Hearts", 7,R.drawable.hearts_7),
            Card("8 of Hearts", 8,R.drawable.hearts_8),
            Card("9 of Hearts", 9,R.drawable.hearts_9),
            Card("10 of Hearts", 10,R.drawable.hearts_10),
            Card("Jack of Hearts", 11,R.drawable.hearts_jack),
            Card("Queen of Hearts", 12,R.drawable.hearts_queen),
            Card("King of Hearts", 13,R.drawable.hearts_king),
            Card("Ace of Spades", 1,R.drawable.spades_1),
            Card("2 of Spades", 2,R.drawable.spades_2),
            Card("3 of Spades", 3,R.drawable.spades_3),
            Card("4 of Spades", 4,R.drawable.spades_4),
            Card("5 of Spades", 5,R.drawable.spades_5),
            Card("6 of Spades", 6,R.drawable.spades_6),
            Card("7 of Spades", 7,R.drawable.spades_7),
            Card("8 of Spades", 8,R.drawable.spades_8),
            Card("9 of Spades", 9,R.drawable.spades_9),
            Card("10 of Spades", 10,R.drawable.spades_10),
            Card("Jack of Spades", 11,R.drawable.spades_jack),
            Card("Queen of Spades", 12,R.drawable.spades_queen),
            Card("King of Spades", 13,R.drawable.spades_king),
            Card("Ace of Clubs", 1, R.drawable.clubs_1),
            Card("2 of Clubs", 2, R.drawable.clubs_2),
            Card("3 of Clubs", 3, R.drawable.clubs_3),
            Card("4 of Clubs", 4, R.drawable.clubs_4),
            Card("5 of Clubs", 5, R.drawable.clubs_5),
            Card("6 of Clubs", 6, R.drawable.clubs_6),
            Card("7 of Clubs", 7, R.drawable.clubs_7),
            Card("8 of Clubs", 8, R.drawable.clubs_8),
            Card("9 of Clubs", 9, R.drawable.clubs_9),
            Card("10 of Clubs", 10, R.drawable.clubs_10),
            Card("Jack of Clubs", 11, R.drawable.clubs_jack),
            Card("Queen of Clubs", 12, R.drawable.clubs_queen),
            Card("King of Clubs", 13, R.drawable.clubs_king),
            Card("Ace of Diamonds", 1, R.drawable.diamonds_1),
            Card("2 of Diamonds", 2, R.drawable.diamonds_2),
            Card("2 of Diamonds", 3, R.drawable.diamonds_3),
            Card("2 of Diamonds", 4, R.drawable.diamonds_4),
            Card("2 of Diamonds", 5, R.drawable.diamonds_5),
            Card("2 of Diamonds", 6, R.drawable.diamonds_6),
            Card("2 of Diamonds", 7, R.drawable.diamonds_7),
            Card("2 of Diamonds", 8, R.drawable.diamonds_8),
            Card("2 of Diamonds", 9, R.drawable.diamonds_9),
            Card("2 of Diamonds", 10, R.drawable.diamonds_10),
            Card("Jack of Diamonds", 11, R.drawable.diamonds_jack),
            Card("Queen of Diamonds", 12, R.drawable.diamonds_queen),
            Card("King of Diamonds", 13, R.drawable.diamonds_king)
        )
        deckOfCards.shuffle()
    }

}