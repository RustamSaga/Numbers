package com.rustamsaga.numbers.main.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.rustamsaga.numbers.R

class DetailsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val value = requireArguments().getString(KEY)
        view.findViewById<TextView>(R.id.detailsTextView).text = value
    }

    companion object {
        private const val KEY = "details"

        fun newInstance(value: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY, value)
                }
            }

    }
}