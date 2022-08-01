package com.dev.simonedipaolo.cashregister.fragments;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.simonedipaolo.cashregister.R;
import com.dev.simonedipaolo.cashregister.adapters.RepartiAdapter;
import com.dev.simonedipaolo.cashregister.utils.ResourcesRetriever;

/**
 * Created by Simone Di Paolo on 31/07/2022.
 */
public class ConfiguraRepartiFragment extends Fragment {

    private Button creaReparti;
    private EditText howManyReparti;

    /**
     * Reparto index it's the index of the dropdown reparti menu.
     */
    private int repartoIndex;

    private RecyclerView recyclerView;
    private RepartiAdapter repartiAdapter;

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

        repartiAdapter = new RepartiAdapter(this.getContext(), instance, repartiNames, repartiImages, editIcon, repartoIndex);

        creaReparti = v.findViewById(R.id.crea_reparti_button);
        creaReparti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    repartiAdapter = new RepartiAdapter(getActivity().getApplicationContext(), instance, repartiNames, repartiImages, repartoIndex, editIcon, Integer.parseInt(howManyReparti.getText().toString()));
                    // custom adapter for reparti
                    recyclerView.setAdapter(repartiAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
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
        repartiImages = ResourcesRetriever.getRepartiImages();
        editIcon = ResourcesRetriever.getEditIcon();
    }

}
