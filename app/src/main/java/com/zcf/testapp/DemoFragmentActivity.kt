package com.zcf.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.zcf.testapp.fragment1.Tab1Fragment

class DemoFragmentActivity: AppCompatActivity(R.layout.activity_demo_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragmentContainerView, Tab1Fragment::class.java, intent.extras)
            }
        }
    }
}