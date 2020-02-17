package com.example.petish.Utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.petish.Model.ApiRequest
import com.example.petish.Model.Category
import java.util.regex.Pattern.compile

fun omri(context: Context,token: String){
    Toast.makeText(context,token,Toast.LENGTH_LONG ).show()
}


private val emailRegex = compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

fun String.isEmail() : Boolean {
    return emailRegex.matcher(this).matches()
}

fun isNull(any: Any?):Boolean{
    any?.let {
        return false;
    }
    return true;
}
inline fun <T1: Any, T2: Any, R: Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2)->R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}
