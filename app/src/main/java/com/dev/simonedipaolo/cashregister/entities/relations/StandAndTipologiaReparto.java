package com.dev.simonedipaolo.cashregister.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.dev.simonedipaolo.cashregister.entities.Stand;
import com.dev.simonedipaolo.cashregister.entities.TipologiaReparto;

/**
 * Created by Simone Di Paolo on 02/08/2022.
 */
public class StandAndTipologiaReparto {
    @Embedded public Stand stand;
    @Relation(
            parentColumn = "stand_uid",
            entityColumn = "tipologia_reparto"
    )
    public TipologiaReparto tipologiaReparto;
}
