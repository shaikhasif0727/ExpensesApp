package com.example.expensesapp.paresantation

import androidx.lifecycle.viewModelScope
import com.example.expensesapp.domain.use_cases.ValidateEmail
import com.example.expensesapp.domain.use_cases.ValidatePassword
import com.example.expensesapp.domain.use_cases.ValidateRepeatedPassword
import com.example.expensesapp.domain.use_cases.ValidateTerms
import com.example.expensesapp.paresantation.base.BaseViewModel
import com.example.expensesapp.paresantation.common.UiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateRepeatedPassword: ValidateRepeatedPassword,
    private val validateTerms: ValidateTerms,
) : BaseViewModel<RegistrationFormContract.Event, RegistrationFormContract.State, RegistrationFormContract.Effect>() {

    override fun createInitialState(): RegistrationFormContract.State {
        return RegistrationFormContract.State()
    }

    override fun handleEvent(event: RegistrationFormContract.Event) {
        when(event){
            is RegistrationFormContract.Event.EmailChanged -> {
                setState {
                    copy(
                        email = event.email,
                        emailError = null
                    )
                }
            }

            is RegistrationFormContract.Event.PasswordChanged -> {
                setState {
                    copy(
                        password = event.password,
                        passwordError = null
                    )
                }
            }
            is RegistrationFormContract.Event.RepeatedPasswordChanged -> {
                setState {
                    copy(
                        repeatedPassword = event.repeatedPassword,
                        repeatedPasswordError = null
                    )
                }
            }

            is RegistrationFormContract.Event.AcceptTerms -> {
                setState {
                    copy(
                        acceptedTerms = event.isAccepted,
                        termsError = null
                    )
                }
            }
            RegistrationFormContract.Event.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(uiState.email)
        val passwordResult = validatePassword.execute(uiState.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(uiState.password,uiState.repeatedPassword)
        val termsResult = validateTerms.execute(uiState.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termsResult
        ).any { !it.successful }

        if(hasError){
            setState {
                copy(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage,
                    repeatedPasswordError = repeatedPasswordResult.errorMessage,
                    termsError = termsResult.errorMessage
                )
            }
            return
        }

        viewModelScope.launch {
            setEffect { RegistrationFormContract.Effect.Success }
        }
    }


}