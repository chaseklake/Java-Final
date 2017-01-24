package com.chaseklake.spellbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GetSpell extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_spell);

        Intent intent = getIntent();
        Spell currentSpell = (Spell) intent.getSerializableExtra("CurrentSpell");
        TextView spellText = (TextView) findViewById(R.id.SpellText);

        String text = currentSpell.toString();

        spellText.setText(text);

    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(this, SelectPage.class);
        startActivity(back);
    }
}
