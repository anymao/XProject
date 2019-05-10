package com.anymore.example.app.cookies

import android.content.Context
import com.anymore.example.mvvm.model.api.BASE_URL
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * @param saveUrls 如果不为空，只保存列表中地址服务器所返回的cookie；如果为空，保存所有的url的cookie
 * Created by liuyuanmao on 2019/5/5.
 */
class PersistentCookieJar(private val cookieStore: CookieStore,private val saveUrls:List<String>? = SAVE_COOKIES_URLS):CookieJar{

    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        if (saveUrls != null){
            if (saveUrls.contains(url.toString())){
                cookieStore.add(url,cookies)
            }
        }else{
            cookieStore.add(url,cookies)
        }
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        return cookieStore.getCookies()
    }

    fun clear(){
        cookieStore.removeAll()
    }

    companion object {
        private val SAVE_COOKIES_URLS = arrayListOf(
            "$BASE_URL/user/login",
            "$BASE_URL/user/register",
            "$BASE_URL/logout/json"
        )

        private var instance:PersistentCookieJar? = null

        fun getInstance(context: Context):PersistentCookieJar{
            if (instance == null){
                instance = PersistentCookieJar(SharedPreferencesCookieStore(context))
            }
            return instance!!
        }
    }

}