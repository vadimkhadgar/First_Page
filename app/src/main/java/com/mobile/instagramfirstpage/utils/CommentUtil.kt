package com.mobile.instagramfirstpage.utils

object CommentUtil {
    fun intCommentToString(quantityOfComments: Int): String {
        return when (quantityOfComments) {
            1 -> "View 1 comment"
            else -> "View all $quantityOfComments comments"
        }
    }
}