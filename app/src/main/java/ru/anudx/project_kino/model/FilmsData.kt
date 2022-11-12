package ru.anudx.project_kino.model

import android.os.Parcel
import android.os.Parcelable


@Parcelize
data class FilmsData(val id: Int, val title: String?, val description: String?, val image: Int): Parcelable
{
    constructor(parcel: Parcel): this (
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeInt(this.id)
        p0?.writeString(this.title)
        p0?.writeString(this.description)
        p0?.writeInt(this.image)
    }

    companion object CREATOR : Parcelable.Creator<FilmsData> {
        override fun createFromParcel(parcel: Parcel): FilmsData {
            return FilmsData(parcel)
        }

        override fun newArray(size: Int): Array<FilmsData?> {
            return arrayOfNulls(size)
        }
    }

}

annotation class Parcelize


