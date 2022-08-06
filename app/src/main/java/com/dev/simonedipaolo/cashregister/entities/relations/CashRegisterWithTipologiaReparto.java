package com.dev.simonedipaolo.cashregister.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.dev.simonedipaolo.cashregister.entities.CashRegister;
import com.dev.simonedipaolo.cashregister.entities.Reparto;
import com.dev.simonedipaolo.cashregister.entities.TipologiaReparto;

import java.util.List;

/**
 * Created by Simone Di Paolo on 02/08/2022.
 */
public class CashRegisterWithTipologiaReparto {
    @Embedded
    public CashRegister cashRegister;
    @Relation(
            parentColumn = "cash_register_uid",
            entityColumn = "lista_reparti"
    )
    public List<TipologiaReparto> listTipologiaReparti;
}
