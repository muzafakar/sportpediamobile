package com.sportpedia.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sportpedia.R
import org.jetbrains.anko.AnkoLogger

class SplashActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //TODO bisa check update
    }
}
