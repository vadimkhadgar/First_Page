package com.mobile.instagramfirstpage.utils

fun Int.commentUtil(): String {
    return when (this) {
        1 -> "View 1 comment"
        else -> "View all $this comments"
    }
}