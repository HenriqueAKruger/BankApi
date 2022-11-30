package com.example.bankapi.repositores

import com.example.bankapi.entities.Account
import com.example.bankapi.entities.Transfer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TransferRepository: JpaRepository<Transfer, Int> {

    @Query("FROM Account WHERE id = :id")
    fun findAccountById(@Param("id") id: Int): Account?
}