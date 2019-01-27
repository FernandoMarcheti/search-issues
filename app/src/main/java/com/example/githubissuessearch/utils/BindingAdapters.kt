package com.example.githubissuessearch.utils

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("mutableInt")
fun setMutableInt(view: TextView, number: MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null) {
        number?.observe(parentActivity, Observer { value -> view.text = value.toString()})
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}