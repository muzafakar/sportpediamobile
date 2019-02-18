package com.sportpedia.ui.splash.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.sportpedia.R
import com.sportpedia.ui.main.MainActivity
import com.sportpedia.util.Const
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity

class RegisterFragment : Fragment(), AnkoLogger {
    private val firestore = FirebaseFirestore.getInstance()
    private lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uid = FirebaseAuth.getInstance().currentUser?.uid!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_register, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = "Complete Registration"

        btnSubmit.setOnClickListener {
            val address = etAddress.text.toString()
            val phone = etPhone.text.toString()

            //TODO add regex to check phone format
            if (address.isNotEmpty() && phone.isNotEmpty()) {
                val hash = HashMap<String, String>().apply {
                    put(Const.phone, phone)
                    put(Const.address, address)

                }
                firestore.document("${Const.user}/$uid")
                    .set(hash, SetOptions.merge())
                    .addOnCompleteListener {
                        if (it.isComplete) {
                            context!!.startActivity<MainActivity>()
                            activity!!.finish()
                        } else {
                            // TODO handle error
                        }
                    }
            }
        }
    }
}