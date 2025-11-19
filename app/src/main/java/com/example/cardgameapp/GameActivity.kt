package com.example.cardgameapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.cardgameapp.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding

    private lateinit var deckOfCards: MutableList<String>
    private var count = 0
    private var cardsLeft = 52

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)

        setContentView(binding.root)

        createShuffledDeck()

        binding.picDrawPileTopCard.setImageResource(R.drawable.backside_card)
        binding.picBottomOfDrawPile.setImageResource(R.drawable.backside_card)


        binding.drawPile.setOnClickListener {

            if (cardsLeft > 0) {
                cardsLeft--
            }

            binding.textViewCardCount.text = cardsLeft.toString()
            if (count < deckOfCards.size) {
                showCard(deckOfCards[count])
            } else {
                count = 0
                cardsLeft = 52
                showCard(deckOfCards[count])
            }
            count++

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
        }



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

    private fun showCard(card : String) {
        val i = when (card) {
            "h1" -> R.drawable.hearts_1
            "h2" -> R.drawable.hearts_2
            "h3" -> R.drawable.hearts_3
            "h4" -> R.drawable.hearts_4
            "h5" -> R.drawable.hearts_5
            "h6" -> R.drawable.hearts_6
            "h7" -> R.drawable.hearts_7
            "h8" -> R.drawable.hearts_8
            "h9" -> R.drawable.hearts_9
            "h10" -> R.drawable.hearts_10
            "h11" -> R.drawable.hearts_jack
            "h12" -> R.drawable.hearts_queen
            "h13" -> R.drawable.hearts_king
            "s1" -> R.drawable.spades_1
            "s2" -> R.drawable.spades_2
            "s3" -> R.drawable.spades_3
            "s4" -> R.drawable.spades_4
            "s5" -> R.drawable.spades_5
            "s6" -> R.drawable.spades_6
            "s7" -> R.drawable.spades_7
            "s8" -> R.drawable.spades_8
            "s9" -> R.drawable.spades_9
            "s10" -> R.drawable.spades_10
            "s11" -> R.drawable.spades_jack
            "s12" -> R.drawable.spades_queen
            "s13" -> R.drawable.spades_king
            "d1" -> R.drawable.diamonds_1
            "d2" -> R.drawable.diamonds_2
            "d3" -> R.drawable.diamonds_3
            "d4" -> R.drawable.diamonds_4
            "d5" -> R.drawable.diamonds_5
            "d6" -> R.drawable.diamonds_6
            "d7" -> R.drawable.diamonds_7
            "d8" -> R.drawable.diamonds_8
            "d9" -> R.drawable.diamonds_9
            "d10" -> R.drawable.diamonds_10
            "d11" -> R.drawable.diamonds_jack
            "d12" -> R.drawable.diamonds_queen
            "d13" -> R.drawable.diamonds_king
            "c1" -> R.drawable.clubs_1
            "c2" -> R.drawable.clubs_2
            "c3" -> R.drawable.clubs_3
            "c4" -> R.drawable.clubs_4
            "c5" -> R.drawable.clubs_5
            "c6" -> R.drawable.clubs_6
            "c7" -> R.drawable.clubs_7
            "c8" -> R.drawable.clubs_8
            "c9" -> R.drawable.clubs_9
            "c10" -> R.drawable.clubs_10
            "c11" -> R.drawable.clubs_jack
            "c12" -> R.drawable.clubs_queen
            "c13" -> R.drawable.clubs_king
            else -> R.drawable.backside_card
        }
        binding.picDrawPileTopCard.setImageResource(i)
    }
}