package com.example.rankcheck

import java.security.MessageDigest

object Util {
    fun hashPassword(password: String): String? {
        return MessageDigest
            .getInstance("SHA-256")
            .digest(password.toByteArray())
            .fold("", { str, it -> str + "%02x".format(it) })
    }
}