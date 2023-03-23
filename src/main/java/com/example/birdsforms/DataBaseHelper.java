package com.example.birdsforms;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String WATCHER_TABLE = "WATCHER_TABLE";
    public static final String COLUMN_WATCHER_LOGIN = "WATCHER_LOGIN";
    public static final String ID = "WATCHER_ID";
    public static final String WATCHER_PASSWORD = "WATCHER_PASSWORD";
    public static final String BIRDS_WATCHED = "BIRDS_WATCHED";
    public static final String BIRDS_NUMBER = "BIRDS_NUMBER";
    public static final String RARE_BIRDS_DETECTED = "RARE_BIRDS_DETECTED";

    DataBaseHelper(@Nullable Context context){
        super(context, "newbirdswatcher.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + WATCHER_TABLE +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_WATCHER_LOGIN + " TEXT, " +
                WATCHER_PASSWORD + " TEXT, " +
                BIRDS_NUMBER + " INT, " +
                RARE_BIRDS_DETECTED + " BOOLEAN, " +
                BIRDS_WATCHED + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(WatcherModel watcherModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_WATCHER_LOGIN, watcherModel.getName());
        cv.put(WATCHER_PASSWORD, watcherModel.getPassword());
        cv.put(BIRDS_NUMBER, watcherModel.getNumberOfBirds());
        cv.put(RARE_BIRDS_DETECTED, watcherModel.getRareBirdsDetector());
        cv.put(BIRDS_WATCHED, watcherModel.getBirds());

        long insert = db.insert(WATCHER_TABLE, null, cv);
        if (insert == -1)
            return false;
        return true;
    }


    public boolean DeleteOne(WatcherModel watcherModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + WATCHER_TABLE +
                " WHERE " + ID + " = " + watcherModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }

    public boolean DeleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + WATCHER_TABLE;

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }

    // push en serveur sur firebase

    public void PushOnline() {
        String query = "SELECT * FROM " + WATCHER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {

                int watcherID = cursor.getInt(0);
                String login = cursor.getString(1);
                String password = cursor.getString(2);
                int numberOfBirds = cursor.getInt(3);
                boolean rareBirds = (cursor.getInt(4) != 0);
                String birds = cursor.getString(5);

                String path = "BirdsWatcherDB/" + login;
                DocumentReference mDocRef = FirebaseFirestore.getInstance().document(path);

                Map<String, Object> dataToSave = new HashMap<String, Object>();

                dataToSave.put(ID, watcherID);
                dataToSave.put(COLUMN_WATCHER_LOGIN, login);
                dataToSave.put(WATCHER_PASSWORD, password);
                dataToSave.put(BIRDS_NUMBER, numberOfBirds);
                dataToSave.put(RARE_BIRDS_DETECTED, rareBirds);
                dataToSave.put(BIRDS_WATCHED, birds);

                mDocRef.set(dataToSave).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Document enregistré");
                        } else {
                            Log.w(TAG, "Exception: ", task.getException());
                        }
                    }
                });

            } while (cursor.moveToNext());

        } else {}
        cursor.close();
        db.close();
    }



    public List<WatcherModel> getEveryone() {
        List<WatcherModel> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + WATCHER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int watcherID = cursor.getInt(0);
                String login = cursor.getString(1);
                String password = cursor.getString(2);
                int numberOfBirds = cursor.getInt(3);
                boolean rareBirds = (cursor.getInt(4) != 0);
                String birds = cursor.getString(5);
                System.out.println(login);
                WatcherModel newWatcher = new WatcherModel(watcherID, login, password,
                        numberOfBirds, rareBirds, birds);
                returnList.add(newWatcher);

            } while (cursor.moveToNext());

        } else {}
        cursor.close();
        db.close();
        return returnList;
    }


}
