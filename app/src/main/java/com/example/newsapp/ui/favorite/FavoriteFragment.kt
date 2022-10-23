package com.example.newsapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentDetailsBinding
import com.example.newsapp.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val eBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return inflater.inflate(R.layout.fragment_details, container, false)
    }


}