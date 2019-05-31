package com.anymore.example.app.config

import android.content.Context
import android.support.annotation.IntRange
import com.anymore.example.app.config.Key.WEBVIEW_TEXT_SIZE
import com.anymore.example.app.config.Name.CONFIG_SP_NAME

import com.anymore.example.mvvm.ExampleApplication

/**
 * Created by liuyuanmao on 2019/5/7.
 */
private object Name {
    const val CONFIG_SP_NAME = "app_config"
}

private object Key {
    const val WEBVIEW_TEXT_SIZE = "webview_text_size"
}

object AppConfig {
    private val mSharedPreferences by lazy {
        ExampleApplication.INSTANCE.getSharedPreferences(
            CONFIG_SP_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun saveWebViewTextSize(@IntRange(from = 0, to = 3) value: Int) {
        mSharedPreferences.edit().putInt(WEBVIEW_TEXT_SIZE, value).apply()
    }

    fun getWebViewTextSize(default: Int) = mSharedPreferences.getInt(WEBVIEW_TEXT_SIZE, default)

}