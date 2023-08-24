package com.example.expensesapp.paresantation

import com.example.expensesapp.paresantation.common.UiEffect
import com.example.expensesapp.paresantation.common.UiEvent
import com.example.expensesapp.paresantation.common.UiState

class RegistrationFormContract {

    data class State(
        val email:String = "",
        val emailError: String? = null,
        val password: String = "",
        val passwordError: String? = null,
        val repeatedPassword: String = "",
        val repeatedPasswordError: String? = null,
        val acceptedTerms:Boolean = false,
        val termsError: String? = null
    ) : UiState

    sealed class Event() : UiEvent{

        data class EmailChanged(val email: String): Event()
        data class PasswordChanged(val password: String): Event()
        data class RepeatedPasswordChanged(val repeatedPassword: String): Event()
        data class AcceptTerms(val isAccepted: Boolean): Event()

        object Submit: Event()
    }

    sealed class Effect() : UiEffect{

        object Success: Effect()
    }
}