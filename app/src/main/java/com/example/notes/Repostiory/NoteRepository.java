package com.example.notes.Repostiory;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notes.Dao.NoteDao;
import com.example.notes.Database.NotesDataBase;
import com.example.notes.Model.Notes;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Notes>> allNotes;

   public NoteRepository(Application application) {
        NotesDataBase db = NotesDataBase.getDataBase(application);
        noteDao = db.getNoteDao();
        allNotes = noteDao.getAllNotes();
    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Notes>> getAllNotes() {
        return allNotes;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
   public void insert(Notes notes){
       //We need to not run the insert on the main thread, so we use the ExecutorService
        // we created in the NotesDatabase to perform the insert on a background thread.
       NotesDataBase.dataBaseWriteExecutor.execute(() -> noteDao.Insert(notes));
   }

   public void delete(Notes notes){
       NotesDataBase.dataBaseWriteExecutor.execute(() -> noteDao.Delete(notes));
    }


}
