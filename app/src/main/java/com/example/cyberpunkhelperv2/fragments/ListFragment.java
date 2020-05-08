package com.example.cyberpunkhelperv2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cyberpunkhelperv2.R;
import com.example.cyberpunkhelperv2.adapters.NotesAdapter;
import com.example.cyberpunkhelperv2.database.models.Note;
import com.example.cyberpunkhelperv2.utils.Space;
import com.example.cyberpunkhelperv2.viewModels.NotesListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private NotesListViewModel mNotesListViewModel;
    private List<Note> mNoteList;
    private NotesAdapter mNotesAdapter;
    private OnListFragmentInteractionListener mListener;

    public void setListData(List<Note> noteList) {
        //if data changed, set new list to adapter of recyclerview

        if (mNoteList == null) {
            mNoteList = new ArrayList<>();
        }
        mNoteList.clear();
        mNoteList.addAll(noteList);

        if (mNotesAdapter != null) {
            mNotesAdapter.setListData(noteList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        Context context = view.getContext();
        //set recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewNotes);
        recyclerView.addItemDecoration(new Space(20));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mNotesAdapter = new NotesAdapter(mListener);

        if (mNoteList != null) {
            mNotesAdapter.setListData(mNoteList);
        }
        recyclerView.setAdapter(mNotesAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //get viewModel
        mNotesListViewModel = ViewModelProviders.of(this).get(NotesListViewModel.class);
        //bind to Livedata
        mNotesListViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> dataItems) {
                if (dataItems != null) {
                    setListData(dataItems);
                }
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnListFragmentInteractionListener {
        //onClick items of list
        void onListClickNote(Note note);

        //onClick delete item from list
        void onListFragmentDeleteItemById(long idNote);
    }
}
