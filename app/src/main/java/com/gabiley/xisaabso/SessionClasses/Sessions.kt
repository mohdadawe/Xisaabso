package com.gabiley.diwaankagabiley.SessionClasses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class Sessions(
    val IDSessionClass: String,
    val UserSessionClass: String,
    val AccountStatusSessionClass: String,
    val AccountTypeClass: String,

): Parcelable