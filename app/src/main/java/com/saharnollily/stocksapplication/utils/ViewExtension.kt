package com.saharnollily.stocksapplication.utils

import android.view.View
import java.util.*

fun View.hide(){
    visibility = View.GONE
}

fun View.show(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun Float.round(decimals: Int = 2): Float
    {
        val x = if(this.toString().contains(",")){
            this.toString().replace(",", ".").toFloat()
        }else{
            this
        }
        return "%.${decimals}f".format(Locale.ENGLISH, x).toFloat()
}