package com.example.cyberpunkhelperv2.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cyberpunkhelperv2.fragments.ListFragment;
import com.example.cyberpunkhelperv2.database.models.Note;

import java.util.ArrayList;
import java.util.List;

import com.example.cyberpunkhelperv2.R;
import com.example.cyberpunkhelperv2.utils.DateConverter;

/**
 * This class will serve as adapter for recyclerview. Create viewholder and bind data to the view
 * inside onCreateViewHolder() and onBindViewHolder() methods respectively as shown below.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    //Create list of notes
    private List<Note> mNoteList;
    private final ListFragment.OnListFragmentInteractionListener mListener;

    public NotesAdapter(ListFragment.OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    public void setListData(List<Note> noteList) {
        if (mNoteList == null) {
            mNoteList = new ArrayList<>();
        }
        mNoteList.clear();
        mNoteList.addAll(noteList);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_row_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.note = mNoteList.get(position);
        holder.mNoteName.setText(holder.note.getName());
        holder.mNoteRole.setText(holder.note.getRole());
        holder.createdAt.setText(DateConverter.getDayMonth(holder.note.getCreatedAt()));

        //create random color and set it
        int color = Color.argb(255, 0, 255, 255);
        holder.backStrip.setBackgroundColor(color);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mListener) {
                    mListener.onListClickNote(holder.note);
                }
            }
        });

        final long noteId = holder.note.getId();
        holder.mImageDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListFragmentDeleteItemById(noteId);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mNoteList != null) {
            return mNoteList.size();
        } else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView createdAt;
        private final TextView mNoteName;
        private final TextView mNoteRole;
        private final ImageView mImageDeleteView;

        private Note note;
        FrameLayout backStrip;

        private ViewHolder(View view) {
            super(view);
            mView = view;
            mNoteName = view.findViewById(R.id.noteName);
            mNoteRole = view.findViewById(R.id.noteRole);
            createdAt = view.findViewById(R.id.createdAt);
            mImageDeleteView = view.findViewById(R.id.imgDelete);
            backStrip = view.findViewById(R.id.backStrip);

        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mNoteName.getText() + "'";
        }
    }
}
