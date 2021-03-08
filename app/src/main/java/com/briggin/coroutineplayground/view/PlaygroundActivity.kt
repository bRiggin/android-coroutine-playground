package com.briggin.coroutineplayground.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.briggin.coroutineplayground.R
import kotlinx.android.synthetic.main.activity_main.*

class PlaygroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, PlaygroundFragment.newInstance())
                .commit()
        }
    }
}
