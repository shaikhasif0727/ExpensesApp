package com.example.expensesapp.domain.use_cases

import android.util.Patterns
import javax.inject.Inject

class ValidatePassword @Inject constructor() {

    fun execute(password: String) : ValidationResult{
        if(password.length < 8){
            return ValidationResult(
                successful = false,
                errorMessage = "The password need to consist of at least 8 character"
            )
        }
        val containsLetterAndDigits = password.any{ it.isDigit()} &&
                password.any() { it.isLetter() }

        if(!containsLetterAndDigits){
            return ValidationResult(
                successful = false,
                errorMessage = "The password need to contain at least one letter and digit"
            )
        }

        return ValidationResult(
            successful = false
        )
    }

}