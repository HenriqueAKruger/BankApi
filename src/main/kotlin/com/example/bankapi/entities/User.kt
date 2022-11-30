package com.example.bankapi.entities

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "user")
class User(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    var login: String?,

    var password: String?,

    @field:OneToOne
    @field:JoinColumn(name = "account")
    var account: Account?
)