package com.practice.koinpractice.data

interface Service {
    fun httpText(): String
}

class ServiceImpl : Service {
    override fun httpText(): String = "ServiceImpl"
}

class ServiceFancyImpl : Service {
    override fun httpText(): String = "ServiceFancyImpl"
}