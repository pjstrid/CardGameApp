package com.example.cardgameapp

object DataManager {

    lateinit var deckOfCards: MutableList<Card>

    lateinit var currentPlayer: Player

    var top10List: MutableList<Player?> = mutableListOf()

    //  Function for creating the deck as a list and shuffle it straight away
    fun setHighscore(player: Player) {
        top10List.add(player)
    }

    //  Function for creating the deck as a list and shuffle it straight away
    fun createShuffledDeck() {
        deckOfCards = mutableListOf(
            Card("Ace of Hearts", 1, R.drawable.hearts_1),
            Card("2 of Hearts", 2, R.drawable.hearts_2),
            Card("3 of Hearts", 3, R.drawable.hearts_3),
            Card("4 of Hearts", 4, R.drawable.hearts_4),
            Card("5 of Hearts", 5, R.drawable.hearts_5),
            Card("6 of Hearts", 6, R.drawable.hearts_6),
            Card("7 of Hearts", 7, R.drawable.hearts_7),
            Card("8 of Hearts", 8, R.drawable.hearts_8),
            Card("9 of Hearts", 9, R.drawable.hearts_9),
            Card("10 of Hearts", 10, R.drawable.hearts_10),
            Card("Jack of Hearts", 11, R.drawable.hearts_jack),
            Card("Queen of Hearts", 12, R.drawable.hearts_queen),
            Card("King of Hearts", 13, R.drawable.hearts_king),
            Card("Ace of Spades", 1, R.drawable.spades_1),
            Card("2 of Spades", 2, R.drawable.spades_2),
            Card("3 of Spades", 3, R.drawable.spades_3),
            Card("4 of Spades", 4, R.drawable.spades_4),
            Card("5 of Spades", 5, R.drawable.spades_5),
            Card("6 of Spades", 6, R.drawable.spades_6),
            Card("7 of Spades", 7, R.drawable.spades_7),
            Card("8 of Spades", 8, R.drawable.spades_8),
            Card("9 of Spades", 9, R.drawable.spades_9),
            Card("10 of Spades", 10, R.drawable.spades_10),
            Card("Jack of Spades", 11, R.drawable.spades_jack),
            Card("Queen of Spades", 12, R.drawable.spades_queen),
            Card("King of Spades", 13, R.drawable.spades_king),
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