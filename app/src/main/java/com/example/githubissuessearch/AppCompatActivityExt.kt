package com.example.githubissuessearch

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.addFragmentTo(containderId: Int, fragment: Fragment, tag: String = "") {
    supportFragmentManager.beginTransaction().add(containderId, fragment, tag).commit();
}