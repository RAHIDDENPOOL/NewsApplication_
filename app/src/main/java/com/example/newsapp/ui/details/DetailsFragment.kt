package com.example.newsapp.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val eBinding get() = _binding!!
    private val viewModel by viewModels<DetailsViewModel>()
    private val bundleArgs: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return eBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = bundleArgs.article

        article.let { article ->
            article.urlToImage.let {
                Glide.with(this).load(article.urlToImage).into(eBinding.headerImage)
            }
            eBinding.headerImage.clipToOutline = true
            eBinding.articleDetailsTitle.text = article.title
            eBinding.articleDetailsDecriptionText.text = article.description
            eBinding.iconFavorite.setOnClickListener {
                viewModel.saveFavoriteArticle(article)
                eBinding.articleDetailsButton.setOnClickListener {
                    try {
                        Intent()
                            .setAction(Intent.ACTION_VIEW)
                            .addCategory(Intent.CATEGORY_BROWSABLE)
                            .setData(Uri.parse(takeIf { URLUtil.isValidUrl(article.url) }
                                ?.let {
                                    article.url
                                } ?: "https://google.com"))
                            .let {
                                ContextCompat.startActivity(requireContext(), it, null)
                            }

                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "The device doesn`t have any browser to view the document",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}