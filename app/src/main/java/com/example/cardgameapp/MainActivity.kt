package com.example.cardgameapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.cardgameapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    lateinit var currentPlayer : Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
//        binding.buttonStartGame.visibility = View.INVISIBLE

        currentPlayer = Player("")

        binding.buttonCreatePlayer.setOnClickListener {
            currentPlayer.name = binding.editTextCreatePlayer.editText?.text.toString().uppercase()

            binding.playerNameView.text = currentPlayer.name
            binding.buttonStartGame.visibility = View.VISIBLE
        }

        binding.buttonStartGame.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)

            startActivity(intent)
        }

    }
}