package com.example.wakeup.ui.main.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.wakeup.ui.main.models.UserLocation;

import java.util.List;

@Dao
public interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserLocation location);

    @Update
    void update(UserLocation location);

    @Delete
    void delete(UserLocation location);

    @Query("SELECT * FROM user_location")
    LiveData<List<UserLocation>> getAll();

}
