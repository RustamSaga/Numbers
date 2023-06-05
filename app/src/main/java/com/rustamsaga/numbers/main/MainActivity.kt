package com.rustamsaga.numbers.main.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rustamsaga.numbers.R
import com.rustamsaga.numbers.main.numbers.presentation.NumbersFragment

class MainActivity : AppCompatActivity(), ShowFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NumbersFragment())
                .commit()
    }

    override fun show(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }

}

interface ShowFragment {
    fun show(fragment: Fragment)

    class Empty: ShowFragment{
        override fun show(fragment: Fragment) = Unit

    }
}