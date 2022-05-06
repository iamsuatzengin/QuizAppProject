package com.suatzengin.quizapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val userId: Int,
    val name: String,
    val surname: String,
    val mail: String,
    val password: String,
    @ColumnInfo(name = "is_admin")
    val isAdmin: Boolean = false,
)
