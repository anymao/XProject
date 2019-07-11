package com.anymore.example.mvvm.view.anims

import com.anymore.example.R

/**
 * 补间动画
 * Created by liuyuanmao on 2019/7/2.
 */
interface CoreAnimations {

    companion object {
        /**
         *以下是一些默认实现动画
         */

        /** 没有动画 */
        val None = object : CoreAnimations {
            override fun convertAnimations() = emptyArray<Int>()
        }

        /**由下到上动画 */
        val Present = object : CoreAnimations {
            override fun convertAnimations() =
                arrayOf(R.anim.push_in_down, R.anim.push_no_ani, R.anim.push_no_ani, R.anim.push_out_down)
        }

        /** 从左到右动画 */
        val Slide = object : CoreAnimations {
            override fun convertAnimations() =
                arrayOf(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
        }

        /**渐变 */
        val Fade = object : CoreAnimations {
            override fun convertAnimations() =
                arrayOf(R.anim.alpha_in, R.anim.alpha_out, R.anim.alpha_in, R.anim.alpha_out)
        }

        /**放大 */
        val Zoom = object : CoreAnimations {
            override fun convertAnimations() = arrayOf(R.anim.zoom_in, R.anim.zoom_out, R.anim.zoom_in, R.anim.zoom_out)
        }
    }

    fun convertAnimations(): Array<Int>
}