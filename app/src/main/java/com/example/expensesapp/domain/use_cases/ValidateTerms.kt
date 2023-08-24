package com.example.expensesapp.domain.use_cases

import javax.inject.Inject

class ValidateTerms @Inject constructor() {

    fun execute(acceptedTerms: Boolean) : ValidationResult{
        if(!acceptedTerms){
            return ValidationResult(
                successful = false,
                errorMessage = "Please accept the terms"
            )
        }

        return ValidationResult(
            successful = false
        )
    }

}