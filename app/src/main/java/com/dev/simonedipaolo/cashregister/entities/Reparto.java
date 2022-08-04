package com.dev.simonedipaolo.cashregister.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.dev.simonedipaolo.cashregister.utils.ConstantsUtils;

/**
 * Created by Simone Di Paolo on 02/08/2022.
 */
@Entity
public class Reparto {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reparto_uid")
    private int repartoUid;

    @ColumnInfo(name = "nome_reparto")
    private String nomeReparto;

    @Ignore
    public Reparto() {
        // empty
        nomeReparto = ConstantsUtils.DEFAULT_REPARTO_NAME;
    }

    @Ignore
    public Reparto(String nomeReparto) {
        this.nomeReparto = nomeReparto;
    }

    public Reparto(int repartoUid, String nomeReparto) {
        this.repartoUid = repartoUid;
        this.nomeReparto = nomeReparto;
    }

    public int getRepartoUid() {
        return repartoUid;
    }

    public void setRepartoUid(int repartoUid) {
        this.repartoUid = repartoUid;
    }

    public String getNomeReparto() {
        return nomeReparto;
    }

    public void setNomeReparto(String nomeReparto) {
        this.nomeReparto = nomeReparto;
    }
}
