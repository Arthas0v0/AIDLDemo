package com.arthas.aidldemo

import android.os.Parcel
import android.os.Parcelable

class Person(var name: String? = "", var age: Int = 0) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    )

    fun readFromParcel(reply: Parcel) {
        name = reply.readString().toString()
        age = reply.readInt()
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Person(name=$name, age=$age)"
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }


}