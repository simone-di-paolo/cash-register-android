package com.dev.simonedipaolo.cashregister.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.simonedipaolo.cashregister.R;
import com.dev.simonedipaolo.cashregister.adapters.RepartiAdapter;
import com.dev.simonedipaolo.cashregister.entities.TipologiaReparto;
import com.dev.simonedipaolo.cashregister.room.StandDatabase;
import com.dev.simonedipaolo.cashregister.utils.ConstantsUtils;
import com.dev.simonedipaolo.cashregister.utils.OpenDatabase;
import com.dev.simonedipaolo.cashregister.utils.ResourcesRetriever;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Simone Di Paolo on 28/07/2022.
 */
public class CashRegisterFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    // CAULCULATORS BUTTONS
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonCanc;
    private Button buttonComma;
    private Button settingsButton;

    private Spinner dropdown;

    private RecyclerView recyclerView;

    private int repartiImages[];
    private RepartiAdapter repartiAdapter;

    private StandDatabase db;
    private List<TipologiaReparto> reparti;
    private List<String> nomiTipologiaReparto;
    private boolean isNomiTipologiaRepartoEmpty;
    private int tipologiaRepartoIndex;

    public CashRegisterFragment() {
        // empty
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        container.removeAllViews();
        View v = inflater.inflate(R.layout.fragment_cash_register, container, false);

        db = OpenDatabase.openDB(getActivity().getApplicationContext(), ConstantsUtils.DATABASE_NAME);
        db = OpenDatabase.openDB(getContext(), ConstantsUtils.DATABASE_NAME);
        reparti = db.standDao().getAllTipologiaReparto();

        nomiTipologiaReparto = reparti.stream()
                .map(tipologiaReparto -> tipologiaReparto.getTipologiaReparto())
                .collect(Collectors.toList());

        isNomiTipologiaRepartoEmpty = !CollectionUtils.isNotEmpty(nomiTipologiaReparto);

        viewsInitializer(v);
        createDropdownList();

        if(CollectionUtils.isNotEmpty(reparti)) {
            // custom adapter for reparto
            repartiAdapter = new RepartiAdapter(this.getContext(), this, reparti.get(0).getListaReparti(), isNomiTipologiaRepartoEmpty);
            recyclerView.setAdapter(repartiAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        }
        return v;
    }

    private void viewsInitializer(View v) {
        // buttons
        button0 = v.findViewById(R.id.calculator_button0);
        button1 = v.findViewById(R.id.calculator_button1);
        button2 = v.findViewById(R.id.calculator_button2);
        button3 = v.findViewById(R.id.calculator_button3);
        button4 = v.findViewById(R.id.calculator_button4);
        button5 = v.findViewById(R.id.calculator_button5);
        button6 = v.findViewById(R.id.calculator_button6);
        button7 = v.findViewById(R.id.calculator_button7);
        button8 = v.findViewById(R.id.calculator_button8);
        button9 = v.findViewById(R.id.calculator_button9);

        // settings button
        settingsButton = v.findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(view -> Navigation.findNavController(v).navigate(R.id.settingsFragment));

        // dropdown
        dropdown = v.findViewById(R.id.dropdown);

        // recycler view
        recyclerView = v.findViewById(R.id.repartiSettingsRecyclerView);
    }

    private void createDropdownList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, nomiTipologiaReparto);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        if(isNomiTipologiaRepartoEmpty) {
            dropdown.setActivated(false);
            dropdown.setClickable(false);
        }

        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        repartiAdapter = new RepartiAdapter(this.getContext(), this, reparti.get(i).getListaReparti(), isNomiTipologiaRepartoEmpty);
        recyclerView.setAdapter(repartiAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // empty
    }

}
