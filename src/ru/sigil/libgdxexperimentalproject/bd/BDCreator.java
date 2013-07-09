package ru.sigil.libgdxexperimentalproject.bd;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public abstract class BDCreator {
    public static void createBD(Context c) {
        try {
            SQLiteDatabase myDB = c.openOrCreateDatabase("WordsDB", 0, null);

            /* Create a Table in the Database. */
            myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                    + "words " +
                    "(id INTEGER PRIMARY KEY, word VARCHAR(100), difficulty INTEGER);");
            myDB.execSQL("insert into words (word, difficulty) values (\"hui\", 1)");
            myDB.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFileFromAssets(Context c) {
        AssetManager am = c.getAssets();
        try {
            String fileName = "WordsDB";
            File destinationFile = new File(Environment.getDataDirectory() + "/data/" + c.getPackageName() + "/databases/" + fileName);
            if (destinationFile.exists()) {
                return;
            }
            InputStream in = am.open("WordsDB");
            FileOutputStream f = new FileOutputStream(destinationFile);
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = in.read(buffer)) > 0) {
                f.write(buffer, 0, len1);
            }
            f.close();
        } catch (Exception e) {
            Log.d("CopyFileFromAssets", e.getMessage());
        }
    }
}
