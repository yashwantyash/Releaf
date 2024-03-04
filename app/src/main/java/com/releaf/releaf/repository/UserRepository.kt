package com.releaf.releaf.repository

import com.releaf.releaf.database.ReleafDatabase
import com.releaf.releaf.models.UserRoom

class UserRepository(private val db: ReleafDatabase) {
    suspend fun insertUser(user: UserRoom) = db.getUserDao().insertUser(user)
    suspend fun updateUser(user: UserRoom) = db.getUserDao().updateUser(user)

//    fun getAllUser() = db.getUserDao().getAllUsers()
}