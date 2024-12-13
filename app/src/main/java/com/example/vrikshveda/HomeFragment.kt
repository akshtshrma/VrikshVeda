package com.example.vrikshveda

import ArticleAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.vrikshveda.adapters.HorizontalPagerAdapter



class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val horizontalViewPager = view.findViewById<ViewPager2>(R.id.horizontalViewPager)
        horizontalViewPager.adapter = HorizontalPagerAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewArticles)

        val articles = listOf(
            Article(R.drawable.article_1, "Want to Know About the Side effects of Neem", "By : Saurabh Joshi"),
            Article(R.drawable.article_2, "Plant a Tree, Grow a Forest Closer to Home", "By : Richa Patel"),
            Article(R.drawable.article_3, "Imapct of Off-Shore Nutrients on Plants", "By : N.K.Tiwari"),
            Article(R.drawable.article_1, "Want to Know About the Side effects of Neem", "By : Saurabh Joshi"),
            Article(R.drawable.article_2, "Plant a Tree, Grow a Forest Closer to Home", "By : Richa Patel"),
            Article(R.drawable.article_3, "Imapct of Off-Shore Nutrients on Plants", "By : N.K.Tiwari"),
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ArticleAdapter(articles)

        return view
    }
}

