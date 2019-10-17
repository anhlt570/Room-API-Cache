package com.anle.democache.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.anle.democache.models.UserInfo

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): LiveData<List<UserInfo>>

    @Query("SELECT * FROM users WHERE id=:id LIMIT 1")
    fun getUserLiveData(id: Int): LiveData<UserInfo>

    @Query("SELECT * FROM users WHERE id=:id LIMIT 1")
    fun getUser(id: Int): UserInfo

    @Insert(onConflict = REPLACE)
    fun insertUsers(bookEntities: List<UserInfo>)

    @Insert(onConflict = REPLACE)
    fun insertUser(userInfo: UserInfo)

    @Query("DELETE FROM users")
    fun clearUsers()
}