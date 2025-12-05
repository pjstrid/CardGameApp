package com.example.cardgameapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cardgameapp.databinding.ActivityGameBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    private lateinit var currentPlayer: Player

    private var cardCount = 0
    private var cardsLeft = 52

    private var firstNineCardsList = mutableListOf<Card>()
    private lateinit var currentPile: ImageView
    private var pileCount = 1
    private lateinit var currentPileCard: Card
    private lateinit var theDrawCard: Card
    private var timer: Job? = null
    var timerCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)

        setContentView(binding.root)

//      Get player name from MainActivity and add to the Layout
        currentPlayer = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("currentPlayer", Player::class.java)!!
        } else {
            intent.getSerializableExtra("currentPlayer") as Player
        }
        binding.textViewCornerPlayerName.text = currentPlayer.name

//      Creates a shuffled deck of cards
        DataManager.createShuffledDeck()

//      To check the full deck
        Log.i("...", "==================")
        var i = 1
        while (i < 53) {
            Log.i("...", "card $i : ${DataManager.deckOfCards[i - 1].name}")
            i++
        }


//      Deals a card to each nine piles
        dealTheFirstNine()

//      Creates a layout with a card on each pile
        createStartLayout()
        binding.textViewCardCount.text = cardsLeft.toString()

        //  Determines pile one as the starting pile
        currentPile = determinePile(pileCount)

        //  Picks the first card from the list to be in the card in the starting pile
        currentPileCard = firstNineCardsList[0]
        theDrawCard = DataManager.deckOfCards[cardCount]

//      "Opens" the card on the current pile
        showCardOnPile()

        startTimer()

//      Game logic when pressing the "HIGHER-Button"
        binding.buttonHigher.setOnClickListener {
            val guess = "higher"
            gameplayLogic(guess)
        }

//      Game logic when pressing the "LOWER-Button"
        binding.buttonLower.setOnClickListener {
            val guess = "lower"
            gameplayLogic(guess)
        }

//      Restart the game with the same player when pressing the "RESTART GAME-Button"
        binding.buttonRestart.setOnClickListener {
            stopTimer()

            currentPlayer = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra("currentPlayer", Player::class.java)!!
            } else {
                intent.getSerializableExtra("currentPlayer") as Player
            }

            val restartIntent = Intent(this, GameActivity::class.java).apply {
                putExtra("currentPlayer", currentPlayer)
            }
            binding.textViewCornerPlayerName.text = currentPlayer.name

            startActivity(restartIntent)
            finish()
        }

