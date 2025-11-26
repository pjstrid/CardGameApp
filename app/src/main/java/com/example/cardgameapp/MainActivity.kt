package com.example.cardgameapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cardgameapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    lateinit var currentPlayer : Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        currentPlayer = Player("")

        binding.buttonCreatePlayer.setOnClickListener {
            currentPlayer.name = binding.editTextCreatePlayer.editText?.text.toString()

            binding.playerNameView.text = currentPlayer.name
        }

        binding.buttonStartGame.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)

            startActivity(intent)
        }

    }
}