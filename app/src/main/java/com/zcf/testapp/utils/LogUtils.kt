package com.zcf.testapp.utils

import android.util.Log
import com.zcf.testapp.BuildConfig

fun log(tag: String, any: Any?) {
    if (!BuildConfig.DEBUG) {
        return
    }

    Log.d(tag, "$any")
}