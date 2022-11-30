package com.example.bankapi.controllers

import com.example.bankapi.entities.Account
import com.example.bankapi.entities.Transfer
import com.example.bankapi.repositores.TransferRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/transfer")
class TransferController {

    @Autowired
    lateinit var repository: TransferRepository

    @GetMapping
    fun index(): List<Transfer> = repository.findAll()

    @GetMapping("/{id}")
    fun show(@PathVariable("id") id: Int): Transfer = repository.findById(id).orElseThrow { EntityNotFoundException() }

    @PostMapping
    fun create(@RequestBody transaction: Transfer): Transfer {

        var account: Account? = null

        transaction.account?.let {
            account = repository.findAccountById(it.id)
        }

        if (account == null) throw Exception("Account not found")

        return repository.save(transaction)
    }
}