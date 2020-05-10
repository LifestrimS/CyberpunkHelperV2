package com.example.cyberpunkhelperv2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyberpunkhelperv2.R;
import com.example.cyberpunkhelperv2.database.models.Note;
import com.example.cyberpunkhelperv2.fragments.AlertDialogFragment;
import com.example.cyberpunkhelperv2.fragments.ListFragment;
import com.example.cyberpunkhelperv2.fragments.characterFragment;
import com.example.cyberpunkhelperv2.viewModels.NotesListViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import static com.example.cyberpunkhelperv2.fragments.AlertDialogFragment.ID_LONG;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnListFragmentInteractionListener,
        AlertDialogFragment.AlertDialogListener {

    com.example.cyberpunkhelperv2.fragments.characterFragment characterFragment;

    NotesListViewModel mNotesListViewModel;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        characterFragment = new characterFragment();

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        BottomAppBar bar = findViewById(R.id.bottomAppBar);
        bar.replaceMenu(R.menu.bottom_menu);
        bar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showDialog();
                return true;
            }
        });

        mNotesListViewModel = ViewModelProviders.of(this).get(NotesListViewModel.class);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, new ListFragment())
                .addToBackStack("list")
                .commit();


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropDices();
            }
        });
    }

    public void dropDices() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dice_dialog);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        String[] dices = {"2", "6", "10", "20"};
        String[] numberDices = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        final Spinner spinnerDices = dialog.findViewById(R.id.spinnerDices);
        ArrayAdapter<String> adapterDices = new ArrayAdapter<String>(this, R.layout.spinner_row, dices);
        spinnerDices.setAdapter(adapterDices);

        final Spinner spinnerNumberDices = dialog.findViewById(R.id.spinnerNumberDices);
        ArrayAdapter<String> adapterNumberDices = new ArrayAdapter<>(this, R.layout.spinner_row, numberDices);
        spinnerNumberDices.setAdapter(adapterNumberDices);

        TextView dropDice = dialog.findViewById(R.id.twDiceCount);

        Button buttonDrop = dialog.findViewById(R.id.buttonDrop);
        buttonDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedDices = Integer.parseInt(spinnerDices.getSelectedItem().toString());
                int selectedNumberDices = Integer.parseInt(spinnerNumberDices.getSelectedItem().toString());
                Log.d("TAG", "selectedDice: " + selectedDices);
                Log.d("TAG", "selectedNumberDices: " + selectedNumberDices);

                Random random = new Random();
                int sum = 0;
                for (int i = 0; i < selectedNumberDices; i++) {
                    sum += random.nextInt(selectedDices) + 1;
                }
                dropDice.setText(String.valueOf(sum));

            }
        });

        dialog.show();
    }

    public void showDialog() {
        //fab.hide();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_character_dialog);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.open_character_dialog);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final EditText editTextName = dialog.findViewById(R.id.editTextName);
        final EditText editTextHandle = dialog.findViewById(R.id.editTextHandle);
        final EditText editTextRole = dialog.findViewById(R.id.editTextRole);
        final EditText editTextAge = dialog.findViewById(R.id.editTextAge);
        final EditText editTextChPoints = dialog.findViewById(R.id.editTextChPoints);
        TextView textViewSave = dialog.findViewById(R.id.textViewSave);

        editTextName.setText(note.getName());
        editTextHandle.setText(note.getHandle());
        editTextRole.setText(note.getRole());
        editTextChPoints.setText(note.getChPoints());
        editTextAge.setText(note.getAge());


        textViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = editTextName.getText().toString();
                String Handle = editTextHandle.getText().toString();
                String Role = editTextRole.getText().toString();
                String Age = editTextAge.getText().toString();
                String ChPoints = editTextChPoints.getText().toString();

                Date createdAt = Calendar.getInstance().getTime();
                //Update note
                mNotesListViewModel.deleteById((long)note.getId());
                mNotesListViewModel.addNote(new Note(Name, Handle, Role, Age, ChPoints, createdAt));

                fab.show();
                dialog.dismiss();
            }
        });
        dialog.show();

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
