package com.practice.koinpractice.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.practice.koinpractice.domain.Repository
import com.practice.koinpractice.presentation.LandingFragment.Companion.BUNDLE_INFO

class LandingViewModel(private val handle: SavedStateHandle, private val repository: Repository, private val hostName: String) : ViewModel() {

    fun loadContent(): String = """
        ${repository.retrieve()} in $hostName
        ViewModel-ID: ${System.identityHashCode(this)}
    """.trimIndent()

    fun loadBundle(): String? = handle[BUNDLE_INFO]

    fun saveBundle(data: String) {
        handle[BUNDLE_INFO] = data
    }
}