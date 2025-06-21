package com.example.myapplication

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat

class P19EditTextCustom (context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {

    private var sethint: String? = null
    private var setcolor = false
    var darkButton: Drawable? = null
    var lightButton: Drawable? = null
    fun init() {
        darkButton = ResourcesCompat.getDrawable(resources, R.drawable.baseline_1x_mobiledata_24,
            null )
        lightButton = ResourcesCompat.getDrawable(resources, R.drawable.baseline_1x_mobiledata_22, null )
        addTextChangedListener(object : TextWatcher {
          override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                showButton()
                show()
            }
            override fun afterTextChanged(s: Editable) {}
        })
    }
    fun show() {
        setOnTouchListener { view, motionEvent ->
            var isclicked:Boolean = false
            val clearButtonStart = (width - paddingEnd - darkButton!!.intrinsicWidth).toFloat()
            if (motionEvent.x > clearButtonStart) {
                isclicked = true
            }
            if (isclicked) {
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> text!!.clear()
                    MotionEvent.ACTION_UP -> hideButton()
                }
            }
            true
        }
    }

    fun showButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, darkButton, null)
    }
    fun hideButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, lightButton, null)
    }
    init {
        val tarry1: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextCustom)
        try {
            sethint = tarry1.getString(R.styleable.EditTextCustom_setHint)
            if (sethint == null)
                setHint("enter ur message")
            else
                setHint("enter ur message here")
//retrieving a boolean attribute value from a set of XML attributes using Android's TypedArray class.
            setcolor = tarry1.getBoolean(R.styleable.EditTextCustom_setColor1, true)
            if (setcolor)
                setTextColor(Color.RED)
        }
        finally
        {
            tarry1.recycle()
            init()
        }
    }
}