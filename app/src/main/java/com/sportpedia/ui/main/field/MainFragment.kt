package com.sportpedia.ui.main.field

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sportpedia.R
import com.sportpedia.util.Dialogs
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.AnkoLogger

class MainFragment : Fragment(), AnkoLogger {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        etDate.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                Dialogs.setDate(context!!, v)
            }
        }

        etTime.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                Dialogs.setTime(context!!, v)
            }
        }

        btnSearch.setOnClickListener {
            findNavController().navigate(R.id.toField)
        }
    }
}