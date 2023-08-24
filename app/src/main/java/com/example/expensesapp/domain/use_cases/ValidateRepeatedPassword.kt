package com.example.expensesapp.domain.use_cases

import javax.inject.Inject

class ValidateRepeatedPassword @Inject constructor() {

    fun execute(password: String,repeatedPassword: String) : ValidationResult{
        if (repeatedPassword.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "The password don't match"
            )
        }
        if(password != repeatedPassword){
            return ValidationResult(
                successful = false,
                errorMessage = "The password don't match"
            )
        }
        return ValidationResult(
            successful = false
        )
    }

}