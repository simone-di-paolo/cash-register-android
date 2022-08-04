package com.dev.simonedipaolo.cashregister.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.dev.simonedipaolo.cashregister.entities.CashRegister;
import com.dev.simonedipaolo.cashregister.entities.TipologiaReparto;

/**
 * Created by Simone Di Paolo on 02/08/2022.
 */
public class StandAndTipologiaReparto {
    @Embedded public CashRegister stand;
    @Relation(
            parentColumn = "cash_register_uid",
            entityColumn = "tipologia_reparto"
    )
    public TipologiaReparto tipologiaReparto;
}
