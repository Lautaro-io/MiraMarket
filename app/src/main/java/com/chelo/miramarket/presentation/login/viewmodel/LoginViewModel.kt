package com.chelo.miramarket.presentation.login.viewmodel

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.miramarket.R
import com.chelo.miramarket.data.repositories.UserRepositoryImp
import com.chelo.miramarket.domain.model.User
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appContext: Application,
    private val userRepository: UserRepositoryImp
) : ViewModel() {

    private val credentialManager = CredentialManager.create(appContext)

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun signInWithGoogle(activity: Activity) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val googleIdOption = GetGoogleIdOption.Builder()
                    .setServerClientId(appContext.getString(R.string.tk))
                    .setFilterByAuthorizedAccounts(false)
                    .build()

                val signInOption = GetSignInWithGoogleOption.Builder(
                    appContext.getString(R.string.tk)
                ).build()

                val request = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .addCredentialOption(signInOption)
                    .build()

                val result = credentialManager.getCredential(
                    activity,
                    request
                )
                val credential = result.credential

                if (credential is CustomCredential &&
                    credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
                ) {
                    val idToken = GoogleIdTokenCredential
                        .createFrom(credential.data)
                        .idToken

                    val googleCredential = GoogleAuthProvider.getCredential(idToken, null)

                    val user = userRepository.loginWithGoogle(googleCredential)

                    _state.value =  _state.value.copy(
                        isLoading = false,
                        currentUser = user
                    )
                }
            } catch (e: Exception) {
                Log.e("CHELO", "Error en login: ${e.message}", e)
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Error desconocido"
                )
            }
        }
    }
}

data class LoginState(
    val isLoading: Boolean = false,
    val currentUser: User? = null,
    val error: String? = null
)
