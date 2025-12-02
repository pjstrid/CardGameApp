package com.example.cardgameapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cardgameapp.databinding.FragmentHighscoreBinding
import com.example.cardgameapp.databinding.FragmentHomeBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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

    override fun onResume() {
        super.onResume()

        if (DataManager.top10List.isNotEmpty()) {
            binding.namePosition1.text = DataManager.top10List[0]?.name
            binding.timePosition1.text = DataManager.top10List[0]?.time
        }
        if (DataManager.top10List.size > 1 ) {
            binding.namePosition2.text = DataManager.top10List[1]?.name
            binding.timePosition2.text = DataManager.top10List[1]?.time
        }
        if (DataManager.top10List.size > 2 ) {
            binding.namePosition3.text = DataManager.top10List[2]?.name
            binding.timePosition3.text = DataManager.top10List[2]?.time
        }
        if (DataManager.top10List.size > 3 ) {
            binding.namePosition4.text = DataManager.top10List[3]?.name
            binding.timePosition4.text = DataManager.top10List[3]?.time
        }
        if (DataManager.top10List.size > 4 ) {
            binding.namePosition5.text = DataManager.top10List[4]?.name
            binding.timePosition5.text = DataManager.top10List[4]?.time
        }
        if (DataManager.top10List.size > 5 ) {
            binding.namePosition6.text = DataManager.top10List[5]?.name
            binding.timePosition6.text = DataManager.top10List[5]?.time
        }
        if (DataManager.top10List.size > 6 ) {
            binding.namePosition7.text = DataManager.top10List[6]?.name
            binding.timePosition7.text = DataManager.top10List[6]?.time
        }
        if (DataManager.top10List.size > 7 ) {
            binding.namePosition8.text = DataManager.top10List[7]?.name
            binding.timePosition8.text = DataManager.top10List[7]?.time
        }
        if (DataManager.top10List.size > 8 ) {
            binding.namePosition9.text = DataManager.top10List[8]?.name
            binding.timePosition9.text = DataManager.top10List[8]?.time
        }
        if (DataManager.top10List.size > 9 ) {
            binding.namePosition10.text = DataManager.top10List[9]?.name
            binding.timePosition10.text = DataManager.top10List[9]?.time
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HighscoreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            HighscoreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}