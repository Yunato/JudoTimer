package io.github.yunato.judotimer.model.dto

import android.os.Parcel
import android.os.Parcelable

data class Game(val num: Int,
                val name: String,
                val grade: String,
                val rank: String,
                val subtitle: String) : Parcelable {

    companion object{
        @JvmField
        val CREATOR: Parcelable.Creator<Game> = object :Parcelable.Creator<Game>{
            override fun createFromParcel(source: Parcel): Game = source.run {
                Game(readInt(), readString(), readString(), readString(), readString())
            }

            override fun newArray(size: Int): Array<Game?> = arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.run{
            writeInt(num)
            writeString(name)
            writeString(grade)
            writeString(rank)
            writeString(subtitle)
        }
    }
}
