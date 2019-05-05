package com.anymore.example.app.cookies

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Created by liuyuanmao on 2019/5/5.
 */
class PersistentCookieJar(private val cookieStore: CookieStore):CookieJar{
    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        cookieStore.add(url,cookies)
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        return cookieStore.get(url)
    }

}