package com.dev.simonedipaolo.cashregister.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.simonedipaolo.cashregister.R;
import com.dev.simonedipaolo.cashregister.adapters.RepartiAdapter;
import com.dev.simonedipaolo.cashregister.adapters.RepartiSettingsAdapter;
import com.dev.simonedipaolo.cashregister.utils.EditRepartoDialog;
import com.dev.simonedipaolo.cashregister.utils.ResourcesRetriever;

/**
 * Created by Simone Di Paolo on 31/07/2022.
 */
public class ConfiguraRepartiFragment extends Fragment {

    private Button creaReparti;
    private Button saveButton;
    private EditText howManyReparti;

    private EditRepartoDialog.EditRepartoDialogListener editRepartoDialogListener;

    /**
     * Reparto index it's the index of the dropdown reparti menu.
     */
    private int repartoIndex;

    private RecyclerView recyclerView;
    private RepartiSettingsAdapter repartiSettingsAdapter;

    private String repartiNames[];
    private int repartiImages[];
    private int editIcon;

    public ConfiguraRepartiFragment() {
        // empty
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        container.removeAllViews();
        View v = inflater.inflate(R.layout.fragment_configure_reparti, container, false);

        repartoIndex = ConfiguraRepartiFragmentArgs.fromBundle(getArguments()).getRepartoIndex();
        initializeDrawables();

        recyclerView = v.findViewById(R.id.repartiSettingsRecyclerView);

        instantiateViews(v);

        return v;
    }

    private void instantiateViews(View v) {
        ConfiguraRepartiFragment instance = this;

        howManyReparti = v.findViewById(R.id.howManyRepartiEditText);
        howManyReparti.setInputType(InputType.TYPE_CLASS_NUMBER);

        repartiSettingsAdapter = new RepartiSettingsAdapter(this.getContext(), instance, repartiNames, repartoIndex);

        creaReparti = v.findViewById(R.id.crea_reparti_button);
        creaReparti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    repartiSettingsAdapter = new RepartiSettingsAdapter(getActivity().getApplicationContext(), instance, repartiNames, Integer.parseInt(howManyReparti.getText().toString()));
                    // custom adapter for reparti
                    recyclerView.setAdapter(repartiSettingsAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
/*
        saveButton = v.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });
*/
    }

    private void initializeDrawables() {
        switch(repartoIndex) {
            case 0:
                repartiNames = ResourcesRetriever.getRepartiCiboNames(this.getContext());
                break;
            case 1:
                repartiNames = ResourcesRetriever.getRepartiBibiteNames(this.getContext());
                break;
            case 2:
                repartiNames = ResourcesRetriever.getRepartiCocktailNames(this.getContext());
                break;
            case 3:
                repartiNames = ResourcesRetriever.getRepartiAltroNames(this.getContext());
                break;
        }
    }

}
