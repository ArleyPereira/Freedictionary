package com.example.freedictionary.presenter.words

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.freedictionary.databinding.FragmentWordsBinding
import com.example.freedictionary.domain.model.WordRefFirebase
import com.example.freedictionary.presenter.home.HomeFragmentDirections
import com.example.freedictionary.presenter.paging.MainLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordsFragment : Fragment() {

    private var _binding: FragmentWordsBinding? = null
    private val binding get() = _binding!!

    private val wordsViewModel: WordsViewModel by viewModels()

    private lateinit var wordsAdapter: WordsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWordAdapter()

        getWords()
    }

    private fun initWordAdapter() {
        wordsAdapter = WordsAdapter { itemSelected ->
            val wordRefFirebase = WordRefFirebase(
                idLocal = itemSelected.id ?: 0L,
                word = itemSelected.word ?: ""
            )

            val action = HomeFragmentDirections
                .actionHomeFragmentToDetailsFragment(wordRefFirebase)

            findNavController().navigate(action)
        }

        with(binding.wordRecycler) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = wordsAdapter.withLoadStateFooter(
                footer = MainLoadStateAdapter()
            )
        }
    }

    private fun getWords() {
        lifecycleScope.launch {
            wordsViewModel.charactersPagingData().collect { pagingData ->
                wordsAdapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}