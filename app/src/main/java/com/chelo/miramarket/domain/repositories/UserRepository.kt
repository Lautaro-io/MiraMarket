package com.chelo.miramarket.domain.repositories

import com.chelo.miramarket.domain.model.User
import com.google.firebase.auth.AuthCredential
import javax.inject.Inject

interface UserRepository {

    suspend fun loginWithGoogle(credential: AuthCredential) : User


}