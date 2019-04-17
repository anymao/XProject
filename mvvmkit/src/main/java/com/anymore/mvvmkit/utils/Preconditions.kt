package com.anymore.mvvmkit.utils

import android.support.annotation.Nullable
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Created by liuyuanmao on 2019/4/17.
 */

inline fun checkState(expression: Boolean) {
    if (!expression) {
        throw IllegalStateException()
    }
}

inline fun checkState(expression: Boolean, @Nullable errorMessage: Any) {
    if (!expression) {
        throw IllegalStateException(errorMessage.toString())
    }
}

inline fun checkState(expression: Boolean, @Nullable errorMessageTemplate: String, @Nullable vararg errorMessageArgs: Any) {
    if (!expression) {
        throw IllegalStateException(format(errorMessageTemplate, *errorMessageArgs))
    }
}

@ExperimentalContracts
inline fun <T> checkNotNull(reference: T?, @Nullable errorMessage: Any): T {
    contract {
        returns() implies (reference != null)
    }
    return reference ?: throw NullPointerException(errorMessage.toString())
}

@ExperimentalContracts
fun <T> checkNotNull(reference: T?, @Nullable errorMessageTemplate: String, @Nullable vararg errorMessageArgs: Any): T {
    contract {
        returns() implies (reference != null)
    }
    return reference ?: throw NullPointerException(format(errorMessageTemplate, *errorMessageArgs))
}

fun format(template: String, @Nullable vararg args: Any): String {
    val builder = StringBuilder(template.length + 16 * args.size)
    var templateStart = 0

    var i = 0
    var placeholderStart: Int
    while (i < args.size) {
        placeholderStart = template.indexOf("%s", templateStart)
        if (placeholderStart == -1) {
            break
        }

        builder.append(template.substring(templateStart, placeholderStart))
        builder.append(args[i++])
        templateStart = placeholderStart + 2
    }

    builder.append(template.substring(templateStart))
    if (i < args.size) {
        builder.append(" [")
        builder.append(args[i++])

        while (i < args.size) {
            builder.append(", ")
            builder.append(args[i++])
        }

        builder.append(']')
    }

    return builder.toString()
}