package com.chelo.miramarket.data.remote

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSource @Inject constructor(private val auth: FirebaseAuth) {

    private val currentUser = auth.currentUser

    suspend fun loginWithGoogle(credentials: AuthCredential): FirebaseUser {
        return try {
            val result = auth.signInWithCredential(credentials).await()
            result.user ?: throw Exception("User is null")
        } catch (e : Exception) {
            throw e
        }
    }


}