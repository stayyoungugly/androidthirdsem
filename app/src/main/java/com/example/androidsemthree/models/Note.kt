package com.example.androidsemthree.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "date")
    var date: Date?,

    @ColumnInfo(name = "longitude")
    var longitude: Double?,

    @ColumnInfo(name = "latitude")
    var latitude: Double?
)
