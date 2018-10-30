package com.example.demo.helper

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

object Helper {

    @JvmStatic
    fun main(args: Array<String>) {
        val passwordEncoder = BCryptPasswordEncoder()
        val password = passwordEncoder.encode("bob")
        println(password)
    }
}
