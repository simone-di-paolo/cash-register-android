package com.dev.simonedipaolo.cashregister.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.dev.simonedipaolo.cashregister.entities.CashRegister;
import com.dev.simonedipaolo.cashregister.entities.Receipt;
import com.dev.simonedipaolo.cashregister.entities.Reparto;
import com.dev.simonedipaolo.cashregister.entities.TipologiaReparto;
import com.dev.simonedipaolo.cashregister.room.dao.CashRegisterDao;
import com.dev.simonedipaolo.cashregister.room.dao.TipologiaRepartoDao;

/**
 * Created by Simone Di Paolo on 02/08/2022.
 */
@Database(entities = {
        CashRegister.class,
        TipologiaReparto.class,
        Reparto.class,
        Receipt.class
}, version = 1)
@TypeConverters({Converters.class})
public abstract class StandDatabase extends RoomDatabase {
    public abstract CashRegisterDao cashRegisterDao();
    public abstract TipologiaRepartoDao tipologiaRepartoDao();
}
