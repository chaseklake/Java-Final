package com.chaseklake.spellbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class CreateSpell extends AppCompatActivity {
    EditText name, range, castTime, duration, description;
    Spinner school, level;
    CheckBox v, s, m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_spell);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final Intent main = new Intent(this, GetSpell.class);
        //Custom adapter for integer spinner
        level = (Spinner) this.findViewById(R.id.LevelSpinner);
        ArrayList<Integer> levels = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++)
            levels.add(i);
        ArrayAdapter<Integer> lvlAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, levels);
        level.setAdapter(lvlAdapter);

        //Other elements of activity
        name = (EditText) this.findViewById(R.id.editSpellName);
        range = (EditText) this.findViewById(R.id.editRange);
        castTime = (EditText) this.findViewById(R.id.editCastTime);
        duration = (EditText) this.findViewById(R.id.editDur);
        description = (EditText) this.findViewById(R.id.editDescription);
        school = (Spinner) this.findViewById(R.id.SchoolSpinner);
        v = (CheckBox) this.findViewById(R.id.VisCheckBox);
        s = (CheckBox) this.findViewById(R.id.SomCheckBox);
        m = (CheckBox) this.findViewById(R.id.MatCheckBox);

        Button create = (Button) this.findViewById(R.id.btnCreateSpell);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get all the data from
                ArrayList<Spell.Component> comps = new ArrayList<Spell.Component>();
                if (v.isChecked()) {
                    Spell.Component v = Spell.Component.V;
                    comps.add(v);
                }
                if (s.isChecked()) {
                    Spell.Component s = Spell.Component.S;
                    comps.add(s);
                }
                if (m.isChecked()) {
                    Spell.Component m = Spell.Component.M;
                    comps.add(m);
                }
                int lvl = (int)level.getSelectedItem();
                String n = name.getText().toString();
                String ct = castTime.getText().toString();
                String r = range.getText().toString();
                String dur = duration.getText().toString();
                String desc = description.getText().toString();
                String schl = school.getSelectedItem().toString();


                Spell temp = new Spell(n,ct,r,dur,desc,lvl,schl,comps);
                SelectPage.spells.add(temp);
                main.putExtra("CurrentSpell", temp);
                startActivity(main);
            }
        });
    }
}
