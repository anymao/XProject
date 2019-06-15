package com.anymore.example.mvvm.view.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.PopupWindow
import com.anymore.example.R


/**
 * FloatMenu是实现仿微信的上下文菜单，泛型T与RecyclerView的列表项类型相同
 * Created by liuyuanmao on 2018/11/5.
 */
class FloatMenu<T> @JvmOverloads constructor(
    private val mContext: Context,
    private val mMenuTexts: Array<String>,
    private var mFloatMenuListener: FloatMenuListener<T>? = null
) : PopupWindow() {
    private var mData: T? = null
    private var mTouchItemPosition: Int = 0
    private lateinit var mRootView: FrameLayout
    private lateinit var mMenuList: ListView
    private lateinit var mAdapter: FloatMenuAdapter

    init {
        initLayout()
        initData()
    }

    @SuppressLint("InflateParams")
    private fun initLayout() {
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.layout_float_menu, null, false) as FrameLayout
        mMenuList = mRootView.findViewById(R.id.menu_list)
        mMenuList.dividerHeight = 0
        contentView = mRootView
        width = ViewGroup.LayoutParams.WRAP_CONTENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        isTouchable = true
        //设置点击其他地方就消失
        isOutsideTouchable = true
        isFocusable = true
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


    private fun initData() {
        setOnDismissListener {
            mFloatMenuListener?.onDismiss(mTouchItemPosition)
        }
        mAdapter = FloatMenuAdapter(mContext, mMenuTexts)
        mMenuList.adapter = mAdapter
        mMenuList.setOnItemClickListener { parent, view, position, id ->
            mFloatMenuListener?.onClick(position, mData, mTouchItemPosition)
            dismiss()
        }
    }

    fun setFloatMenuListener(listener: FloatMenuListener<T>) {
        this.mFloatMenuListener = listener
    }

    fun show(view: View, x: Int, y: Int, t: T, position: Int) {
        mData = t//存储长按的这个item对应的bean
        mTouchItemPosition = position//存储item在列表中的位置
        //获取屏幕宽高
        val showX: Int
        val showY: Int
        val m = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val d = m.defaultDisplay
        val size = Point()
        d.getSize(size)
        //apply animations
        if (x > size.x / 2) {//触点在右，向左展开
            showX = x - contentView.measuredWidth
            if (y + contentView.measuredHeight + 10 > size.y) {//触点偏下，向上展开
                showY = y - contentView.measuredHeight
                setAnimationStyle(R.style.FM_anim_bottom_right)
            } else {
                showY = y
                setAnimationStyle(R.style.FM_anim_top_right)
            }
        } else {
            showX = x
            if (y + contentView.measuredHeight + 10 > size.y) {//触点偏下，向上展开
                showY = y - contentView.measuredHeight
                setAnimationStyle(R.style.FM_anim_bottom_left)
            } else {
                showY = y
                setAnimationStyle(R.style.FM_anim_top_left)
            }
        }

        //don't apply animations
        //        if (x > size.x/2){
        //            showX = x-getContentView().getMeasuredWidth()
        //        }else {
        //            showX = x
        //        }
        //        if (y+getContentView().getMeasuredHeight()+10 > size.y){
        //            showY = y-getContentView().getMeasuredHeight()
        //        }else {
        //            showY = y
        //        }
        showAtLocation(view, Gravity.TOP or Gravity.LEFT, showX, showY)
    }


    interface FloatMenuListener<T> {
        /**
         * 浮动菜单点击回调
         * @param positionInFloatMenu 点击菜单中列表项的位置
         * @param item 对应的在视图列表项数据
         * @param positionInList 数据列表项在列表的位置
         */
        fun onClick(positionInFloatMenu: Int, item: T?, positionInList: Int)

        fun onDismiss(positionInList: Int)
    }


    private class FloatMenuAdapter internal constructor(context: Context, objects: Array<String>) :
        ArrayAdapter<String>(context, R.layout.item_float_menu, objects)


}
