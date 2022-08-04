package com.dev.simonedipaolo.cashregister.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.simonedipaolo.cashregister.R;
import com.dev.simonedipaolo.cashregister.TipologiaRepartiSettingsAdapter;
import com.dev.simonedipaolo.cashregister.adapters.RepartiSettingsAdapter;
import com.dev.simonedipaolo.cashregister.entities.Reparto;
import com.dev.simonedipaolo.cashregister.entities.TipologiaReparto;
import com.dev.simonedipaolo.cashregister.room.StandDatabase;
import com.dev.simonedipaolo.cashregister.utils.ConstantsUtils;
import com.dev.simonedipaolo.cashregister.utils.OpenDatabase;

import java.util.List;

/**
 * Created by Simone Di Paolo on 30/07/2022.
 */
public class SettingsFragment extends Fragment {

    private RecyclerView recyclerView;
    private TipologiaRepartiSettingsAdapter tipologiaRepartiSettingsAdapter;

    private Button aggiungiReparto;

    private StandDatabase db;
    private List<TipologiaReparto> tipologiaRepartiFromDatabase;

    private NavController navController;

    public SettingsFragment() {
        // empty
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        container.removeAllViews();
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        recyclerView = v.findViewById(R.id.fragment_settings_recycler_view);

        db = OpenDatabase.openDB(getContext(), ConstantsUtils.DATABASE_NAME);
        tipologiaRepartiFromDatabase = db.standDao().getAllTipologiaReparto();

        // setto l'adapter
        tipologiaRepartiSettingsAdapter = new TipologiaRepartiSettingsAdapter(getActivity().getApplicationContext(), this);
        setAdapter(tipologiaRepartiSettingsAdapter);

        navController = Navigation.findNavController(getActivity().findViewById(R.id.fragmentPlaceholder));
        initializeViews(v);

        return v;
    }

    private void initializeViews(View v) {
        SettingsFragment instance = this;

        // recupero di nuovi i reparti ed i nomi dai reparti dal db (magari sono aggiornati)
        tipologiaRepartiFromDatabase = db.standDao().getAllTipologiaReparto();

        // gestisco il click del pulsante crea reparto
        aggiungiReparto = v.findViewById(R.id.aggiungi_tipologia_reparto_button);
        aggiungiReparto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipologiaRepartiSettingsAdapter.addNewReparto();
            }
        });
    }

    private void setAdapter(TipologiaRepartiSettingsAdapter tipologiaRepartiSettingsAdapter) {
        recyclerView.setAdapter(tipologiaRepartiSettingsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }

}
