package com.gabiley.diwaankagabiley.SessionClasses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class PasswordSessions(
    val userDetailsIDClass: String,
    val accountTypeClass: String,
    val branchClass: String,
    val companyIDClass: String,
    val loginIDClass: String
): Parcelable