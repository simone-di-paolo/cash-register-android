package com.dev.simonedipaolo.cashregister.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by Simone Di Paolo on 02/08/2022.
 */
@Entity
public class TipologiaReparto {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tipologia_reparto_uid")
    private int tipologiaRepartoUid;

    @ColumnInfo(name = "tipologia_reparto")
    private String tipologiaReparto;

    @Ignore
    public TipologiaReparto() {
        // empty
    }

    public TipologiaReparto(String tipologiaReparto) {
        this.tipologiaReparto = tipologiaReparto;
    }

    public int getTipologiaRepartoUid() {
        return tipologiaRepartoUid;
    }

    public void setTipologiaRepartoUid(int tipologiaRepartoUid) {
        this.tipologiaRepartoUid = tipologiaRepartoUid;
    }

    public String getTipologiaReparto() {
        return tipologiaReparto;
    }

    public void setTipologiaReparto(String tipologiaReparto) {
        this.tipologiaReparto = tipologiaReparto;
    }
}
