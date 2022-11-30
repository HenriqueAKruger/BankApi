package com.example.bankapi.repositores

import com.example.bankapi.entities.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: JpaRepository<Person, Int> {
}