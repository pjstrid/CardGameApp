package com.example.cardgameapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.cardgameapp.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    private lateinit var deckOfCards: MutableList<String>

    private var cardCount = 0
    private var cardsLeft = 43
    private lateinit var currentPile : ImageView
    private var pileCount = 1
    private lateinit var currentCard: String
    private var currentCardResId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)

        setContentView(binding.root)

//      Creates a shuffled list of all the cards
        createShuffledDeck()

//      Creates a layout with a card on each pile
        createStartLayout()

//      Game starts
        currentPile = determinePile(pileCount)
        currentCard = deckOfCards[cardCount]
        currentCardResId = showCard(currentCard)

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


//      showCard(deckOfCards[cardCount])

        binding.buttonHigher.setOnClickListener {

        }

        binding.buttonLower.setOnClickListener {

        }
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
            "h1", "h2", "h3", "h4", "h5", "h6", "h7", "h8", "h9", "h10", "h11", "h12", "h13",
            "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "s12", "s13",
            "d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "d10", "d11", "d12", "d13",
            "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "c10", "c11", "c12", "c13"
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