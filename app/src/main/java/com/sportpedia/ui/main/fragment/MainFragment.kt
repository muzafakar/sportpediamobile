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
import com.sportpedia.ui.booking.BookingActivity
import com.sportpedia.util.Const
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity

class MainFragment : Fragment(), AnkoLogger {
    private val categoryList = mutableListOf<Category>()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryAdapter = CategoryAdapter(context!!, categoryList) {
            context!!.startActivity<BookingActivity>(Const.category to it.category)
        }

    }

    private fun setupDummyCategory() {
        val cFutsal = Category(category = "futsal", icon = R.drawable.futsal)
        val cBasketball = Category(category = "basketball", icon = R.drawable.basketball)
        val cBadmintion = Category(category = "badminton", icon = R.drawable.badminton)
        val cFootball = Category(category = "football", icon = R.drawable.football)

        categoryList.apply {
            add(cFutsal)
            add(cBasketball)
            add(cBadmintion)
            add(cFootball)
            add(cFootball)
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