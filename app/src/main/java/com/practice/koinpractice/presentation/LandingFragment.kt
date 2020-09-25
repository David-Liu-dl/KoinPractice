package com.practice.koinpractice.presentation

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.practice.koinpractice.R
import com.practice.koinpractice.ViewModuleQualifier
import com.practice.koinpractice.presentation.LandingViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class LandingFragment : Fragment(R.layout.fragment_landing) {
    companion object {
        const val BUNDLE_INFO = "BUNDLE_INFO"
    }

    private val viewModel: LandingViewModel by stateViewModel(
        named(ViewModuleQualifier.LandingViewModel),
        { arguments ?: Bundle() }) {
        parametersOf("LandingView")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.text_view_info).text = information()
        view.findViewById<TextView>(R.id.text_view_bundle).text = bundle()
        view.findViewById<EditText>(R.id.edit_text_bundle)
            .setOnEditorActionListener { v, actionId, event ->
                if (event?.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.saveBundle(v.text.toString())
                }

                false
            }
    }

    private fun information() = "${viewModel.loadContent()} \n Fragment-ID: ${System.identityHashCode(this)}"

    private fun bundle() = "Bundle: ${viewModel.loadBundle() ?: "null"}"
}
