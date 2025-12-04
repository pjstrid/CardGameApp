package com.example.cardgameapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cardgameapp.databinding.FragmentHighscoreBinding

class HighscoreFragment : Fragment() {
    lateinit var binding: FragmentHighscoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHighscoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Sets the highscore with the top 10 results from the list
    override fun onResume() {
        super.onResume()

        val finalTop10List = DataManager.createTop10List()

        var i = 0
        while (i < finalTop10List.size && i < 10) {
            val minutes = finalTop10List[i]?.time?.div(60)
            val seconds = finalTop10List[i]?.time?.rem(60)

            var correctedTime = ""

            if (minutes != null) {
                if (seconds != null) {
                    if (minutes < 10 && seconds < 10) {
                        correctedTime = "0$minutes:0$seconds"

                    } else if (minutes < 10) {
                        correctedTime = "0$minutes:$seconds"

                    } else if (seconds < 10) {
                        correctedTime = "$minutes:0$seconds"

                    } else {
                        correctedTime = "$minutes:$seconds"
                    }
                }
            }

            if (finalTop10List.isNotEmpty()) {
                when (i) {
                    0 -> {
                        binding.namePosition1.text = finalTop10List[i]?.name
                        binding.timePosition1.text = correctedTime
                    }

                    1 -> {
                        binding.namePosition2.text = finalTop10List[i]?.name
                        binding.timePosition2.text = correctedTime
                    }

                    2 -> {
                        binding.namePosition3.text = finalTop10List[i]?.name
                        binding.timePosition3.text = correctedTime
                    }

                    3 -> {
                        binding.namePosition4.text = finalTop10List[i]?.name
                        binding.timePosition4.text = correctedTime
                    }

                    4 -> {
                        binding.namePosition5.text = finalTop10List[i]?.name
                        binding.timePosition5.text = correctedTime
                    }

                    5 -> {
                        binding.namePosition6.text = finalTop10List[i]?.name
                        binding.timePosition6.text = correctedTime
                    }

                    6 -> {
                        binding.namePosition7.text = finalTop10List[i]?.name
                        binding.timePosition7.text = correctedTime
                    }

                    7 -> {
                        binding.namePosition8.text = finalTop10List[i]?.name
                        binding.timePosition8.text = correctedTime
                    }

                    8 -> {
                        binding.namePosition9.text = finalTop10List[i]?.name
                        binding.timePosition9.text = correctedTime
                    }

                    9 -> {
                        binding.namePosition10.text = finalTop10List[i]?.name
                        binding.timePosition10.text = correctedTime
                    }
                }
            }
            i++
        }
    }

}