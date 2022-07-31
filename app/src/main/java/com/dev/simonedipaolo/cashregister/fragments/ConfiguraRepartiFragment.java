package com.dev.simonedipaolo.cashregister.fragments;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import com.dev.simonedipaolo.cashregister.R;

/**
 * Created by Simone Di Paolo on 31/07/2022.
 */
public class ConfiguraRepartiFragment extends Fragment {

    private Button creaReparti;
    private EditText howManyReparti;

    public ConfiguraRepartiFragment() {
        // empty
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        container.removeAllViews();
        View v = inflater.inflate(R.layout.fragment_configure_reparti, container, false);

        int repartoIndex = ConfiguraRepartiFragmentArgs.fromBundle(getArguments()).getRepartoIndex();
        Toast.makeText(getActivity().getApplicationContext(), "Reparto index: " + repartoIndex, Toast.LENGTH_SHORT).show();

        instantiateViews(v);

        return v;
    }

    private void instantiateViews(View v) {
        howManyReparti = v.findViewById(R.id.howManyRepartiEditText);
        howManyReparti.setInputType(InputType.TYPE_CLASS_NUMBER);

        creaReparti = v.findViewById(R.id.crea_reparti_button);
        creaReparti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), howManyReparti.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
