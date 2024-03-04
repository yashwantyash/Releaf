package com.releaf.releaf.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.releaf.releaf.utility.NavConst.USER_TABLE

@Entity(tableName = USER_TABLE)
data class UserRoom(

//    @PrimaryKey(autoGenerate = true)
//    val id: Long,

    val fullName:String,
    val phone:String,
    val email:String
)

