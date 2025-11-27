package com.example.cardgameapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cardgameapp.databinding.ActivityGameBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    private lateinit var currentPlayerName: String

    private lateinit var deckOfCards: MutableList<Card>

    private var cardCount = 0
    private var cardsLeft = 43

    private var pileOneList = mutableListOf<Card>()
    private var pileTwoList = mutableListOf<Card>()
    private var pileThreeList = mutableListOf<Card>()
    private var pileFourList = mutableListOf<Card>()
    private var pileFiveList = mutableListOf<Card>()
    private var pileSixList = mutableListOf<Card>()
    private var pileSevenList = mutableListOf<Card>()
    private var pileEightList = mutableListOf<Card>()
    private var pileNineList = mutableListOf<Card>()
    private lateinit var currentPile : ImageView
    private var pileCount = 1
    private lateinit var currentPileCard: Card
    private lateinit var theDrawCard: Card


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)

        setContentView(binding.root)

        currentPlayerName = intent.getStringExtra("currentPlayer").toString()
        binding.textViewCornerPlayerName.text = currentPlayerName

//      Creates a shuffled list of all the cards
        createShuffledDeck()

//      Deals a card to each nine piles
        dealTheFirstNine()

//      Creates a layout with a card on each pile
        createStartLayout()
        binding.textViewCardCount.text = cardsLeft.toString()

