package com.example.highkills.trans

import android.os.Parcel
import android.os.Parcelable


class ParWay:Parcelable {
    var name = "Xiao ji ji"
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
    }

    companion object CREATOP:Parcelable.Creator<ParWay>{
        override fun createFromParcel(source: Parcel?): ParWay {
            val parWay = ParWay()
            parWay.name = source?.readString() ?: ""
            return parWay
        }

        override fun newArray(size: Int): Array<ParWay?> {
            return arrayOfNulls(size)
        }
    }
}

