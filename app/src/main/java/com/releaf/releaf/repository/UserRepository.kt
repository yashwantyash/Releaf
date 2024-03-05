//package com.releaf.releaf.repository
//
//import com.releaf.releaf.database.ReleafDatabase
//import com.releaf.releaf.models.User
//
//class UserRepository(private val db: ReleafDatabase) {
//
//    // suspend make sure all the function runs on background thread
//    suspend fun insertUser(user: User) = db.getUserDao().insertUser(user)
//    suspend fun getUserCount(user: User) = db.getUserDao().getUserCount()
////    suspend fun updateUser(user: UserRoom) = db.getUserDao().updateUser(user)
//
////    fun getAllUser() = db.getUserDao().getAllUsers()
//}