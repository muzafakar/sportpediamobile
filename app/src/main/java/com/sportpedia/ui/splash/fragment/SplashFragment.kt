package com.sportpedia.ui.splash.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.sportpedia.R
import com.sportpedia.ui.main.MainActivity
import com.sportpedia.util.PrefManager
import kotlinx.android.synthetic.main.fragment_splash.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity

class SplashFragment : Fragment(), AnkoLogger {
    private val auth = FirebaseAuth.getInstance()
    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefManager = PrefManager(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_splash, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            if (auth.currentUser != null) {
                // TODO checkUserData(Source.CACHE)
                toMainActivity()
            } else {
                animateButton()
                btnSignIn.setOnClickListener {
                    requestGoogleAccount()
                }
            }
        }, 1500)
    }

    private fun animateButton() {
        val fadeIn = AlphaAnimation(0f, 1f).apply {
            interpolator = DecelerateInterpolator()
            duration = 1100L
        }

        btnSignIn.animation = fadeIn
        btnSignIn.visibility = View.VISIBLE
    }

    private fun toMainActivity() {
        context!!.startActivity<MainActivity>()
        activity!!.finish()
    }

    private fun requestGoogleAccount() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestId()
            .requestEmail()
            .requestProfile()
            .build()

        context?.let {
            val googleSignIn = GoogleSignIn.getClient(it, gso)
            startActivityForResult(googleSignIn.signInIntent, 11)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 11) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.let {
                    val credential = GoogleAuthProvider.getCredential(it.idToken, null)
                    signInFirebase(credential)
                }
            } catch (ex: ApiException) {
                info { "gAccount: $ex" }
            }
        }
    }

    private fun signInFirebase(credential: AuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    toMainActivity()
                }
            }
    }
/*
    private fun checkUserData(source: Source) {
        val uid = auth.currentUser?.uid
        firestore.document("${Const.user}/$uid")
            .get(source)
            .addOnCompleteListener {
                if (it.isSuccessful && it.result!!.exists()) {
                    it.result?.let { doc ->
                        val address: String? = doc.get(Const.address) as String
                        val phone: String? = doc.get(Const.phone) as String

                        if (address.isNullOrEmpty() && phone.isNullOrEmpty()) {
                            findNavController().navigate(R.id.toRegister)
                        } else {
                            val user = doc.toObject(User::class.java)!!
                            prefManager.saveUser(user)
                            toMainActivity()
                        }
                    }
                }
            }
    }*/
}
