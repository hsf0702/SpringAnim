package com.example.springanim

import android.view.animation.Interpolator

/**
 * created by hsf
 * date:2019/12/31
 * description:
 */
class MyBounceInterpolator : Interpolator {
    internal var defaultAmplitude = 0.3
    internal var defaultFrequency = 6.0

    constructor() {}

    constructor(defaultAmplitude: Double, defaultFrequency: Double) {
        this.defaultAmplitude = defaultAmplitude
        this.defaultFrequency = defaultFrequency
    }

    override fun getInterpolation(time: Float): Float {
        return (-1.0 * Math.pow(
            Math.E,
            -time / defaultAmplitude
        ) * Math.cos(defaultFrequency * time) + 1).toFloat()
    }
}
