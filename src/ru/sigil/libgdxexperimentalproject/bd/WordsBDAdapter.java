package ru.sigil.libgdxexperimentalproject.bd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class WordsBDAdapter {
    private Context context;
    private static final WordsBDAdapter INSTANCE = new WordsBDAdapter();

    public static WordsBDAdapter getInstance() {
        return INSTANCE;
    }

    public String getWord(int difficulty) {
        //TODO выбираем слово заданной сложности
        String res = "";
        String textFilter = " DIFFICULTY = " + String.valueOf(difficulty)
                + " ORDER BY RANDOM() LIMIT 1 ";
        SQLiteDatabase mDatabase = getContext().openOrCreateDatabase("WordsDB",
                0, null);
        try {
            Cursor managedCursor = mDatabase.query("words", null, textFilter,
                    null, null, null, null);
            if(managedCursor.moveToFirst()){
            res = managedCursor.getString(managedCursor.getColumnIndex("word"));
            }
            managedCursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDatabase.close();
        }
        return res;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}