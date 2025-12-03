package com.example.cardgameapp

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cardgameapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding


    private val editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == RESULT_OK) {

            val updatedPlayer = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getSerializableExtra("updatedPlayer", Player::class.java)
            } else {
                result.data?.getSerializableExtra("updatedPlayer") as Player
            }

            if (updatedPlayer != null) {
                DataManager.currentPlayer = updatedPlayer
            }
            DataManager.setHighscore(DataManager.currentPlayer)

        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

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

                binding.playerNameView.text = DataManager.currentPlayer.name
                binding.buttonStartGame.visibility = View.VISIBLE
            }
        }

        binding.buttonStartGame.setOnClickListener {
            val intent = Intent(activity, GameActivity::class.java)
            intent.putExtra("currentPlayer", DataManager.currentPlayer)
            editLauncher.launch(intent)
        }
    }


}