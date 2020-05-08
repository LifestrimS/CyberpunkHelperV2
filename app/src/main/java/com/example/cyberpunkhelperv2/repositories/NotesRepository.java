package com.example.cyberpunkhelperv2.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.cyberpunkhelperv2.database.Daos.NoteDao;
import com.example.cyberpunkhelperv2.database.NoteDatabase;
import com.example.cyberpunkhelperv2.database.models.Note;

/**
 * This class will serve as true source of data. Create methods to get data from Room database or
 * any other database like firebase. Inside create LiveData of List of all notes and method to add
 * note inside Room Database as shown below.
 */

//Notes repository
public class NotesRepository {
    //Live Data of List of all notes
    private LiveData<List<Note>> mAllNotes;
    //Define Notes Dao
    private NoteDao mNoteDao;

    public NotesRepository(@NonNull Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getDatabase(application);
        //init Notes Dao
        mNoteDao = noteDatabase.noteDao();
        //get all notes
        mAllNotes = mNoteDao.getAllNotes();
    }
    //method to get all notes
    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    //method to add note
    public void addNote(Note note) {
        new AddNote(mNoteDao).execute(note);
    }

    //Async task to add note
    public class AddNote extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncTaskDao;
        AddNote(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.insertNote(notes[0]);
            return null;
        }
    }

    public void deleteNote(Note note) {
        new deleteAsyncTask(mNoteDao).execute(note);
    }

    private static class deleteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao mAsyncTaskDao;
        deleteAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.deleteNote(params[0]);
            return null;
        }
    }

    public void deleteById(Long idItem) {
        new deleteByIdAsyncTask(mNoteDao).execute(idItem);
    }

    private static class deleteByIdAsyncTask extends AsyncTask<Long, Void, Void> {
        private NoteDao mAsyncTaskDao;
        deleteByIdAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Long... params) {
            mAsyncTaskDao.deleteById(params[0]);
            return null;
        }
    }
}
