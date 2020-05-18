package com.example.cyberpunkhelperv2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
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

import org.w3c.dom.Text;

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
                switch (item.getItemId()) {
                    case (R.id.add_random_character):
                        generateCharacter();
                        break;
                    case (R.id.add_character):
                        showDialog();
                        break;
                }
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
    
    public void generateCharacter() {
        String Name = "Name";
        String Handle = "Handle";
        String Role = "Role";
        String Age = "25";
        String ChPoints = "6";
//1d10
        Random random = new Random();
        String StatsInt = String.valueOf(random.nextInt(10) + 1);
        String StatsRef = String.valueOf(random.nextInt(10) + 1);
        String StatsTech = String.valueOf(random.nextInt(10) + 1);
        String StatsCool = String.valueOf(random.nextInt(10) + 1);
        String StatsAttr = String.valueOf(random.nextInt(10) + 1);
        String StatsLuck = String.valueOf(random.nextInt(10) + 1);
        String StatsMa = String.valueOf(random.nextInt(10) + 1);
        String StatsBody = String.valueOf(random.nextInt(10) + 1);
        String StatsEmp = String.valueOf(random.nextInt(10) + 1);
        String StatsSave = String.valueOf(random.nextInt(10) + 1);
        String StatsRun = String.valueOf(Integer.parseInt(StatsMa) * 3);
        String StatsLeap = String.valueOf(Integer.parseInt(StatsRun) / 4);
        String StatsLift = String.valueOf(Integer.parseInt(StatsBody) * 10);

        int btm = 0;
        switch (Integer.parseInt(StatsBody)) {
            case 1:
            case 2:
                btm = 0;
                break;
            case 3:
            case 4:
                btm = 1;
                break;
            case 5:
            case 6:
            case 7:
                btm = 2;
                break;
            case 8:
            case 9:
                btm = 3;
                break;
            default:
                btm = 4;
                break;
        }

        String StatsBtm = String.valueOf(btm);

        String armorHead = "2";
        String armorTorso = "2";
        String armorRArm = "2";
        String armorLArm = "2";
        String armorRLeg = "2";
        String armorLLeg = "2";

        Date createdAt = Calendar.getInstance().getTime();

        mNotesListViewModel.addNote(new Note(Name, Handle, Role, Age, ChPoints,
                StatsInt, StatsRef, StatsTech, StatsCool,
                StatsAttr, StatsLuck, StatsMa, StatsBody, StatsEmp,
                StatsRun, StatsLeap, StatsLift, StatsBtm, StatsSave,
                armorHead, armorTorso, armorRArm, armorLArm, armorRLeg, armorLLeg,
                createdAt));
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

        final EditText editTextStatsInt = dialog.findViewById(R.id.editTextInt);
        final EditText editTextStatsRef = dialog.findViewById(R.id.editTextRef);
        final EditText editTextStatsTech = dialog.findViewById(R.id.editTextTech);
        final EditText editTextStatsCool = dialog.findViewById(R.id.editTextCool);
        final EditText editTextStatsAttr = dialog.findViewById(R.id.editTextAttr);
        final EditText editTextStatsLuck = dialog.findViewById(R.id.editTextLuck);
        final EditText editTextStatsMa = dialog.findViewById(R.id.editTextMa);
        final EditText editTextStatsBody = dialog.findViewById(R.id.editTextBody);
        final EditText editTextStatsEmp = dialog.findViewById(R.id.editTextEmp);
        final EditText editTextStatsSave = dialog.findViewById(R.id.editTextSave);


        final TextView textViewRun = dialog.findViewById(R.id.editTextRun);
        final TextView textViewLeap = dialog.findViewById(R.id.editTextLeap);
        final TextView textViewLift = dialog.findViewById(R.id.editTextLift);
        final TextView textViewBtm = dialog.findViewById(R.id.editTextBtm);

        final EditText editTextArmorHead = dialog.findViewById(R.id.editTextArmorHead);
        final EditText editTextArmorTorso = dialog.findViewById(R.id.editTextArmorTorso);
        final EditText editTextArmorRArm = dialog.findViewById(R.id.editTextArmorRArm);
        final EditText editTextArmorLArm = dialog.findViewById(R.id.editTextArmorLArm);
        final EditText editTextArmorRLeg = dialog.findViewById(R.id.editTextArmorRLeg);
        final EditText editTextArmorLLeg = dialog.findViewById(R.id.editTextArmorLLeg);

        Button buttonAdd = dialog.findViewById(R.id.buttonAdd);
        Button buttonCancel = dialog.findViewById(R.id.buttonCancel);
        Button buttonGenerate = dialog.findViewById(R.id.buttonGenerate);

        buttonAdd.setEnabled(false);

        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (generateStats(editTextStatsMa, editTextStatsBody,
                        textViewRun, textViewLeap, textViewLift, textViewBtm)) {
                    buttonAdd.setEnabled(true);
                }
            }
        });


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = editTextName.getText().toString();
                String Handle = editTextHandle.getText().toString();
                String Role = ediTextRole.getText().toString();
                String Age = editTextAge.getText().toString();
                String ChPoints = editTextChPoints.getText().toString();

                String StatsInt = editTextStatsInt.getText().toString();
                String StatsRef = editTextStatsRef.getText().toString();
                String StatsTech = editTextStatsTech.getText().toString();
                String StatsCool = editTextStatsCool.getText().toString();
                String StatsAttr = editTextStatsAttr.getText().toString();
                String StatsLuck = editTextStatsLuck.getText().toString();
                String StatsMa = editTextStatsMa.getText().toString();
                String StatsBody = editTextStatsBody.getText().toString();
                String StatsEmp = editTextStatsEmp.getText().toString();

                String StatsSave = editTextStatsSave.getText().toString();

                String StatsRun = textViewRun.getText().toString();
                String StatsLeap = textViewLeap.getText().toString();
                String StatsLift = textViewLift.getText().toString();
                String StatsBtm = textViewBtm.getText().toString();

                String armorHead = editTextArmorHead.getText().toString();
                String armorTorso = editTextArmorTorso.getText().toString();
                String armorRArm = editTextArmorRArm.getText().toString();
                String armorLArm = editTextArmorLArm.getText().toString();
                String armorRLeg = editTextArmorRLeg.getText().toString();
                String armorLLeg = editTextArmorLLeg.getText().toString();

                Date createdAt = Calendar.getInstance().getTime();
                //add note
                mNotesListViewModel.addNote(new Note(Name, Handle, Role, Age, ChPoints,
                        StatsInt, StatsRef, StatsTech, StatsCool,
                        StatsAttr, StatsLuck, StatsMa, StatsBody, StatsEmp,
                        StatsRun, StatsLeap, StatsLift, StatsBtm, StatsSave,
                        armorHead, armorTorso, armorRArm, armorLArm, armorRLeg, armorLLeg,
                        createdAt));
                fab.show();
                dialog.dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public boolean generateStats(TextView editTextStatsMa, TextView editTextStatsBody,
                                 TextView textViewRun, TextView textViewLeap, TextView textViewLift, TextView textViewBtm) {

        if (editTextStatsMa.getEditableText().toString().length() == 0 ||
                editTextStatsBody.getEditableText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "U should enter MA and BODY!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            String StatsMa = editTextStatsMa.getText().toString();
            String StatsBody = editTextStatsBody.getText().toString();

            int run = Integer.parseInt(StatsMa) * 3;
            String StatsRun = Integer.toString(run);

            int leap = run / 4;
            String StatsLeap = Integer.toString(leap);

            int lift = Integer.parseInt(StatsBody) * 10;
            String StatsLift = Integer.toString(lift);

            int btm = 0;
            switch (Integer.parseInt(StatsBody)) {
                case 1:
                case 2:
                    btm = 0;
                    break;
                case 3:
                case 4:
                    btm = 1;
                    break;
                case 5:
                case 6:
                case 7:
                    btm = 2;
                    break;
                case 8:
                case 9:
                    btm = 3;
                    break;
                default:
                    btm = 4;
                    break;
            }
            String StatsBtm = Integer.toString(btm);

            textViewRun.setText(StatsRun);
            textViewLeap.setText(StatsLeap);
            textViewLift.setText(StatsLift);
            textViewBtm.setText(StatsBtm);
            return true;
        }
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

        final EditText editTextStatsInt = dialog.findViewById(R.id.editTextInt);
        final EditText editTextStatsRef = dialog.findViewById(R.id.editTextRef);
        final EditText editTextStatsTech = dialog.findViewById(R.id.editTextTech);
        final EditText editTextStatsCool = dialog.findViewById(R.id.editTextCool);
        final EditText editTextStatsAttr = dialog.findViewById(R.id.editTextAttr);
        final EditText editTextStatsLuck = dialog.findViewById(R.id.editTextLuck);
        final EditText editTextStatsMa = dialog.findViewById(R.id.editTextMa);
        final EditText editTextStatsBody = dialog.findViewById(R.id.editTextBody);
        final EditText editTextStatsEmp = dialog.findViewById(R.id.editTextEmp);
        final EditText editTextStatsSave = dialog.findViewById(R.id.editTextSave);

        final TextView textViewRun = dialog.findViewById(R.id.editTextRun);
        final TextView textViewLeap = dialog.findViewById(R.id.editTextLeap);
        final TextView textViewLift = dialog.findViewById(R.id.editTextLift);
        final TextView textViewBtm = dialog.findViewById(R.id.editTextBtm);

        final EditText editTextArmorHead = dialog.findViewById(R.id.editTextArmorHead);
        final EditText editTextArmorTorso = dialog.findViewById(R.id.editTextArmorTorso);
        final EditText editTextArmorRArm = dialog.findViewById(R.id.editTextArmorRArm);
        final EditText editTextArmorLArm = dialog.findViewById(R.id.editTextArmorLArm);
        final EditText editTextArmorRLeg = dialog.findViewById(R.id.editTextArmorRLeg);
        final EditText editTextArmorLLeg = dialog.findViewById(R.id.editTextArmorLLeg);

        Button buttonSave = dialog.findViewById(R.id.buttonSave);
        Button buttonGenerate = dialog.findViewById(R.id.buttonGenerate);

        editTextName.setText(note.getName());
        editTextHandle.setText(note.getHandle());
        editTextRole.setText(note.getRole());
        editTextChPoints.setText(note.getChPoints());
        editTextAge.setText(note.getAge());

        editTextStatsInt.setText(note.getStatInt());
        editTextStatsRef.setText(note.getStatRef());
        editTextStatsTech.setText(note.getStatTech());
        editTextStatsCool.setText(note.getStatCool());
        editTextStatsAttr.setText(note.getStatAttr());
        editTextStatsLuck.setText(note.getStatLuck());
        editTextStatsMa.setText(note.getStatMa());
        editTextStatsBody.setText(note.getStatBody());
        editTextStatsEmp.setText(note.getStatEmp());
        editTextStatsSave.setText(note.getStatSave());

        textViewRun.setText(note.getStatRun());
        textViewLeap.setText(note.getStatLeap());
        textViewLift.setText(note.getStatLift());
        textViewBtm.setText(note.getStatBtm());

        editTextArmorHead.setText(note.getArmorHead());
        editTextArmorTorso.setText(note.getArmorTorso());
        editTextArmorRArm.setText(note.getArmorRArm());
        editTextArmorLArm.setText(note.getArmorLArm());
        editTextArmorRLeg.setText(note.getArmorRLeg());
        editTextArmorLLeg.setText(note.getArmorLLeg());


        if (textViewRun.getText().toString().length() == 0 ||
                textViewLeap.getText().toString().length() == 0 ||
                textViewLift.getText().toString().length() == 0 ||
                textViewBtm.getText().toString().length() == 0) {
            buttonSave.setEnabled(false);
        }

        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (generateStats(editTextStatsMa, editTextStatsBody,
                        textViewRun, textViewLeap, textViewLift, textViewBtm)) {

                    buttonSave.setEnabled(true);
                }
            }
        });


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = editTextName.getText().toString();
                String Handle = editTextHandle.getText().toString();
                String Role = editTextRole.getText().toString();
                String Age = editTextAge.getText().toString();
                String ChPoints = editTextChPoints.getText().toString();

                String StatsInt = editTextStatsInt.getText().toString();
                String StatsRef = editTextStatsRef.getText().toString();
                String StatsTech = editTextStatsTech.getText().toString();
                String StatsCool = editTextStatsCool.getText().toString();
                String StatsAttr = editTextStatsAttr.getText().toString();
                String StatsLuck = editTextStatsLuck.getText().toString();
                String StatsMa = editTextStatsMa.getText().toString();
                String StatsBody = editTextStatsBody.getText().toString();
                String StatsEmp = editTextStatsEmp.getText().toString();

                String StatsSave = editTextStatsSave.getText().toString();

                String StatsRun = textViewRun.getText().toString();
                String StatsLeap = textViewLeap.getText().toString();
                String StatsLift = textViewLift.getText().toString();
                String StatsBtm = textViewBtm.getText().toString();

                String armorHead = editTextArmorHead.getText().toString();
                String armorTorso = editTextArmorTorso.getText().toString();
                String armorRArm = editTextArmorRArm.getText().toString();
                String armorLArm = editTextArmorLArm.getText().toString();
                String armorRLeg = editTextArmorRLeg.getText().toString();
                String armorLLeg = editTextArmorLLeg.getText().toString();

                Date createdAt = Calendar.getInstance().getTime();
                //Update note
                mNotesListViewModel.deleteById((long)note.getId());
                mNotesListViewModel.addNote(new Note(Name, Handle, Role, Age, ChPoints,
                        StatsInt, StatsRef, StatsTech, StatsCool,
                        StatsAttr, StatsLuck, StatsMa, StatsBody, StatsEmp,
                        StatsRun, StatsLeap, StatsLift, StatsBtm, StatsSave,
                        armorHead, armorTorso, armorRArm, armorLArm, armorRLeg, armorLLeg,
                        createdAt));

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
