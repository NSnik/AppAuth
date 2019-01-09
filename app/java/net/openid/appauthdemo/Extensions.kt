package com.example.demofon

import android.support.v4.app.Fragment
import org.jetbrains.anko.bundleOf

inline fun <reified T : Fragment> newInstanceFragment(vararg params: Pair<String, Any>) =
        T::class.java.newInstance().apply {
            arguments = bundleOf(*params)
        }
