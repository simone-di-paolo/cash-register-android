package com.dev.simonedipaolo.cashregister.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.dev.simonedipaolo.cashregister.entities.Reparto;
import com.dev.simonedipaolo.cashregister.entities.TipologiaReparto;

import java.util.List;

/**
 * Created by Simone Di Paolo on 02/08/2022.
 */
public class TipologiaRepartoWithReparto {
    @Embedded
    public TipologiaReparto tipologiaReparto;
    @Relation(
            parentColumn = "tipologia_reparto_uid",
            entityColumn = "nome_reparto"
    )
    public List<Reparto> reparto;
}
