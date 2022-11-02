package com.example.newsapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentMainBinding
import com.example.newsapp.ui.adapters.NewsAdapter
import com.example.newsapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val eBinding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()
    lateinit var newsAdapter: NewsAdapter // создаю адаптер

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return eBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        // переход на DetailsFragment Создал аргумент в nav_graph, добавил в конструктор article ":Serializable"
        newsAdapter.setOnItemClickListener {
            val bundle = bundleOf("article" to it)
            view.findNavController().navigate(
                R.id.action_mainFragment_to_detailsFragment,
                bundle
            )
        }


        viewModel.newsLiveData.observe(viewLifecycleOwner) { responce ->
            when (responce) {
                // действия взависимости от того что с обёрткой Resource даём действия
                is Resource.Success -> {
                    eBinding.pagProgressBar.visibility = View.INVISIBLE
                    // Загружаем данные в RecyclerView
                    responce.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Error -> {
                    eBinding.pagProgressBar.visibility = View.INVISIBLE
                    responce.data?.let {
                        Log.e("TAG 228", "MainFragment:error${it}")
                    }
                }

                is Resource.Loading -> {
                    eBinding.pagProgressBar.visibility = View.VISIBLE
                }

            }
        }
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter()
        eBinding.newsAdapter.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}