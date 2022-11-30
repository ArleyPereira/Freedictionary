package com.example.freedictionary.presenter.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.freedictionary.R
import com.example.freedictionary.data.remote.model.Definition
import com.example.freedictionary.databinding.FragmentDetailsBinding
import com.example.freedictionary.domain.model.WordRefFirebase
import com.example.freedictionary.domain.model.WordSearchedDomain
import com.example.freedictionary.util.StateView
import com.example.freedictionary.util.showBottomSheet
import com.like.LikeButton
import com.like.OnLikeListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val detailsViewModel: DetailsViewModel by viewModels()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    private var favoriteList: MutableList<WordRefFirebase> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFavorites()

        getWordSearched(args.wordRefFirebase.word)

        initListeners()
    }

    private fun initListeners() {
        binding.ivClose.setOnClickListener { findNavController().popBackStack() }
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

        binding.likeButton.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {
                saveFavorite()
            }

            override fun unLiked(likeButton: LikeButton) {
                removeFavorite()
            }
        })
    }

    private fun initMeaningAdapter(definitionList: List<Definition>) {
        with(binding.rvDefinition) {
            setHasFixedSize(true)
            adapter = MeaningAdapter(requireContext(), definitionList)
        }
    }

    private fun getWordSearched(word: String) {
        detailsViewModel.getWordSearched(word).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    setShimmerVisibility(true)
                }

                is StateView.Success -> {
                    if (stateView.data != null) {
                        setShimmerVisibility(false)
                        configData(stateView.data)
                    } else {
                        getWordApi(word)
                    }
                }

                is StateView.Error -> {
                    showBottomSheet()
                }
            }
        }
    }

    private fun getWordApi(word: String) {
        detailsViewModel.getWordApi(word).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {

                }

                is StateView.Success -> {
                    stateView.data?.get(0)?.let {
                        configData(it)

                        insertWordSearched(it)
                    }
                    setShimmerVisibility(false)
                }

                is StateView.Error -> {
                    showBottomSheet(
                        message = stateView.message,
                        onClick = { findNavController().popBackStack() }
                    )
                }
            }
        }
    }

    private fun setShimmerVisibility(visibility: Boolean) {
        binding.shimmerLayoutLoading.shimmerDetails.run {
            isVisible = visibility
            if (visibility) {
                startShimmer()
            } else stopShimmer()
        }

        binding.layoutDetails.isVisible = !visibility
    }

    private fun configData(word: WordSearchedDomain) {
        detailsViewModel.saveWordHistoric(
            WordRefFirebase(idLocal = args.wordRefFirebase.idLocal, word = args.wordRefFirebase.word)
        )

        binding.textWord.text = word.word
        binding.textPhonetic.text = word.phonetic

        val definitionList = word.meanings?.get(0)?.definitions ?: emptyList()
        initMeaningAdapter(definitionList)
    }

    private fun insertWordSearched(wordSearchedDomain: WordSearchedDomain) {
        detailsViewModel.insertWordSearched(wordSearchedDomain)
            .observe(viewLifecycleOwner) { stateView ->
                when (stateView) {
                    is StateView.Loading -> {

                    }

                    is StateView.Success -> {

                    }

                    is StateView.Error -> {

                    }
                }
            }
    }

    private fun getFavorites() {
        detailsViewModel.favorites.observe(viewLifecycleOwner) { favorites ->
            favoriteList.clear()
            favoriteList.addAll(favorites)

            binding.likeButton.isLiked =
                favorites.indexOfFirst { it.idLocal == args.wordRefFirebase.idLocal } > -1
        }
    }

    private fun saveFavorite() {
        detailsViewModel.saveFavorite(args.wordRefFirebase)
            .observe(viewLifecycleOwner) { stateView ->
                when (stateView) {
                    is StateView.Loading -> {

                    }
                    is StateView.Success -> {
                        Toast.makeText(
                            requireContext(),
                            R.string.message_save_favorite_sucesso_details_fragment,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is StateView.Error -> {
                        binding.likeButton.isLiked = false
                        showBottomSheet(message = getString(R.string.message_save_favorite_error_details_fragment))
                    }
                }
            }
    }

    private fun removeFavorite() {
        val wordRefFirebase = favoriteList.first { it.idLocal == args.wordRefFirebase.idLocal }
        detailsViewModel.removeFavorite(wordId = wordRefFirebase.id)
            .observe(viewLifecycleOwner) { stateView ->
                when (stateView) {
                    is StateView.Loading -> {

                    }
                    is StateView.Success -> {
                        Toast.makeText(
                            requireContext(),
                            R.string.message_remove_favorite_sucesso_details_fragment,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is StateView.Error -> {
                        binding.likeButton.isLiked = true
                        showBottomSheet(message = getString(R.string.message_remove_favorite_error_details_fragment))
                    }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}