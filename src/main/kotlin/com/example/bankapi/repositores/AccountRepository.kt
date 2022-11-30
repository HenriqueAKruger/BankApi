package com.example.bankapi.repositores

import com.example.bankapi.entities.Account
import com.example.bankapi.entities.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: JpaRepository<Account, Int> {

    @Query("FROM Person WHERE id = :id")
    fun findPersonById(@Param("id") id: Int): Person?
}