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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataManager.listOfScores = loadFromPrefs(requireContext())
    }

    // Sets the highscore with the top 10 results from the list
    override fun onResume() {
        super.onResume()

        var i = 0
        binding.buttonResetHighscore.setOnClickListener {
            clearPrefs(requireContext())
            DataManager.listOfScores.clear()

            while (i < 10) {
                when (i) {
                    0 -> {
                        binding.namePosition1.text = ""
                        binding.timePosition1.text = ""
                    }

                    1 -> {
                        binding.namePosition2.text = ""
                        binding.timePosition2.text = ""
                    }

                    2 -> {
                        binding.namePosition3.text = ""
                        binding.timePosition3.text = ""
                    }

                    3 -> {
                        binding.namePosition4.text = ""
                        binding.timePosition4.text = ""
                    }

                    4 -> {
                        binding.namePosition5.text = ""
                        binding.timePosition5.text = ""
                    }

                    5 -> {
                        binding.namePosition6.text = ""
                        binding.timePosition6.text = ""
                    }

                    6 -> {
                        binding.namePosition7.text = ""
                        binding.timePosition7.text = ""
                    }

                    7 -> {
                        binding.namePosition8.text = ""
                        binding.timePosition8.text = ""
                    }

                    8 -> {
                        binding.namePosition9.text = ""
                        binding.timePosition9.text = ""
                    }

                    9 -> {
                        binding.namePosition10.text = ""
                        binding.timePosition10.text = ""
                    }
                }
                i++
            }
        }

        val finalTop10List = DataManager.createTop10List()

        var j = 0
        while (j < finalTop10List.size && j < 10) {
            val minutes = finalTop10List[j]?.time?.div(60)
            val seconds = finalTop10List[j]?.time?.rem(60)

            var correctedTime = ""

            if (minutes != null) {
                if (seconds != null) {
                    correctedTime = if (minutes < 10 && seconds < 10) {
                        "0$minutes:0$seconds"

                    } else if (minutes < 10) {
                        "0$minutes:$seconds"

                    } else if (seconds < 10) {
                        "$minutes:0$seconds"

                    } else {
                        "$minutes:$seconds"
                    }
                }
            }

            if (finalTop10List.isNotEmpty()) {
                when (j) {
                    0 -> {
                        binding.namePosition1.text = finalTop10List[j]?.name
                        binding.timePosition1.text = correctedTime
                    }

                    1 -> {
                        binding.namePosition2.text = finalTop10List[j]?.name
                        binding.timePosition2.text = correctedTime
                    }

                    2 -> {
                        binding.namePosition3.text = finalTop10List[j]?.name
                        binding.timePosition3.text = correctedTime
                    }

                    3 -> {
                        binding.namePosition4.text = finalTop10List[j]?.name
                        binding.timePosition4.text = correctedTime
                    }

                    4 -> {
                        binding.namePosition5.text = finalTop10List[j]?.name
                        binding.timePosition5.text = correctedTime
                    }

                    5 -> {
                        binding.namePosition6.text = finalTop10List[j]?.name
                        binding.timePosition6.text = correctedTime
                    }

                    6 -> {
                        binding.namePosition7.text = finalTop10List[j]?.name
                        binding.timePosition7.text = correctedTime
                    }

                    7 -> {
                        binding.namePosition8.text = finalTop10List[j]?.name
                        binding.timePosition8.text = correctedTime
                    }

                    8 -> {
                        binding.namePosition9.text = finalTop10List[j]?.name
                        binding.timePosition9.text = correctedTime
                    }

                    9 -> {
                        binding.namePosition10.text = finalTop10List[j]?.name
                        binding.timePosition10.text = correctedTime
                    }
                }
            }
            j++
        }
    }

}