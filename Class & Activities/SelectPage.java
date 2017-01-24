package com.chaseklake.spellbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SelectPage extends AppCompatActivity {
    public static ArrayList<Spell> spells = new ArrayList<Spell>();
    public ArrayList<TextView> spellsKnown = new ArrayList<TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final EditText spellName = (EditText) findViewById(R.id.SearchBar);
        final LinearLayout spellList = (LinearLayout) findViewById(R.id.spellList);
        Button search = (Button) findViewById(R.id.searchButton);
        final Button addSpell = (Button) findViewById(R.id.addSpell);
        final Intent create = new Intent(this, CreateSpell.class);
        final Intent get = new Intent(this, GetSpell.class);

        addSpell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(create);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spellList.removeAllViews();
                if (spellName.getText().toString().equals("")) {
                    for (TextView t : spellsKnown)
                        spellList.addView(t);
                }
                else {
                    for (TextView t : spellsKnown) {
                        String text = t.getText().toString().toLowerCase();
                        String params = spellName.getText().toString();
                        if(text.contains(params)) {
                            spellList.addView(t);
                        }
                    }
                }
            }
        });

        for (final Spell s : spells) {
            TextView temp = new TextView(this);
            temp.setText(s.getName().toLowerCase());
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    get.putExtra("CurrentSpell", s);
                    startActivity(get);
                }
            });
            spellsKnown.add(temp);
        }

        for (TextView t : spellsKnown)
            spellList.addView(t);


    }
}
