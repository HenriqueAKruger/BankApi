package com.example.bankapi.entities

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "person")
class Person(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    var name: String?,

    var document: String?,

    // 1 - Pessoa jurídica
    // 2 - Pessoa física
    var type: Int?,

    var email: String?
)