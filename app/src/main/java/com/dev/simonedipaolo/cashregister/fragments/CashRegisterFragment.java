package com.dev.simonedipaolo.cashregister.fragments;

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
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.simonedipaolo.cashregister.R;
import com.dev.simonedipaolo.cashregister.adapters.RepartiAdapter;
import com.dev.simonedipaolo.cashregister.utils.ResourcesRetriever;

/**
 * Created by Simone Di Paolo on 28/07/2022.
 */
public class CashRegisterFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    // BUTTONS
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

    private String repartiCiboNames[];
    private String repartiBibiteNames[];
    private String repartiCocktailNames[];
    private String repartiAltroNames[];
    private int repartiImages[];
    private int editIcon;
    private RepartiAdapter repartiAdapter;

    public CashRegisterFragment() {
        // empty
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        container.removeAllViews();
        View v = inflater.inflate(R.layout.fragment_cash_register, container, false);

        viewsInitializer(v);
        createDropdownList();

        /*
        repartiCiboNames = getResources().getStringArray(R.array.reparto_cibo_default_string_name);
        repartiBibiteNames = getResources().getStringArray(R.array.reparto_bibite_default_string_name);
        repartiCocktailNames = getResources().getStringArray(R.array.reparto_cocktail_default_string_name);;
        repartiAltroNames = getResources().getStringArray(R.array.reparto_altro_default_string_name);
        */

        repartiCiboNames = ResourcesRetriever.getRepartiCiboNames(this.getContext());
        repartiBibiteNames = ResourcesRetriever.getRepartiBibiteNames(this.getContext());
        repartiCocktailNames = ResourcesRetriever.getRepartiCocktailNames(this.getContext());
        repartiAltroNames = ResourcesRetriever.getRepartiAltroNames(this.getContext());
        repartiImages = ResourcesRetriever.getRepartiImages();
        editIcon = ResourcesRetriever.getEditIcon();

        // custom adapter for reparti
        repartiAdapter = new RepartiAdapter(this.getContext(), repartiCiboNames, repartiImages, editIcon, 0);
        recyclerView.setAdapter(repartiAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return v;
    }

    private void createDropdownList() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.default_dropdown_list, R.layout.support_simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(this);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(this.getContext(), choice, Toast.LENGTH_SHORT).show();

        switch(i) {
            case 0:
                repartiAdapter = new RepartiAdapter(this.getContext(), repartiCiboNames, repartiImages, editIcon, i);
                recyclerView.setAdapter(repartiAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                break;
            case 1:
                repartiAdapter = new RepartiAdapter(this.getContext(), repartiBibiteNames, repartiImages, editIcon, i);
                recyclerView.setAdapter(repartiAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                break;
            case 2:
                repartiAdapter = new RepartiAdapter(this.getContext(), repartiCocktailNames, repartiImages, editIcon, i);
                recyclerView.setAdapter(repartiAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                break;
            case 3:
                repartiAdapter = new RepartiAdapter(this.getContext(), repartiAltroNames, repartiImages, editIcon, i);
                recyclerView.setAdapter(repartiAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // empty
    }

}
