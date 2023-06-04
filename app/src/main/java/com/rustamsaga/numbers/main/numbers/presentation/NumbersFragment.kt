package com.rustamsaga.numbers.main.numbers.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rustamsaga.numbers.R
import com.rustamsaga.numbers.main.details.presentation.DetailsFragment
import com.rustamsaga.numbers.main.presentation.ShowFragment

class NumbersFragment : Fragment() {

    private var showFragment: ShowFragment = ShowFragment.Empty()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showFragment = context as ShowFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_numbers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.startProgressBar).visibility = View.GONE

        view.findViewById<View>(R.id.getFactButton).setOnClickListener {
            showFragment.show(
                DetailsFragment
                    .newInstance("Some information about the random number")
            )
        }

    }
    override fun onDetach() {
        super.onDetach()
        showFragment = ShowFragment.Empty()
    }
}