package com.dev.simonedipaolo.cashregister.utils;

import android.content.Context;

import androidx.room.Room;

import com.dev.simonedipaolo.cashregister.room.StandDatabase;

/**
 * Created by Simone Di Paolo on 02/08/2022.
 */
public class OpenDatabase {

    public static StandDatabase openDB (Context context, String databaseName) {
        return Room.databaseBuilder(context, StandDatabase.class, databaseName)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

}
