package com.anymore.mvvmkit.utils

import android.support.annotation.Nullable

object Preconditions {
    fun checkState(expression: Boolean) {
        if (!expression) {
            throw IllegalStateException()
        }
    }

    fun checkState(expression: Boolean, @Nullable errorMessage: Any) {
        if (!expression) {
            throw IllegalStateException(errorMessage.toString())
        }
    }

    fun checkState(expression: Boolean, @Nullable errorMessageTemplate: String, @Nullable vararg errorMessageArgs: Any) {
        if (!expression) {
            throw IllegalStateException(format(errorMessageTemplate, *errorMessageArgs))
        }
    }


    fun <T> checkNotNull(reference: T?, @Nullable errorMessage: Any): T {
        return reference ?: throw NullPointerException(errorMessage.toString())
    }

    fun <T> checkNotNull(reference: T?, @Nullable errorMessageTemplate: String, @Nullable vararg errorMessageArgs: Any): T {
        return reference ?: throw NullPointerException(format(errorMessageTemplate, *errorMessageArgs))
    }

    private fun format(template: String, @Nullable vararg args: Any): String {
        var template = template
        template = template
        val builder = StringBuilder(template.length + 16 * args.size)
        var templateStart = 0

        var i: Int
        var placeholderStart: Int
        i = 0
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

}