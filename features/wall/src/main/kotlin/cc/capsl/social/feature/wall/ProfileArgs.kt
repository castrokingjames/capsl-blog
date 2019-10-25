package cc.capsl.social.feature.wall

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileArgs(val userId: String, val userName: String) : Parcelable