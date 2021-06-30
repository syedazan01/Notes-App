package com.example.notes.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.notes.Model.Notes;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void Insert(Notes note);

    @Delete
    void Delete(Notes note);

    @Query("Select * FROM notes_table order by id ASC")
    LiveData<List<Notes>> getAllNotes();

}
