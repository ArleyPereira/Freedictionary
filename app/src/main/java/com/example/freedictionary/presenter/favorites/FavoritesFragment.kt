package com.example.freedictionary.presenter.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.freedictionary.databinding.FragmentFavoritesBinding
import com.example.freedictionary.domain.model.WordRefFirebase
import com.example.freedictionary.presenter.adapters.FavoritesAndHistoricAdapter
import com.example.freedictionary.presenter.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFavorites()
    }

    private fun getFavorites() {
        favoritesViewModel.favorites.observe(viewLifecycleOwner) { favorites ->
            initFavoritesAdapter(favorites)
            binding.textEmpty.isVisible = favorites.isEmpty()
        }
    }

    private fun initFavoritesAdapter(words: List<WordRefFirebase>) {
        with(binding.wordRecycler) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = FavoritesAndHistoricAdapter(words) { word ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(word)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}