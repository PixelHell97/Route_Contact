package com.pixel.routecontact

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactData(
    val name: String,
    val phoneNum: String,
    val description: String
): Parcelable
