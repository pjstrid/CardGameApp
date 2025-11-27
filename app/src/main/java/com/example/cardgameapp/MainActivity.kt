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
        binding.buttonStartGame.visibility = View.INVISIBLE

        currentPlayer = Player("")

        binding.buttonCreatePlayer.setOnClickListener {
            val input = binding.editTextCreatePlayer.editText?.text.toString()
            
            if (input.length < 2) {
                binding.editTextCreatePlayer.error = getString(R.string.edit_text_error_msg)
            } else {
                binding.editTextCreatePlayer.error = null

                currentPlayer.name = input.uppercase()

                binding.playerNameView.text = currentPlayer.name
                binding.buttonStartGame.visibility = View.VISIBLE
            }
        }

        binding.buttonStartGame.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("currentPlayer", currentPlayer.name)
            startActivity(intent)
        }

    }
}