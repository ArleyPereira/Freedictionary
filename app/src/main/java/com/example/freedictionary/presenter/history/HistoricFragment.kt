package com.example.freedictionary.presenter.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.freedictionary.databinding.FragmentHistoricBinding
import com.example.freedictionary.domain.model.WordRefFirebase
import com.example.freedictionary.presenter.adapters.FavoritesAndHistoricAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoricFragment : Fragment() {

    private var _binding: FragmentHistoricBinding? = null
    private val binding get() = _binding!!

    private val historicViewModel: HistoricViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoricBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getHistorics()
    }

    private fun getHistorics() {
        historicViewModel.historics.observe(viewLifecycleOwner) { historics ->
            initHistoricsAdapter(historics)
            binding.textEmpty.isVisible = historics.isEmpty()
        }
    }

    private fun initHistoricsAdapter(words: List<WordRefFirebase>) {
        with(binding.wordRecycler) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = FavoritesAndHistoricAdapter(words) { word ->
                val action = com.example.freedictionary.presenter.home.HomeFragmentDirections.actionHomeFragmentToDetailsFragment(word)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}