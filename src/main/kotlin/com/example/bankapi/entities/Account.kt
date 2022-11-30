package com.example.bankapi.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "account")
class Account(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    var balance: Double? = 0.00,

    @field:OneToOne
    @field:JoinColumn(name = "person")
    var person: Person?
)