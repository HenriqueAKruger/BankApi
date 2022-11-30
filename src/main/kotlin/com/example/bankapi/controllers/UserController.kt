package com.example.bankapi.controllers

import com.example.bankapi.entities.Account
import com.example.bankapi.entities.User
import com.example.bankapi.repositores.UserRepository
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
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var repository: UserRepository

    @GetMapping
    fun index(): List<User> = repository.findAll()

    @GetMapping("/{id}")
    fun show(@PathVariable("id") id: Int): User = repository.findById(id).orElseThrow { EntityNotFoundException() }

    @PostMapping
    fun create(@RequestBody user: User): User {
        var account: Account? = null

        user.account?.let {
            account = repository.findAccountById(it.id)
        }

        if (account == null) throw Exception("Account not found")

        return repository.save(user)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Int, @RequestBody newUser: User): User {

        val user = repository.findById(id).orElseThrow { EntityNotFoundException() }

        user.apply {
            this.login = newUser.login
            this.password = newUser.password
        }

        return repository.save(user)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int) {

        val user = repository.findById(id).orElseThrow { EntityNotFoundException() }

        repository.delete(user)
    }
}