//      Return to MainActivity / "HOME" when pressing the "HOME-Button"
        binding.buttonHome.setOnClickListener {
            stopTimer()
            finish()
        }
    }

    // Function for starting the timer of the game
    fun startTimer() {
        timer = lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                while (isActive) {
                    delay(1000)
                    timerCount++
                    updateTimerText()
                }
            }
        }
    }

    // Function for stopping the timer of the game
    fun stopTimer() {
        timer?.cancel()
    }

    // Function for updating the timer-text
    fun updateTimerText() {
        val minutes = timerCount / 60
        val seconds = timerCount % 60

        if (minutes < 10 && seconds < 10) {
            binding.textViewTimer.text = "0$minutes:0$seconds"

        } else if (minutes < 10) {
            binding.textViewTimer.text = "0$minutes:$seconds"

        } else if (seconds < 10) {
            binding.textViewTimer.text = "$minutes:0$seconds"

        } else if (minutes > 59) {
            binding.textViewTimer.text = getString(R.string.times_up_text)
            stopTimer()
            //  Hiding the HIGHER & LOWER buttons
            binding.buttonHigher.visibility = View.INVISIBLE
            binding.buttonLower.visibility = View.INVISIBLE
            showLoosingText()

        } else {
            binding.textViewTimer.text = "$minutes:$seconds"
        }
    }

    //  Gameplay logic to check guess and determine won or lost game
    private fun gameplayLogic(guess: String) {
        checkGuess(guess)
        updateDrawPile()

        if (cardCount < 52) {
            theDrawCard = DataManager.deckOfCards[cardCount]
        }

        //  Won or lost game
        if (cardsLeft == 0) {
            stopTimer()

            //  Hiding the HIGHER & LOWER buttons and the RESTART
            // to "force" the player to save the score
            binding.buttonHigher.visibility = View.INVISIBLE
            binding.buttonLower.visibility = View.INVISIBLE

            showWinningText()

            currentPlayer.time = timerCount

            DataManager.addToHighscore(currentPlayer)
            saveToPrefs(this, DataManager.listOfScores)

        } else if (pileCount == 10) {
            stopTimer()
            //  Hiding the HIGHER & LOWER buttons
            binding.buttonHigher.visibility = View.INVISIBLE
            binding.buttonLower.visibility = View.INVISIBLE
            showLoosingText()
        }
    }

    //  Function for showing the "YOU LOSE"-text when game is lost
    private fun showLoosingText() {
        binding.resultCardView.visibility = View.VISIBLE

        binding.resultView.setBackgroundColor(resources.getColor(R.color.red, theme))
        binding.resultView.text = getString(R.string.losing_text)
    }

    //  Function for showing the "YOU WON"-text when game is lost
    private fun showWinningText() {
        binding.resultCardView.visibility = View.VISIBLE

        binding.resultView.setBackgroundColor(resources.getColor(R.color.blue, theme))
        binding.resultView.text = getString(R.string.winning_text)
    }

    //  Function for dealing the first nine card of the deck to the piles
    fun dealTheFirstNine() {
        var dealingPilesCount = 1

        while (dealingPilesCount < 10) {
            firstNineCardsList.add(DataManager.deckOfCards[cardCount])

            cardCount++
            dealingPilesCount++
            cardsLeft--
        }
    }

    //  Function for checking if a guess: Higher or Lower guess, is correct or not
    private fun checkGuess(guess: String) {

        val correctGuess = compareCards(guess)

        if (correctGuess) {
            //  Moving the card from the draw pile to the current pile to be played against next
            currentPileCard = theDrawCard

            //  "Open" the card on the current pile
            showCardOnPile()
        } else {
            //  Changing pile to the next one
            currentPile = determinePile(pileCount)

            //  Show the card from the draw pile on the lost pile
            currentPile.setImageResource(theDrawCard.resId)
            when (pileCount) {
                1 -> binding.textViewOneX.visibility = View.VISIBLE
                2 -> binding.textViewTwoX.visibility = View.VISIBLE
                3 -> binding.textViewThreeX.visibility = View.VISIBLE
                4 -> binding.textViewFourX.visibility = View.VISIBLE
                5 -> binding.textViewFiveX.visibility = View.VISIBLE
                6 -> binding.textViewSixX.visibility = View.VISIBLE
                7 -> binding.textViewSevenX.visibility = View.VISIBLE
                8 -> binding.textViewEightX.visibility = View.VISIBLE
                9 -> binding.textViewNineX.visibility = View.VISIBLE
            }
            binding.textViewOneX.visibility = View.VISIBLE

            //  Sets current card to the next card in the list (and the next pile)
            pileCount++
            when (pileCount) {
                1 -> currentPileCard = firstNineCardsList[0]
                2 -> currentPileCard = firstNineCardsList[1]
                3 -> currentPileCard = firstNineCardsList[2]
                4 -> currentPileCard = firstNineCardsList[3]
                5 -> currentPileCard = firstNineCardsList[4]
                6 -> currentPileCard = firstNineCardsList[5]
                7 -> currentPileCard = firstNineCardsList[6]
                8 -> currentPileCard = firstNineCardsList[7]
                9 -> currentPileCard = firstNineCardsList[8]
            }
            //  "Open" the card on the current pile

            showCardOnPile()

            //  Hiding the HIGHER & LOWER buttons
            binding.buttonHigher.visibility = View.INVISIBLE
            binding.buttonLower.visibility = View.INVISIBLE

            //  Shows the HIGHER & LOWER buttons again after a delay
            lifecycleScope.launch {
                delay(1500)
                closePile(pileCount)

                if (pileCount < 10 && cardsLeft != 0) {
                    binding.buttonHigher.visibility = View.VISIBLE
                    binding.buttonLower.visibility = View.VISIBLE
                }
            }
        }
        //  Plus one on card count to know how many cards played
        cardCount++
    }

    //  Function for "closing" a pile if incorrect guess (set image to backside of card)
    private fun closePile(currentPileCount: Int) {
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

    //  Function for "opening" a pile by showing the current cards image on that pile
    private fun showCardOnPile() {

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

    //  Function for comparing the card shown on the current pile against the card on top of the deck
    private fun compareCards(guess: String): Boolean {
        var correctGuess = false

        if (currentPileCard.value == 1 || theDrawCard.value == 1) {
            correctGuess = true

        } else if (theDrawCard.value == currentPileCard.value) {
            correctGuess = false

        } else if (theDrawCard.value < currentPileCard.value) {
            if (guess == "higher") {
                correctGuess = false

            } else if (guess == "lower") {
                correctGuess = true
            }
        } else { //  if (theDrawCard.value > currentPileCard.value)

            if (guess == "higher") {
                correctGuess = true

            } else if (guess == "lower") {
                correctGuess = false
            }
        }
        return correctGuess
    }

    //  Function for updating the draw pile visibly and the counter
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

    //  Function for determine which pile is the current playing pile
    private fun determinePile(pile: Int): ImageView {
        lateinit var currentPile: ImageView

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

    //  Function for setting the backside of the card as a start on each pile
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

}