package com.practice.koinpractice.domain

import com.practice.koinpractice.data.Service

interface Repository {
    fun retrieve(): String
}

class RepositoryImpl(private val service: Service) : Repository {
    override fun retrieve() = service.httpText()
}
