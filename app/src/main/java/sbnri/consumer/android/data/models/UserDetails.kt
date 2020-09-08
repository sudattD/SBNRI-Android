package sbnri.consumer.android.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDetails(
        @SerializedName("first_name")
        val firstName: String?,
        @SerializedName("last_name")
        val lastname: String?,
        @SerializedName("username")
        val userName : String?,
        @SerializedName("photo_url")
        val photoURL : String?,
        @SerializedName("location")
        val location: Location,
        @SerializedName("has_mpin")
        val hasMPin : Boolean=false,
        @SerializedName("token")
        val token: String?
) : Parcelable

@Parcelize
data class Location(
        @SerializedName("city")
        val cityName:String?,
        @SerializedName("country")
        val country:String?,
        @SerializedName("lat")
        val latitude:String?,
        @SerializedName("long")
        val longitude:String?
) : Parcelable