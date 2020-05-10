package com.example.cyberpunkhelperv2.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.cyberpunkhelperv2.database.models.Note;
import com.example.cyberpunkhelperv2.repositories.NotesRepository;

/**
 * This ViewModel will contain LiveData of all notes and method to insert notes as defined below.
 */
public class NotesListViewModel extends AndroidViewModel {
    private LiveData<List<Note>> mAllNotes;
    NotesRepository mNotesRepository;

    public NotesListViewModel(@NonNull Application application) {
        super(application);
        mNotesRepository = new NotesRepository(application);
        mAllNotes = mNotesRepository.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void addNote(Note note) {
        mNotesRepository.addNote(note);
    }

    public void deleteItem(Note note) {
        mNotesRepository.deleteNote(note);
    }

    public void deleteById(Long idNote) {
        mNotesRepository.deleteById(idNote);
    }

}
