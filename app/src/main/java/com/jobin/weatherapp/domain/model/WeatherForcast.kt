package com.jobin.weatherapp.domain.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "WeatherForcast")
data class WeatherForcast(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("dt")
    @ColumnInfo(name = "dt")
    val dt: Int,

    @Expose
    @SerializedName("dtTxt")
    @ColumnInfo(name = "dtTxt")
    val dtTxt: String,

    @Expose
    @SerializedName("tempMax")
    @ColumnInfo(name = "tempMax")
    val tempMax: Double,

    @Expose
    @SerializedName("tempMin")
    @ColumnInfo(name = "tempMin")
    val tempMin: Double,

    @Expose
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int,

    @Expose
    @SerializedName("main")
    @ColumnInfo(name = "main")
    val main: String


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(dt)
        parcel.writeString(dtTxt)
        parcel.writeDouble(tempMax)
        parcel.writeDouble(tempMin)
        parcel.writeInt(id)
        parcel.writeString(main)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherForcast> {
        override fun createFromParcel(parcel: Parcel): WeatherForcast {
            return WeatherForcast(parcel)
        }

        override fun newArray(size: Int): Array<WeatherForcast?> {
            return arrayOfNulls(size)
        }
    }

}