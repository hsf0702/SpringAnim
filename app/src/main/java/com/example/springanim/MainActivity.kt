package com.example.springanim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.springanim.dialog.ConnInfoBean
import com.example.springanim.dialog.ConnInfoDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var bottomDialog: ConnInfoDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_anim.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val animX = ObjectAnimator.ofFloat(iv, "scaleX", 1.0F, 1.8F, 1.0f)
                val animY = ObjectAnimator.ofFloat(iv, "scaleY", 1.0F, 1.8F, 1.0f)
                val set = AnimatorSet()
                set.setDuration(1000)
                set.setInterpolator(MyBounceInterpolator())
                set.playTogether(animX, animY)
                set.start()
            }
        })

        no_anim_dialog.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val bean = ConnInfoBean()
                bean.title = "温馨提示"
                bean.content = "导出成功，是否现在打开该excel文件？"
                val dialog = ConnInfoDialog(
                    this@MainActivity,
                    bean,
                    object : ConnInfoDialog.CallBack {
                        override fun onCancelClick() {

                        }

                        override fun onOkClick() {

                        }
                    })
                dialog.showDialog(false, 0)
            }
        })

        with_anim_dialog.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val bean = ConnInfoBean()
                bean.title = "温馨提示"
                bean.content = "导出成功，是否现在打开该excel文件？"
                val dialog = ConnInfoDialog(
                    this@MainActivity,
                    bean,
                    object : ConnInfoDialog.CallBack {
                        override fun onCancelClick() {

                        }

                        override fun onOkClick() {

                        }
                    })
                dialog.showDialog(true, 1)
            }
        })

        bt_bottom.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val bean = ConnInfoBean()
                bean.title = "温馨提示"
                bean.content = "导出成功，是否现在打开该excel文件？"
                bottomDialog = ConnInfoDialog(
                    this@MainActivity,
                    bean,
                    object : ConnInfoDialog.CallBack {
                        override fun onCancelClick() {

                        }

                        override fun onOkClick() {

                        }
                    })
                bottomDialog!!.showDialog(true, 2)
            }
        })
    }


    override fun onResume() {
        super.onResume()
        if (bottomDialog != null && bottomDialog!!.getDialog().isShowing) {
            bottomDialog!!.dissMiss()
        }
    }
}
