package io.github.yunato.judotimer.model.dto

import android.os.Parcel
import android.os.Parcelable

data class Player(val name: String,
                  val belongs: String):Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Player> = object : Parcelable.Creator<Player> {
            override fun createFromParcel(source: Parcel): Player = source.run {
                Player(readString(), readString())
            }

            override fun newArray(size: Int): Array<Player?> = arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.run {
            writeString(name)
            writeString(belongs)
        }
    }
}
