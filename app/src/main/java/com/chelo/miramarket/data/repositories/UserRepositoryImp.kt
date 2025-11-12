package com.chelo.miramarket.data.repositories

import com.chelo.miramarket.data.remote.AuthDataSource
import com.chelo.miramarket.domain.model.User
import com.chelo.miramarket.domain.repositories.UserRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val remote: AuthDataSource,
) : UserRepository {

    override suspend fun loginWithGoogle(credential: AuthCredential): User {
        val firebaseUser = remote.loginWithGoogle(credential)
        return firebaseUser.toDomain()
    }

}


private fun FirebaseUser.toDomain() = User(
    id = uid,
    email = email ?: "",
    name = displayName ?: "",
)