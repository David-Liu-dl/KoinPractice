package com.practice.koinpractice.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.practice.koinpractice.R
import com.practice.koinpractice.appModules
import org.koin.core.context.KoinContextHandler.getOrNull
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin()

        setContentView(R.layout.activity_main)

        savedInstanceState ?: supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, LandingFragment.instance("KoinPractice"))
            .commit()
    }

    private fun initKoin() {
        getOrNull() ?: startKoin {
            modules(appModules(applicationContext))
        }
    }
}
