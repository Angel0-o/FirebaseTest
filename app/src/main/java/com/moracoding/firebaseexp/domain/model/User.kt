package com.moracoding.firebaseexp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String = "",
    val email: String? = "",
    val tsLogIn: String? = "",
    val tsLogOut: String? = "",
) : Parcelable