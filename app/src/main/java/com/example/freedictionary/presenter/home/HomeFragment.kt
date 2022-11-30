package com.example.freedictionary.presenter.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.freedictionary.R
import com.example.freedictionary.databinding.FragmentHomeBinding
import com.example.freedictionary.presenter.adapters.ViewPagerAdapter
import com.example.freedictionary.presenter.favorites.FavoritesFragment
import com.example.freedictionary.presenter.history.HistoricFragment
import com.example.freedictionary.presenter.words.WordsFragment
import com.example.freedictionary.util.FirebaseHelper
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabs()

        initListeners()
    }

    private fun initListeners() {
        binding.btnLogout.setOnClickListener {
            FirebaseHelper.getAuth().signOut()

            val navOptions: NavOptions =
                NavOptions.Builder().setPopUpTo(R.id.homeFragment, true).build()
            findNavController().navigate(R.id.action_global_authentication, null, navOptions)
        }
    }

    private fun initTabs() {
        val pageAdapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = pageAdapter

        pageAdapter.addFragment(WordsFragment(), R.string.tab_name_word)
        pageAdapter.addFragment(HistoricFragment(), R.string.tab_name_history)
        pageAdapter.addFragment(FavoritesFragment(), R.string.tab_name_favorite)

        binding.viewPager.offscreenPageLimit = pageAdapter.itemCount

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = getString(pageAdapter.getTitle(position))
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}