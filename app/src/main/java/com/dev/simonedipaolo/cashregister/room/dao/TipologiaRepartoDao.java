package com.dev.simonedipaolo.cashregister.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.dev.simonedipaolo.cashregister.entities.CashRegister;
import com.dev.simonedipaolo.cashregister.entities.Reparto;
import com.dev.simonedipaolo.cashregister.entities.TipologiaReparto;
import com.dev.simonedipaolo.cashregister.entities.relations.StandAndTipologiaReparto;
import com.dev.simonedipaolo.cashregister.entities.relations.TipologiaRepartoWithReparto;

import java.util.List;

/**
 * Created by Simone Di Paolo on 02/08/2022.
 */
@Dao
public interface TipologiaRepartoDao {

    // inserts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTipologiaReparto(TipologiaReparto tipologiaReparto);

    // updates
    @Update
    void updateTipologiaReparto(TipologiaReparto tipologiaReparto);

    // insert all
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTipologiaReparto(TipologiaReparto... tipologieReparti);

    // delete
    @Delete
    void deleteTiplogiaReparto(TipologiaReparto tipologiaReparto);

    // get all

    @Transaction
    @Query("SELECT * FROM tipologiareparto")
    List<TipologiaReparto> getAllTipologiaReparto();

    @Query("UPDATE TipologiaReparto SET lista_reparti = :listaTipologiaReparti")
    void updateListTipologiaRepartoInTipologiaReparto(List<String> listaTipologiaReparti);

    @Query("UPDATE TipologiaReparto SET lista_reparti = :listaTipologiaReparti WHERE tipologia_reparto_uid = :tipologiaRepartoUid")
    void updateListTipologiaRepartoInTipologiaRepartoByUid(List<String> listaTipologiaReparti, int tipologiaRepartoUid);


    @Transaction
    @Query("SELECT * FROM CashRegister")
    List<StandAndTipologiaReparto> getStandAndTipologiaRepartoList();

}
