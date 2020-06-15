package com.aday.core.utils

fun String.removeHttpTag(): String{
    return this.replace("<b>","").replace("</b>","")
}