package com.dev.simonedipaolo.cashregister.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by Simone Di Paolo on 02/08/2022.
 */
@Entity
public class Stand {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "stand_uid")
    private int standUid;

    @ColumnInfo(name = "nome_stand")
    private String nomeStand;

    @ColumnInfo(name = "totale")
    private double totale;

    @ColumnInfo(name = "sub_totale")
    private double subTotale;

    @Ignore
    public Stand() {
        // empty
    }

    public Stand(String nomeStand, double totale, double subTotale) {
        this.nomeStand = nomeStand;
        this.totale = totale;
        this.subTotale = subTotale;
    }

    public int getStandUid() {
        return standUid;
    }

    public void setStandUid(int standUid) {
        this.standUid = standUid;
    }

    public String getNomeStand() {
        return nomeStand;
    }

    public void setNomeStand(String nomeStand) {
        this.nomeStand = nomeStand;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public double getSubTotale() {
        return subTotale;
    }

    public void setSubTotale(double subTotale) {
        this.subTotale = subTotale;
    }
}
