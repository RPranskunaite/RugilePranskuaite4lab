package com.example.fintechlab4as;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {
    private ListView lvNotes;
    private ArrayAdapter listAdapter;
    private ArrayList<String> notesList;

    Spinner spNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_note);

        this.spNotes = findViewById(R.id.spNotes);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.notesList = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));
        listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, notesList);
        spNotes.setAdapter(listAdapter);
    }

    public void onDeleteNoteClick(View view) {
        EditText txtNote = findViewById(R.id.txtNote);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();
        Set<String> savedNotesList = sp.getStringSet("notes", new HashSet<String>());

        String selection = spNotes.getSelectedItem().toString();

        Set<String> newStrSet = new HashSet<String>();

        for (String savedNote : savedNotesList) {
            if (!savedNote.equalsIgnoreCase(selection)) {
                newStrSet.add(savedNote);
            }
        }

        spEd.putStringSet("notes", newStrSet);
        spEd.apply();
        finish();
    }
}
