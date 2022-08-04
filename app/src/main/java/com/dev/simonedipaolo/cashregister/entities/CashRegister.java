package com.dev.simonedipaolo.cashregister.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.util.StringUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simone Di Paolo on 02/08/2022.
 */
@Entity
public class CashRegister {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cash_register_uid")
    private int cashRegisterUid;

    @ColumnInfo(name = "nome_stand")
    private String nomeStand;

    @ColumnInfo(name = "sottotitolo_stand")
    private String sottotitoloStand;

    @ColumnInfo(name = "test_chiusura_scontrino")
    private String testChiusuraScontrino;

    @ColumnInfo(name = "totale")
    private double totale;

    @ColumnInfo(name = "sub_totale")
    private double subTotale;

    @ColumnInfo(name = "elenco_scontrini")
    private List<Receipt> elencoScontrini;

    @Ignore
    public CashRegister() {
        // empty
        this.nomeStand = StringUtils.EMPTY;
        this.totale = 0;
        this.subTotale = 0;
        this.elencoScontrini = new ArrayList<>();
    }

    public CashRegister(String nomeStand, double totale, double subTotale) {
        this.nomeStand = nomeStand;
        this.totale = totale;
        this.subTotale = subTotale;
        this.elencoScontrini = new ArrayList<>();
    }

    public int getCashRegisterUid() {
        return cashRegisterUid;
    }

    public void setCashRegisterUid(int cashRegisterUid) {
        this.cashRegisterUid = cashRegisterUid;
    }

    public String getNomeStand() {
        return nomeStand;
    }

    public void setNomeStand(String nomeStand) {
        this.nomeStand = nomeStand;
    }

    public String getTestChiusuraScontrino() {
        return testChiusuraScontrino;
    }

    public void setTestChiusuraScontrino(String testChiusuraScontrino) {
        this.testChiusuraScontrino = testChiusuraScontrino;
    }

    public String getSottotitoloStand() {
        return sottotitoloStand;
    }

    public void setSottotitoloStand(String sottotitoloStand) {
        this.sottotitoloStand = sottotitoloStand;
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

    public List<Receipt> getElencoScontrini() {
        return elencoScontrini;
    }

    public void setElencoScontrini(List<Receipt> elencoScontrini) {
        this.elencoScontrini = elencoScontrini;
    }
}