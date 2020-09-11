package com.example.myontacts.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_data")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val holder: String,
    val contact: String,
    val resourceId: Int
) : Parcelable