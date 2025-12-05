package com.example.cardgameapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cardgameapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStartGame.visibility = View.INVISIBLE


        binding.buttonCreatePlayer.setOnClickListener {
            val input = binding.editTextCreatePlayer.editText?.text.toString()

            if (input.length < 2) {
                binding.editTextCreatePlayer.error = getString(R.string.edit_text_error_msg)
            } else {
                binding.editTextCreatePlayer.error = null

                DataManager.currentPlayer = Player(input.uppercase(), 0)

                binding.playerNameHeader.visibility = View.VISIBLE
                binding.playerNameView.text = DataManager.currentPlayer.name
                binding.buttonStartGame.visibility = View.VISIBLE
            }
        }

        binding.buttonStartGame.setOnClickListener {
            val intent = Intent(activity, GameActivity::class.java)
            intent.putExtra("currentPlayer", DataManager.currentPlayer)
            startActivity(intent)
        }
    }

}