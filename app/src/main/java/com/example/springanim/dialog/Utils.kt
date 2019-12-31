package com.example.springanim.dialog

import android.content.Context

/**
 * created by hsf
 * date:2019/12/30
 * description:
 */
object Utils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}
