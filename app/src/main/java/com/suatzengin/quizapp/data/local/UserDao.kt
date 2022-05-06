package com.suatzengin.quizapp.data.local

import androidx.room.*
import com.suatzengin.quizapp.domain.model.User
import com.suatzengin.quizapp.data.local.relations.UserWithQuizzes

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(user: User)

    @Query("SELECT * FROM user WHERE mail = :mail AND password = :password")
    suspend fun login(mail: String, password: String): User?

    @Transaction
    @Query("SELECT * FROM user WHERE user_id = :id")
    fun getUsersWithQuizzes(id: Int): List<UserWithQuizzes>

}
