package com.example.cardgameapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// Functions to save and load the highscore list

fun saveToPrefs(context: Context, players: List<Player>) {
    val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val editor = prefs.edit()

    val gson = Gson()
    val json = gson.toJson(players)

    editor.putString("players_key", json)
    editor.apply()
}


fun loadFromPrefs(context: Context) : List<Player> {
    val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val gson = Gson()

    val json = prefs.getString("players_key", null)
    val type = object: TypeToken<List<Player>>() {}.type

    val highScoreList: List<Player> = gson.fromJson(json, type)

    return highScoreList
}