//      Game starts
        currentPile = determinePile(pileCount)
        currentPileCard = pileOneList[0]
        theDrawCard = deckOfCards[cardCount]

        Log.i("!!!", pileOneList[0].name)
        Log.i("!!!", pileTwoList[0].name)
        Log.i("!!!", pileThreeList[0].name)
        Log.i("!!!", pileFourList[0].name)
        Log.i("!!!", pileFiveList[0].name)
        Log.i("!!!", pileSixList[0].name)
        Log.i("!!!", pileSevenList[0].name)
        Log.i("!!!", pileEightList[0].name)
        Log.i("!!!", pileNineList[0].name)

        val numOfCardsInDeck = deckOfCards.size - cardCount
        Log.i("!!!", numOfCardsInDeck.toString())

        openPile()

        binding.buttonHigher.setOnClickListener {

            val guess = "higher"
            checkCorrectGuess(guess)
            updateDrawPile()

            if (cardCount < 52) {
                theDrawCard = deckOfCards[cardCount]
            }

            if (cardsLeft == 0) {
                Toast.makeText(this, "You won!", Toast.LENGTH_LONG).show()
            } else if (pileCount == 10) {
                Toast.makeText(this, "You lost!", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonLower.setOnClickListener {

            val guess = "lower"
            checkCorrectGuess(guess)
            updateDrawPile()

            if (cardCount < 52) {
                theDrawCard = deckOfCards[cardCount]
            }

            if (cardsLeft == 0) {
                Toast.makeText(this, "You won!", Toast.LENGTH_LONG).show()
            } else if (pileCount == 10) {
                Toast.makeText(this, "You lost!", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonHome.setOnClickListener {
            finish()
        }
    }


    fun dealTheFirstNine() {
        var dealingPilesCount = 1

        while (dealingPilesCount < 10) {
            when (dealingPilesCount) {
                1 -> pileOneList.add(deckOfCards[cardCount])
                2 -> pileTwoList.add(deckOfCards[cardCount])
                3 -> pileThreeList.add(deckOfCards[cardCount])
                4 -> pileFourList.add(deckOfCards[cardCount])
                5 -> pileFiveList.add(deckOfCards[cardCount])
                6 -> pileSixList.add(deckOfCards[cardCount])
                7 -> pileSevenList.add(deckOfCards[cardCount])
                8 -> pileEightList.add(deckOfCards[cardCount])
                9 -> pileNineList.add(deckOfCards[cardCount])
            }
            cardCount++
            dealingPilesCount++
        }
    }

    private fun checkCorrectGuess(guess: String) {

        val correctGuess = checkGuess(guess)
        if (correctGuess) {
            currentPileCard = theDrawCard
            openPile()
        } else {
            currentPile = determinePile(pileCount)
            currentPile.setImageResource(theDrawCard.resId)
            pileCount++
            when (pileCount) {
                1 -> currentPileCard = pileOneList[0]
                2 -> currentPileCard = pileTwoList[0]
                3 -> currentPileCard = pileThreeList[0]
                4 -> currentPileCard = pileFourList[0]
                5 -> currentPileCard = pileFiveList[0]
                6 -> currentPileCard = pileSixList[0]
                7 -> currentPileCard = pileSevenList[0]
                8 -> currentPileCard = pileEightList[0]
                9 -> currentPileCard = pileNineList[0]
            }
            openPile()


                binding.buttonHigher.visibility = View.INVISIBLE
                binding.buttonLower.visibility = View.INVISIBLE


            lifecycleScope.launch {
                delay(1000)
                closePile(pileCount)

                if(pileCount < 10) {
                    binding.buttonHigher.visibility = View.VISIBLE
                    binding.buttonLower.visibility = View.VISIBLE
                }
            }
        }

        cardCount++
    }

    private fun closePile(currentPileCount : Int) {
        val previousPile = currentPileCount - 1

        when (previousPile) {
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
            1 -> binding.imageViewOne.setImageResource(currentPileCard.resId)
            2 -> binding.imageViewTwo.setImageResource(currentPileCard.resId)
            3 -> binding.imageViewThree.setImageResource(currentPileCard.resId)
            4 -> binding.imageViewFour.setImageResource(currentPileCard.resId)
            5 -> binding.imageViewFive.setImageResource(currentPileCard.resId)
            6 -> binding.imageViewSix.setImageResource(currentPileCard.resId)
            7 -> binding.imageViewSeven.setImageResource(currentPileCard.resId)
            8 -> binding.imageViewEight.setImageResource(currentPileCard.resId)
            9 -> binding.imageViewNine.setImageResource(currentPileCard.resId)
        }
    }

    private fun checkGuess(guess: String) : Boolean {
        var correctGuess = false

        if (currentPileCard.value == 1) {

            if (theDrawCard.value == 1) {
                Log.e("!!!", "false, ${theDrawCard.name} is the same value as ${currentPileCard.name} ")
                correctGuess = false
            } else {
                Log.i("!!!", "true, ${theDrawCard.name} is higher or lower than ${currentPileCard.name}")
                correctGuess = true
            }

        } else if (theDrawCard.value == 1) {

            Log.i("!!!", "true, ${theDrawCard.name} is higher or lower than ${currentPileCard.name}")
            correctGuess = true

        } else if (theDrawCard.value == currentPileCard.value) {
            Log.e("!!!", "false, ${theDrawCard.name} is the same value as ${currentPileCard.name}")
            correctGuess = false

        } else if (theDrawCard.value < currentPileCard.value) {

            if (guess == "higher") {
                Log.e("!!!", "false, ${theDrawCard.name} is lower than ${currentPileCard.name}")
                correctGuess = false
            } else if (guess == "lower") {
                Log.i("!!!", "true, ${theDrawCard.name} is lower than ${currentPileCard.name}")
                correctGuess = true
            }

        } else {//  if (theDrawCard.value > currentPileCard.value)

            if (guess == "higher") {
                Log.i("!!!", "true, ${theDrawCard.name} is higher than ${currentPileCard.name}")
                correctGuess = true
            } else if (guess == "lower") {
                Log.e("!!!", "false, ${theDrawCard.name} is higher than ${currentPileCard.name}")
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
            2 -> currentPile = binding.imageViewTwo
            3 -> currentPile = binding.imageViewThree
            4 -> currentPile = binding.imageViewFour
            5 -> currentPile = binding.imageViewFive
            6 -> currentPile = binding.imageViewSix
            7 -> currentPile = binding.imageViewSeven
            8 -> currentPile = binding.imageViewEight
            9 -> currentPile = binding.imageViewNine
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
            Card("3 of Diamonds", 3, R.drawable.diamonds_3),
            Card("4 of Diamonds", 4, R.drawable.diamonds_4),
            Card("5 of Diamonds", 5, R.drawable.diamonds_5),
            Card("6 of Diamonds", 6, R.drawable.diamonds_6),
            Card("7 of Diamonds", 7, R.drawable.diamonds_7),
            Card("8 of Diamonds", 8, R.drawable.diamonds_8),
            Card("9 of Diamonds", 9, R.drawable.diamonds_9),
            Card("10 of Diamonds", 10, R.drawable.diamonds_10),
            Card("Jack of Diamonds", 11, R.drawable.diamonds_jack),
            Card("Queen of Diamonds", 12, R.drawable.diamonds_queen),
            Card("King of Diamonds", 13, R.drawable.diamonds_king)
        )
        deckOfCards.shuffle()
    }

}