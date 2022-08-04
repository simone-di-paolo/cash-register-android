package com.dev.simonedipaolo.cashregister.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.dev.simonedipaolo.cashregister.utils.ConstantsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone Di Paolo on 04/08/2022.
 */
@Entity
public class Receipt {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "receipt_uid")
    private int receiptUid;

    @ColumnInfo(name = "totale_scontrino")
    private double totaleScontrino;
    @ColumnInfo(name = "reparti_scontrino")
    private List<String> repartiScontrino;

    @Ignore
    public Receipt() {
        // empty
        totaleScontrino = 0;
        repartiScontrino = new ArrayList<>();
    }

    public Receipt(double totaleScontrino, List<String> repartiScontrino) {
        this.totaleScontrino = totaleScontrino;
        this.repartiScontrino = repartiScontrino;
    }

    public int getReceiptUid() {
        return receiptUid;
    }

    public void setReceiptUid(int receiptUid) {
        this.receiptUid = receiptUid;
    }

    public double getTotaleScontrino() {
        return totaleScontrino;
    }

    public void setTotaleScontrino(double totaleScontrino) {
        this.totaleScontrino = totaleScontrino;
    }

    public List<String> getRepartiScontrino() {
        return repartiScontrino;
    }

    public void setRepartiScontrino(List<String> repartiScontrino) {
        this.repartiScontrino = repartiScontrino;
    }
}
