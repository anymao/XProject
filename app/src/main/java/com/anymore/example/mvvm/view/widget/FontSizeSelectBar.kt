package com.anymore.example.mvvm.view.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.anymore.mvvmkit.sp2px

/**
 * Created by liuyuanmao on 2018/11/2.
 */
class FontSizeSelectBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    //刻度尺的左边，目前等于paddingLeft
    private var mBarLineLeft: Int = 0
    //刻度尺右边，等于paddingRight
    private var mBarLineRight: Int = 0
    //刻度尺下边，等于paddingBottom
    private var mBarLineBottom: Int = 0
    //文字下边界，等于mBarLineBottom-mTextBarPadding
    private var mTextBaseLine: Int = 0
    /**
     * 希望刻度数目等于刻度文字的数组长度等于刻度文字 字体大小数组的长度
     */
    //刻度的数目
    private var mRatingCount: Int = 0
    //刻度文字
    private var mRatingTexts = arrayOf("小", "标准", "大", "加大")
    //刻度文字字体
    private var mRatingTextSizes = intArrayOf(11, 12, 16, 17)

    //默认字体大小
    private val mDefaultTextSize = 15

    //刻度的间距 = （总长度/刻度数目）
    private var mRatingLength: Int = 0

    //刻度小竖条长度
    private var mMarkLength: Int = 0

    //bar和文字的间隔
    private var mTextBarPadding: Int = 0

    //当前等级
    private var mCurrentRating: Int = 0

    private var mOnRatingChangedListener: OnRatingChangedListener? = null


    internal var paint = Paint()

    init {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        mMarkLength = 15
        mCurrentRating = 0
        mRatingCount = Math.min(mRatingTexts.size, mRatingTextSizes.size)
        mTextBarPadding = context.sp2px(20)
    }

    fun setOnRatingChangedListener(listener: OnRatingChangedListener){
        mOnRatingChangedListener = listener
    }

    fun setRatingTexts(ratingTexts: Array<String>) {
        this.mRatingTexts = ratingTexts
        invalidate()
    }

    fun setRatingTextSizes(ratingTextSizes: IntArray) {
        this.mRatingTextSizes = ratingTextSizes
    }

    fun setRating(rating: Int) {
        if (rating >= mRatingCount || rating < 0) {
            return
        }
        mCurrentRating = rating
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mBarLineLeft = paddingLeft//获取刻度线左边位置
        mBarLineRight = measuredWidth - paddingRight//获取刻度线右边位置
        mBarLineBottom = measuredHeight - paddingBottom//获取刻度线下边位置
        mTextBaseLine = mBarLineBottom - mTextBarPadding//获取文字线位置
        mRatingLength = (mBarLineRight - mBarLineLeft) / (mRatingCount - 1)//获取刻度间隔长度
    }

    override fun onDraw(canvas: Canvas) {
        paint.strokeWidth = 2f
        paint.color = Color.GRAY
        canvas.drawLine(
            mBarLineLeft.toFloat(),
            mBarLineBottom.toFloat(),
            mBarLineRight.toFloat(),
            mBarLineBottom.toFloat(),
            paint
        )
        for (i in 0 until mRatingCount) {
            paint.color = Color.GRAY
            canvas.drawLine(
                (mBarLineLeft + i * mRatingLength).toFloat(),
                (mBarLineBottom - mMarkLength).toFloat(),
                (mBarLineLeft + i * mRatingLength).toFloat(),
                (mBarLineBottom + mMarkLength).toFloat(),
                paint
            )
            paint.color = if (mCurrentRating == i) Color.parseColor("#0080FF") else Color.BLACK
            paint.textSize = context.sp2px(mRatingTextSizes[i]).toFloat()
            paint.textAlign = Paint.Align.CENTER
            canvas.drawText(
                mRatingTexts[i],
                (mBarLineLeft + i * mRatingLength).toFloat(),
                mTextBaseLine.toFloat(),
                paint
            )
        }
        paint.color = Color.GRAY
        canvas.drawLine(
            (mBarLineLeft + mCurrentRating * mRatingLength).toFloat(),
            mBarLineBottom.toFloat(),
            (mBarLineLeft + (mRatingCount - 1) * mRatingLength).toFloat(),
            mBarLineBottom.toFloat(),
            paint
        )
        paint.color = Color.parseColor("#66b3ff")
        canvas.drawCircle(
            (mBarLineLeft + mCurrentRating * mRatingLength).toFloat(),
            mBarLineBottom.toFloat(),
            30f,
            paint
        )

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
            val x = event.x
            for (i in 0 until mRatingCount) {
                val distance = mBarLineLeft + i * mRatingLength - x
                if (Math.abs(distance) < 100) {
                    setRating(i)
                    mOnRatingChangedListener?.onRatingChanged(mCurrentRating, mRatingTextSizes[mCurrentRating])
                    break
                }
            }
        }
        return true
    }

    interface OnRatingChangedListener {
        /**
         * 监听拖动后事件等级变化事件
         * @param rating 等级 = 下标
         * @param textSize 设置的文字的textSize sp
         */
        fun onRatingChanged(rating: Int, textSize: Int)
    }

}
