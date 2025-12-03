package com.example.cardgameapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cardgameapp.databinding.FragmentHighscoreBinding

class HighscoreFragment : Fragment() {

    lateinit var binding : FragmentHighscoreBinding


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

        DataManager.top10List.sortBy { it?.time }

        if (DataManager.top10List.isNotEmpty()) {
            binding.namePosition1.text = DataManager.top10List[0]?.name

            val minutes = DataManager.top10List[0]?.time?.div(60)
            val seconds = DataManager.top10List[0]?.time?.rem(60)

            if (minutes != null) {
                if (seconds != null) {
                    if (minutes < 10 && seconds < 10) {
                        binding.timePosition1.text = "0$minutes:0$seconds"

                    } else if (minutes < 10) {
                        binding.timePosition1.text = "0$minutes:$seconds"

                    } else if (seconds < 10) {
                        binding.timePosition1.text = "$minutes:0$seconds"

                    } else {
                        binding.timePosition1.text = "${minutes}:${seconds}"
                    }
                }
            }
        }
        if (DataManager.top10List.size > 1 ) {
            binding.namePosition2.text = DataManager.top10List[1]?.name

            val minutes = DataManager.top10List[1]?.time?.div(60)
            val seconds = DataManager.top10List[1]?.time?.rem(60)

            if (minutes != null) {
                if (seconds != null) {
                    if (minutes < 10 && seconds < 10) {
                        binding.timePosition2.text = "0$minutes:0$seconds"

                    } else if (minutes < 10) {
                        binding.timePosition2.text = "0$minutes:$seconds"

                    } else if (seconds < 10) {
                        binding.timePosition2.text = "$minutes:0$seconds"

                    } else {
                        binding.timePosition2.text = "${minutes}:${seconds}"
                    }
                }
            }
        }
        if (DataManager.top10List.size > 2 ) {
            binding.namePosition3.text = DataManager.top10List[2]?.name

            val minutes = DataManager.top10List[2]?.time?.div(60)
            val seconds = DataManager.top10List[2]?.time?.rem(60)

            if (minutes != null) {
                if (seconds != null) {
                    if (minutes < 10 && seconds < 10) {
                        binding.timePosition3.text = "0$minutes:0$seconds"

                    } else if (minutes < 10) {
                        binding.timePosition3.text = "0$minutes:$seconds"

                    } else if (seconds < 10) {
                        binding.timePosition3.text = "$minutes:0$seconds"

                    } else {
                        binding.timePosition3.text = "${minutes}:${seconds}"
                    }
                }
            }
        }
        if (DataManager.top10List.size > 3 ) {
            binding.namePosition4.text = DataManager.top10List[3]?.name

            val minutes = DataManager.top10List[3]?.time?.div(60)
            val seconds = DataManager.top10List[3]?.time?.rem(60)

            if (minutes != null) {
                if (seconds != null) {
                    if (minutes < 10 && seconds < 10) {
                        binding.timePosition4.text = "0$minutes:0$seconds"

                    } else if (minutes < 10) {
                        binding.timePosition4.text = "0$minutes:$seconds"

                    } else if (seconds < 10) {
                        binding.timePosition4.text = "$minutes:0$seconds"

                    } else {
                        binding.timePosition4.text = "${minutes}:${seconds}"
                    }
                }
            }
        }
        if (DataManager.top10List.size > 4 ) {
            binding.namePosition5.text = DataManager.top10List[4]?.name

            val minutes = DataManager.top10List[5]?.time?.div(60)
            val seconds = DataManager.top10List[4]?.time?.rem(60)

            if (minutes != null) {
                if (seconds != null) {
                    if (minutes < 10 && seconds < 10) {
                        binding.timePosition5.text = "0$minutes:0$seconds"

                    } else if (minutes < 10) {
                        binding.timePosition5.text = "0$minutes:$seconds"

                    } else if (seconds < 10) {
                        binding.timePosition5.text = "$minutes:0$seconds"

                    } else {
                        binding.timePosition5.text = "${minutes}:${seconds}"
                    }
                }
            }
        }
        if (DataManager.top10List.size > 5 ) {
            binding.namePosition6.text = DataManager.top10List[5]?.name

            val minutes = DataManager.top10List[5]?.time?.div(60)
            val seconds = DataManager.top10List[5]?.time?.rem(60)

            if (minutes != null) {
                if (seconds != null) {
                    if (minutes < 10 && seconds < 10) {
                        binding.timePosition6.text = "0$minutes:0$seconds"

                    } else if (minutes < 10) {
                        binding.timePosition6.text = "0$minutes:$seconds"

                    } else if (seconds < 10) {
                        binding.timePosition6.text = "$minutes:0$seconds"

                    } else {
                        binding.timePosition6.text = "${minutes}:${seconds}"
                    }
                }
            }
        }
        if (DataManager.top10List.size > 6 ) {
            binding.namePosition7.text = DataManager.top10List[6]?.name

            val minutes = DataManager.top10List[6]?.time?.div(60)
            val seconds = DataManager.top10List[6]?.time?.rem(60)

            if (minutes != null) {
                if (seconds != null) {
                    if (minutes < 10 && seconds < 10) {
                        binding.timePosition7.text = "0$minutes:0$seconds"

                    } else if (minutes < 10) {
                        binding.timePosition7.text = "0$minutes:$seconds"

                    } else if (seconds < 10) {
                        binding.timePosition7.text = "$minutes:0$seconds"

                    } else {
                        binding.timePosition7.text = "${minutes}:${seconds}"
                    }
                }
            }
        }
        if (DataManager.top10List.size > 7 ) {
            binding.namePosition8.text = DataManager.top10List[7]?.name

            val minutes = DataManager.top10List[7]?.time?.div(60)
            val seconds = DataManager.top10List[7]?.time?.rem(60)

            if (minutes != null) {
                if (seconds != null) {
                    if (minutes < 10 && seconds < 10) {
                        binding.timePosition8.text = "0$minutes:0$seconds"

                    } else if (minutes < 10) {
                        binding.timePosition8.text = "0$minutes:$seconds"

                    } else if (seconds < 10) {
                        binding.timePosition8.text = "$minutes:0$seconds"

                    } else {
                        binding.timePosition8.text = "${minutes}:${seconds}"
                    }
                }
            }
        }
        if (DataManager.top10List.size > 8 ) {
            binding.namePosition9.text = DataManager.top10List[8]?.name

            val minutes = DataManager.top10List[8]?.time?.div(60)
            val seconds = DataManager.top10List[8]?.time?.rem(60)

            if (minutes != null) {
                if (seconds != null) {
                    if (minutes < 10 && seconds < 10) {
                        binding.timePosition9.text = "0$minutes:0$seconds"

                    } else if (minutes < 10) {
                        binding.timePosition9.text = "0$minutes:$seconds"

                    } else if (seconds < 10) {
                        binding.timePosition9.text = "$minutes:0$seconds"

                    } else {
                        binding.timePosition9.text = "${minutes}:${seconds}"
                    }
                }
            }
        }
        if (DataManager.top10List.size > 9 ) {
            binding.namePosition10.text = DataManager.top10List[9]?.name

            val minutes = DataManager.top10List[9]?.time?.div(60)
            val seconds = DataManager.top10List[9]?.time?.rem(60)

            if (minutes != null) {
                if (seconds != null) {
                    if (minutes < 10 && seconds < 10) {
                        binding.timePosition10.text = "0$minutes:0$seconds"

                    } else if (minutes < 10) {
                        binding.timePosition10.text = "0$minutes:$seconds"

                    } else if (seconds < 10) {
                        binding.timePosition10.text = "$minutes:0$seconds"

                    } else {
                        binding.timePosition10.text = "${minutes}:${seconds}"
                    }
                }
            }
        }
    }

}