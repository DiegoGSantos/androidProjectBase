package com.androidprojectbase.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.androidprojectbase.R
import com.androidprojectbase.util.FontUtils
import com.androidprojectbase.util.isStringValid

/**
 * Created by diegosantos on 12/16/17.
 */
class CustomButton : android.support.v7.widget.AppCompatButton, View.OnClickListener {

    internal var mListener: View.OnClickListener? = null
    internal var mContext: Context

    constructor(context: Context) : super(context) {

        mContext = context
        initView(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        mContext = context
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        mContext = context
        initView(attrs)
    }

    fun setButtonClickListener(onClickListener: View.OnClickListener) {
        mListener = onClickListener
    }

    internal fun initView(attrs: AttributeSet?) {

        setOnClickListener(this)

        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.CustomButton)

            if (ta != null) {

                val style = ta.getString(R.styleable.CustomButton_customTypeface)
                ta.recycle()

                if (isStringValid(style)) {
                    FontUtils.setOpenSansFont(mContext, this, FontUtils.getFontFamily(style))
                } else {
                    FontUtils.setOpenSansFont(mContext, this, FontUtils.FontFamily.OPENSANS)
                }
            }
        } else {
            FontUtils.setOpenSansFont(mContext, this, FontUtils.FontFamily.OPENSANS)
        }
    }

    override fun onClick(view: View) {
        view.isEnabled = false


        if (mListener != null) {
            mListener!!.onClick(view)
        }

        val handler = android.os.Handler()
        handler.postDelayed({ view.isEnabled = true }, 1500)
    }

}