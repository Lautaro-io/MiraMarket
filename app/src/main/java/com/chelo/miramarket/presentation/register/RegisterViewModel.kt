package com.chelo.miramarket.presentation.register

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(

) :
    ViewModel() {


    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()


    fun onFieldChange(fieldType: FieldType , value: String){
        _state.update {
            when(fieldType){
                FieldType.STORE_NAME -> it.copy(storeName = value)
                FieldType.CATEGORY -> it.copy(category = value)
                FieldType.DESCRIPTION -> it.copy(description = value)
                FieldType.ADDRESS -> it.copy(address = value)
                FieldType.OWNER_NAME -> it.copy(ownerName = value)
                FieldType.PHONE_NUMBER -> it.copy(phoneNumber = value)
                FieldType.EMAIL -> it.copy(email = value)
                FieldType.OPENING_TIME -> it.copy(openingTime = value)
                FieldType.CLOSING_TIME -> it.copy(closingTime = value)
            }
        }


    }




}

enum class FieldType {
    STORE_NAME,
    CATEGORY,
    DESCRIPTION,
    ADDRESS,
    OWNER_NAME,
    PHONE_NUMBER,
    EMAIL,
    OPENING_TIME,
    CLOSING_TIME
}

data class RegisterState(
    val storeName: String = "",
    val category: String = "",
    val description: String = "",
    val address: String = "",
    val ownerName: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val openingTime: String = "",
    val closingTime: String = ""
)