package com.saharnollily.stocksapplication.utils

import android.view.View

fun View.hide(){
    visibility = View.GONE
}

fun View.show(){
    visibility = View.VISIBLE
}

fun Float.round(decimals: Int = 2): Float = "%,${decimals}f".format(this).toFloat()

fun String.replaceArabicNumber(): String{
    var result = ""
    var en = '0'
    for (ch in this) {
        en = ch
        when (ch) {
            '۰' -> en = '0'
            '۱' -> en = '1'
            '۲' -> en = '2'
            '۳' -> en = '3'
            '۴' -> en = '4'
            '۵' -> en = '5'
            '۶' -> en = '6'
            '۷' -> en = '7'
            '۸' -> en = '8'
            '۹' -> en = '9'
        }
        result = "${result}$en"
    }
    return result
}