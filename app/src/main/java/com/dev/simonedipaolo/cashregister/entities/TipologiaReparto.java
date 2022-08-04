package com.dev.simonedipaolo.cashregister.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.dev.simonedipaolo.cashregister.utils.ConstantsUtils;

import java.util.ArrayList;
import java.util.List;

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

    @ColumnInfo(name = "lista_reparti")
    private List<String> listaReparti;

    @Ignore
    public TipologiaReparto() {
        // empty
        this.tipologiaReparto = ConstantsUtils.DEFAULT_REPARTO_NAME;
        this.listaReparti = new ArrayList<>();
    }

    @Ignore
    public TipologiaReparto(String tipologiaReparto) {
        this.tipologiaReparto = tipologiaReparto;
        this.listaReparti = new ArrayList<>();
    }

    @Ignore
    public TipologiaReparto(int tipologiaRepartoUid, String tipologiaReparto) {
        this.tipologiaRepartoUid = tipologiaRepartoUid;
        this.tipologiaReparto = tipologiaReparto;
        this.listaReparti = new ArrayList<>();
    }

    public TipologiaReparto(int tipologiaRepartoUid, String tipologiaReparto, List<String> listaReparti) {
        this.tipologiaRepartoUid = tipologiaRepartoUid;
        this.tipologiaReparto = tipologiaReparto;
        this.listaReparti = listaReparti;
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

    public List<String> getListaReparti() {
        return listaReparti;
    }

    public void setListaReparti(List<String> listaReparti) {
        this.listaReparti = listaReparti;
    }
}
