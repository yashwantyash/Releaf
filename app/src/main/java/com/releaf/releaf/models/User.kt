package com.releaf.releaf.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.releaf.releaf.utility.Constants.USER_TABLE

@Entity(tableName = USER_TABLE)
data class User(

    @PrimaryKey
    val id: Long=0L,
    val fullName:String,
    val phone:String,
    val email:String
)

