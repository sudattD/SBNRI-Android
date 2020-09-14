package sbnri.consumer.android.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AllBanksData(
    @SerializedName("banks")
    val banks: List<Bank>
) : Parcelable

@Parcelize
data class Bank(
    @SerializedName("banks")
    val subBanks: List<SubBank>,
    @SerializedName("subtitle")
    val subtitle: String?="",
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String
) : Parcelable

@Parcelize
data class SubBank(
    @SerializedName("bankBackgroundColor")
    val bankBackgroundColor: BankBackgroundColor,
    @SerializedName("bankDescription")
    val bankDescription: String,
    @SerializedName("bankFDRate")
    val bankFDRate: String,
    @SerializedName("bankHighlight")
    val bankHighlight: String,
    @SerializedName("bankImage")
    val bankImage: String,
    @SerializedName("bankName")
    val bankName: String,
    @SerializedName("bankSavingsRate")
    val bankSavingsRate: String,
    @SerializedName("bankSize")
    val bankSize: String,
    @SerializedName("fdBookLink")
    val fdBookLink: String,
    @SerializedName("fdProcess")
    val fdProcess: List<FdProces>,
    @SerializedName("id")
    val id: String,
    @SerializedName("numberOfAccounts")
    val numberOfAccounts: String,
    @SerializedName("openingTime")
    val openingTime: String,
    @SerializedName("process")
    val process: List<Process>
) : Parcelable

@Parcelize
data class BankBackgroundColor(
    @SerializedName("blue")
    val blue: Int,
    @SerializedName("green")
    val green: Int,
    @SerializedName("opacity")
    val opacity: Double,
    @SerializedName("red")
    val red: Int,
    @SerializedName("hex")
    val hex:String?
) : Parcelable

@Parcelize
data class FdProces(
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String
) : Parcelable

@Parcelize
data class Process(
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String
) : Parcelable