package com.example.cyberpunkhelperv2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyberpunkhelperv2.R;
import com.example.cyberpunkhelperv2.database.models.Note;
import com.example.cyberpunkhelperv2.fragments.AlertDialogFragment;
import com.example.cyberpunkhelperv2.fragments.ListFragment;
import com.example.cyberpunkhelperv2.fragments.characterFragment;
import com.example.cyberpunkhelperv2.viewModels.NotesListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;

import static com.example.cyberpunkhelperv2.fragments.AlertDialogFragment.ID_LONG;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnListFragmentInteractionListener,
        AlertDialogFragment.AlertDialogListener {

    com.example.cyberpunkhelperv2.fragments.characterFragment characterFragment;
    FragmentTransaction mFragmentTransaction;

    NotesListViewModel mNotesListViewModel;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        characterFragment = new characterFragment();

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        mNotesListViewModel = ViewModelProviders.of(this).get(NotesListViewModel.class);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, new ListFragment())
                .addToBackStack("list")
                .commit();


        // Initialize floating action button
        fab =  findViewById(R.id.fab);
        //show add notes dialogue
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void changeFragment(int id) {
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (id) {
            case R.id.app_bar_add_character:
                mFragmentTransaction.replace(R.id.container, characterFragment);
                break;
        }
        mFragmentTransaction.commit();
    }

    public void showDialog() {
        //fab.hide();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_note_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final EditText editTextName = dialog.findViewById(R.id.editTextName);
        final EditText editTextHandle = dialog.findViewById(R.id.editTextHandle);
        final EditText ediTextRole = dialog.findViewById(R.id.editTextRole);
        final EditText editTextAge = dialog.findViewById(R.id.editTextAge);
        final EditText editTextChPoints = dialog.findViewById(R.id.editTextChPoints);
        TextView textViewAdd = dialog.findViewById(R.id.textViewAdd);
        TextView textViewCancel = dialog.findViewById(R.id.textViewCancel);
        textViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = editTextName.getText().toString();
                String Handle = editTextHandle.getText().toString();
                String Role = ediTextRole.getText().toString();
                String Age = editTextAge.getText().toString();
                String ChPoints = editTextChPoints.getText().toString();

                Date createdAt = Calendar.getInstance().getTime();
                //add note
                mNotesListViewModel.addNote(new Note(Name, Handle, Role, Age, ChPoints, createdAt));
                fab.show();
                dialog.dismiss();
            }
        });
        textViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //fab.show();
            }
        });

        dialog.show();

    }


    @Override
    public void onListClickNote(Note note) {
        Toast.makeText(this, note.getHandle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListFragmentDeleteItemById(long idItem) {
        Bundle bundle = new Bundle();
        bundle.putLong(ID_LONG, idItem);

        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        alertDialogFragment.setArguments(bundle);
        alertDialogFragment.show(getSupportFragmentManager(), "Allert");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, long idItem) {
        mNotesListViewModel.deleteById(idItem);
        Toast.makeText(this, getString(R.string.message_delete), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this, getString(R.string.message_cancel), Toast.LENGTH_SHORT).show();

    }
}
