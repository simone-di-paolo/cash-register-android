package com.dev.simonedipaolo.cashregister.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.dev.simonedipaolo.cashregister.entities.Reparto;
import com.dev.simonedipaolo.cashregister.entities.Stand;
import com.dev.simonedipaolo.cashregister.entities.TipologiaReparto;

/**
 * Created by Simone Di Paolo on 02/08/2022.
 */
@Database(entities = {
        Stand.class,
        TipologiaReparto.class,
        Reparto.class
}, version = 2)
@TypeConverters({Converters.class})
public abstract class StandDatabase extends RoomDatabase {
    public abstract StandDao standDao();
}
