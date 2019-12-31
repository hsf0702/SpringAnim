package com.example.springanim.dialog

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.marginLeft

import com.example.springanim.R
import com.example.springanim.SpringScaleInterpolator
import kotlinx.android.synthetic.main.activity_main.*


/**
 * 自定义弹出提示信息的dialog
 */
class ConnInfoDialog(
    private val mActivity: Activity,
    private val bean: ConnInfoBean,
    private val callBack: CallBack
) {
    private var dialog: AlertDialog? = null
    private var dialogView: View? = null
    private var ll_container: LinearLayout? = null

    fun getDialog(): AlertDialog {
        return this.dialog!!
    }

    @SuppressLint("ObjectAnimatorBinding")
    fun showDialog(isAnim: Boolean, type: Int) {
        if (type != 2) {
            dialogView = LayoutInflater.from(mActivity).inflate(R.layout.conn_info_layout, null)
            val tv_title = dialogView!!.findViewById<TextView>(R.id.tv_title)
            ll_container = dialogView!!.findViewById<LinearLayout>(R.id.ll_container)
            val tv_content = dialogView!!.findViewById<TextView>(R.id.tv_content)
            val tv_cancel = dialogView!!.findViewById<TextView>(R.id.tv_cancel)
            val tv_ok = dialogView!!.findViewById<TextView>(R.id.tv_ok)

            if (bean.cancelText != null && bean.cancelText != "") {
                tv_cancel.text = bean.cancelText
            } else {
                tv_cancel.text = "取消"
            }
            if (bean.okText != null && bean.okText != "") {
                tv_ok.text = bean.okText
            } else {
                tv_ok.text = "确定"
            }

            tv_cancel.setOnClickListener {
                callBack.onCancelClick()
                dialog!!.dismiss()
            }
            tv_ok.setOnClickListener {
                callBack.onOkClick()
                dialog!!.dismiss()
            }

            tv_title.text = bean.title
            tv_content.text = bean.content
        } else {
            dialogView = LayoutInflater.from(mActivity).inflate(R.layout.conn_bottom_layout, null)
            ll_container = dialogView!!.findViewById<LinearLayout>(R.id.ll_container)
            val tv = dialogView!!.findViewById<TextView>(R.id.tv)

            tv.layoutParams = LinearLayout.LayoutParams(
                Utils.dip2px(mActivity, 360f),
                Utils.dip2px(mActivity, 300.0f)
            )

        }
        val customizeDialog =
            AlertDialog.Builder(mActivity, R.style.center_alert_dialog).setView(dialogView)
        dialog = customizeDialog.create()

        ll_container!!.setVisibility(View.VISIBLE)
        if (isAnim) {
            if (type == 1) {
                dialog!!.setCanceledOnTouchOutside(false)//是否外部可点击
                val window = dialog!!.getWindow()
                //设置弹出位置
                window!!.setWindowAnimations(R.style.dialog_anim)//设置动画效果
                window.setGravity(Gravity.CENTER)
            } else if (type == 2) {
                val window = dialog!!.getWindow()
                //设置弹出位置
                window!!.setWindowAnimations(R.style.slide_anim)//设置动画效果
                window.setGravity(Gravity.BOTTOM)
            }
        }
        dialog!!.show()
    }


    fun dissMiss() {
        if (dialog != null) {
            dialog!!.dismiss()
        }
    }

    interface CallBack {
        fun onOkClick()

        fun onCancelClick()
    }
}
