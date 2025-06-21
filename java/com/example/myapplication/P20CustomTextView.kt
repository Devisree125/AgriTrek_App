package com.example.myapplication

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class P20CustomTextView : AppCompatTextView {
    private var title: String? = null
    private var color = false

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val tarry: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.TextViewCustom)
        try {
            title = tarry.getString(R.styleable.TextViewCustom_settitle)
            if (title == null)
                setText("Custom Message")
            else
                setText("Hello")

            color = tarry.getBoolean(R.styleable.TextViewCustom_setColor, true)
            if (color)
                setTextColor(Color.MAGENTA)
        }
        finally {
            tarry.recycle()
        }
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)  { }
}