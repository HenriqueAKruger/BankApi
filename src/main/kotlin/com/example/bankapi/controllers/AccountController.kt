package com.example.bankapi.controllers

import com.example.bankapi.entities.Account
import com.example.bankapi.entities.Person
import com.example.bankapi.repositores.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/account")
class AccountController {

    @Autowired
    lateinit var repository: AccountRepository

    @GetMapping
    fun index(): List<Account> = repository.findAll()

    @GetMapping("/{id}")
    fun show(@PathVariable("id") id: Int): Account = repository.findById(id).orElseThrow { EntityNotFoundException() }

    @PostMapping
    fun create(@RequestBody account: Account): Account {
        var person: Person? = null

        account.person?.let {
            person = repository.findPersonById(it.id)
        }

        if (person == null) throw Exception("Person not found")

        return repository.save(account)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Int, @RequestBody newAccount: Account): Account {

        val account = repository.findById(id).orElseThrow { EntityNotFoundException() }

        account.apply {
            this.balance = newAccount.balance
        }

        return repository.save(account)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int) {

        val account = repository.findById(id).orElseThrow { EntityNotFoundException() }

        repository.delete(account)
    }
}