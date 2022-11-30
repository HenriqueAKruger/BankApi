package com.example.bankapi.controllers

import com.example.bankapi.entities.Person
import com.example.bankapi.repositores.PersonRepository
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
@RequestMapping("/person")
class PersonController {

    @Autowired
    lateinit var repository: PersonRepository

    @GetMapping
    fun index(): List<Person> = repository.findAll()

    @GetMapping("/{id}")
    fun show(@PathVariable("id") id: Int): Person = repository.findById(id).orElseThrow { EntityNotFoundException() }

    @PostMapping
    fun create(@RequestBody person: Person) = repository.save(person)

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Int, @RequestBody newPerson: Person): Person {

        val person = repository.findById(id).orElseThrow { EntityNotFoundException() }

        person.apply {
            this.name = newPerson.name
            this.document = newPerson.document
            this.email = newPerson.email
        }

        return repository.save(person)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int) {

        val person = repository.findById(id).orElseThrow { EntityNotFoundException() }

        repository.delete(person)
    }
}