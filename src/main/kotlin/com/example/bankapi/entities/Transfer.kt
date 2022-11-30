package com.example.bankapi.entities

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "transfer")
class Transfer(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    val value: Double?,

    // 1 - Credit
    // 2 - Debit
    val type: Int?,

    @field:OneToOne
    @field:JoinColumn(name = "account")
    val account: Account?
)