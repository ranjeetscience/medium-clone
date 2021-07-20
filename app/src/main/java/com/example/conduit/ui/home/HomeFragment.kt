package com.example.conduit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.api.model.entity.Article
import com.example.conduit.adapter.FeedListAdapter
import com.example.conduit.databinding.FragmentHomeBinding
import com.example.conduit.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var _binding: FragmentHomeBinding
    private lateinit var adapter: FeedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = FeedListAdapter(
            onFavoriteClicked = this::markFavourite,
            onArticleClicked = this::onArticleClicked
        )
        _binding.rlFeed.adapter = adapter

        return _binding.root
    }

    private fun markFavourite(article: Article) {

    }

    private fun onArticleClicked(slug: String) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.feed.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
            }
        }
    }
}