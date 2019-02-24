package com.sportpedia.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sportpedia.R
import com.sportpedia.adapter.CategoryAdapter
import com.sportpedia.model.Category
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.AnkoLogger

class MainFragment : Fragment(), AnkoLogger {
    private val categoryList = mutableListOf<Category>()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryAdapter = CategoryAdapter(context!!, categoryList) {

        }

    }

    private fun setupDummyCategory() {
        val cFutsal = Category(category = "futsal", icon = R.drawable.ic_futsal)
        val cBasketball = Category(category = "basketball", icon = R.drawable.ic_basketball)
        val cBadmintion = Category(category = "badmintion", icon = R.drawable.ic_badminton)
        val cFootball = Category(category = "football", icon = R.drawable.ic_football)

        categoryList.apply {
            add(cFutsal)
            add(cBasketball)
            add(cBadmintion)
            add(cFootball)
        }

        categoryAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCategory.layoutManager = GridLayoutManager(context!!, 3)
        rvCategory.adapter = categoryAdapter
        setupDummyCategory()
    }


}