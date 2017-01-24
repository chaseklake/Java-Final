package com.chaseklake.spellbook;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Created by clake18 on 1/22/2017.
 */
@SuppressWarnings("serial")
public class Spell implements Serializable {
    public enum Component {
        V, S, M;
    }
    private String name, castTime, range, duration, school;
    private String description;
    private int level;
    private ArrayList<Component> components;

    Spell(String n, String ct, String r, String dur, String desc, int l, String s, ArrayList<Component> comps) {
        name = n;
        castTime = ct;
        range = r;
        duration = dur;
        description = desc;
        level = l;
        school = s;
        components = comps;
    }

    public String getName() { return name; }
    public String getCastTime() {return castTime; }
    public String getRange() { return range; }
    public String getDuration() { return duration; }
    public String getDescription() { return description; }
    public int getLevel() { return level; }
    public String getSchool() { return school; }
    public String getComponents() {
        String comps = "";
        for (Component c : components)
            comps += c.name() + " ";
        return comps;
    }

    @Override
    public String toString() {
        return name + "\n" + level + " level " + school + "\nCasting Time: " + castTime + "\nRange: "
                + range + "\nComponents: " + this.getComponents() + "\nDuration: " + duration + "\n\n"
                + description;
    }

    public void writeToFile(Context context) {
        try {
            OutputStreamWriter stream = new OutputStreamWriter(context.openFileOutput("spells.txt", Context.MODE_PRIVATE));
            stream.write("{[Name:" + name);
            stream.write("]\n[Level:" + level);
            stream.write("]\n[School:" + school);
            stream.write("]\n[Casting Time:" + castTime);
            stream.write("]\n[Range:" + range);
            stream.write("]\n[Components:" + this.getComponents());
            stream.write("]\n[Duration:" + duration);
            stream.write("]\n[Description:" + description + "]}\n");
            stream.close();
        } catch (Exception e) { System.out.println(e.toString()); }
    }
}
