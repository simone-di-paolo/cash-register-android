package com.dev.simonedipaolo.cashregister.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.simonedipaolo.cashregister.R;
import com.dev.simonedipaolo.cashregister.adapters.RepartiSettingsAdapter;
import com.dev.simonedipaolo.cashregister.entities.Reparto;
import com.dev.simonedipaolo.cashregister.entities.TipologiaReparto;
import com.dev.simonedipaolo.cashregister.room.StandDatabase;
import com.dev.simonedipaolo.cashregister.utils.ConstantsUtils;
import com.dev.simonedipaolo.cashregister.utils.EditRepartoDialog;
import com.dev.simonedipaolo.cashregister.utils.OpenDatabase;
import com.dev.simonedipaolo.cashregister.utils.ResourcesRetriever;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Simone Di Paolo on 31/07/2022.
 */
public class ConfiguraRepartiFragment extends Fragment {

    private Button aggiungiReparto;

    /**
     * Reparto index it's the index of the dropdown reparti menu.
     */
    private int repartoIndex;

    private RecyclerView recyclerView;
    private RepartiSettingsAdapter repartiSettingsAdapter;

    private List<TipologiaReparto> reparti;

    private StandDatabase db;
    private List<Reparto> repartiFromDatabase;

    private int tipologiaRepartoIndex;

    public ConfiguraRepartiFragment() {
        // empty
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        container.removeAllViews();
        View v = inflater.inflate(R.layout.fragment_configure_reparti, container, false);

        repartoIndex = ConfiguraRepartiFragmentArgs.fromBundle(getArguments()).getRepartoIndex();
        recyclerView = v.findViewById(R.id.repartiSettingsRecyclerView);

        db = OpenDatabase.openDB(getContext(), ConstantsUtils.DATABASE_NAME);
        repartiFromDatabase = db.standDao().getAllReparto();

        reparti = db.standDao().getAllTipologiaReparto();

        tipologiaRepartoIndex = ConfiguraRepartiFragmentArgs.fromBundle(getArguments()).getRepartoIndex();

        // setto l'adapter
        repartiSettingsAdapter = new RepartiSettingsAdapter(getActivity().getApplicationContext(), this, tipologiaRepartoIndex);
        setAdapter(repartiSettingsAdapter);

        instantiateViews(v);

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void instantiateViews(View v) {
        ConfiguraRepartiFragment instance = this;

        // recupero di nuovi i reparti ed i nomi dai reparti dal db (magari sono aggiornati)
        repartiFromDatabase = db.standDao().getAllReparto();

        // gestisco il click del pulsante crea reparto
        aggiungiReparto = v.findViewById(R.id.aggiungiRepartoSingolo);
        aggiungiReparto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repartiSettingsAdapter.addNewReparto();
            }
        });
    }

    private void setAdapter(RepartiSettingsAdapter repartiSettingsAdapter) {
        recyclerView.setAdapter(repartiSettingsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }

}
