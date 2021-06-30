package com.example.notes.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.notes.Dao.NoteDao;
import com.example.notes.Model.Notes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Notes.class},version = 1,exportSchema = false)
public abstract class NotesDataBase extends RoomDatabase {

   public abstract NoteDao getNoteDao();
   private static volatile NotesDataBase INSTANCE;
   private static volatile int NUMBER_OF_THREADS = 4;
   public static final ExecutorService dataBaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

  public static NotesDataBase getDataBase(final Context context){
      if(INSTANCE == null){
         synchronized (NotesDataBase.class){
            if (INSTANCE == null){
               INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                       NotesDataBase.class,"notes_database").build();
            }
         }
      }
      return INSTANCE;
   }
}
