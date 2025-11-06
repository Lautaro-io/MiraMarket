package com.chelo.miramarket.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.miramarket.data.repositories.UserRepositoryImp
import com.chelo.miramarket.domain.model.User
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepositoryImp) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState())
    val state = _state.asStateFlow()

    fun signWithGoogle(credential: AuthCredential, navigate: () -> Unit){
        viewModelScope.launch {
            _state.value.copy(isLoading = true)
            val user = userRepository.loginWithGoogle(credential)
            _state.value.copy(currentUser = user )
            navigate()
            _state.value.copy(isLoading = false)


        }
    }

}

data class LoginState (
    val isLoading : Boolean = false ,
    val currentUser : User? = null
)