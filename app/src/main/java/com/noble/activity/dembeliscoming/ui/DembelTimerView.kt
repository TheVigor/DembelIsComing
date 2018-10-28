package com.noble.activity.dembeliscoming.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.noble.activity.dembeliscoming.R

class DembelTimerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.timer_main, this, true)

        background = ContextCompat.getDrawable(context, R.drawable.timer_view_background)
    }
}
