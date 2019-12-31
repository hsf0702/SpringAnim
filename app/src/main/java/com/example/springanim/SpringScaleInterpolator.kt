package com.example.springanim

import android.view.animation.Interpolator

/**
 * created by hsf
 * date:2019/12/30
 * description:
 */

class SpringScaleInterpolator(//弹性因数
    private val factor: Float
) : Interpolator {

    override fun getInterpolation(input: Float): Float {

        return (Math.pow(
            2.0,
            (-10 * input).toDouble()
        ) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1).toFloat()
    }

}

