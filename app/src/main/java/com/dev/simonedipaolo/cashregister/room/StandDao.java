package com.dev.simonedipaolo.cashregister.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.dev.simonedipaolo.cashregister.entities.*;
import com.dev.simonedipaolo.cashregister.entities.relations.StandAndTipologiaReparto;
import com.dev.simonedipaolo.cashregister.entities.relations.TipologiaRepartoWithReparto;

import java.util.List;

/**
 * Created by Simone Di Paolo on 02/08/2022.
 */
@Dao
public interface StandDao {

    // inserts

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStand(CashRegister stand);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTipologiaReparto(TipologiaReparto tipologiaReparto);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReparto(Reparto reparto);

    // updates
    @Update
    void updateStand(CashRegister stand);
    @Update
    void updateTipologiaReparto(TipologiaReparto tipologiaReparto);
    @Update
    void updateReparto(Reparto reparto);

    // insert all

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllStands(CashRegister... stands);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTipologiaReparto(TipologiaReparto... tipologieReparti);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllReparto(Reparto... reparti);

    // delete

    @Delete
    void deleteStand(CashRegister stand);
    @Delete
    void deleteTiplogoaReparto(TipologiaReparto tipologiaReparto);
    @Delete
    void deleteReparto(Reparto reparto);

    // get all
    @Transaction
    @Query("SELECT * FROM CashRegister")
    List<CashRegister> getAllStand();

    @Transaction
    @Query("SELECT * FROM tipologiareparto")
    List<TipologiaReparto> getAllTipologiaReparto();

    @Transaction
    @Query("SELECT * FROM reparto")
    List<Reparto> getAllReparto();

    @Transaction
    @Query("SELECT * FROM CashRegister")
    List<StandAndTipologiaReparto> getStandAndTipologiaRepartoList();

    @Transaction
    @Query("SELECT * FROM tipologiareparto WHERE  tipologia_reparto = tipologia_reparto")
    List<TipologiaRepartoWithReparto> getTipologiaRepartoWithReparto();
}
