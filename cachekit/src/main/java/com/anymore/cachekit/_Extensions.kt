package com.anymore.cachekit

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

/**
 * Created by liuyuanmao on 2019/5/22.
 */
internal fun String.md5():String{
    return try {
        val digest = MessageDigest.getInstance("MD5")
        digest.update(toByteArray())
        digest.digest().toHexString()
    }catch (e:NoSuchAlgorithmException){
        hashCode().toString()
    }
}

internal fun ByteArray.toHexString():String{
    val sb = StringBuilder(size * 2)
    for (element in this) {
        val v = element.toInt() and 0xff
        if (v < 16) {
            sb.append('0')
        }
        sb.append(Integer.toHexString(v))
    }
    return sb.toString().toLowerCase(Locale.US)
}