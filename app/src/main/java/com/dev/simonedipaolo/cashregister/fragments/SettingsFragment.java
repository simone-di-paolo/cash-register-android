package com.dev.simonedipaolo.cashregister.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.dev.simonedipaolo.cashregister.R;

/**
 * Created by Simone Di Paolo on 30/07/2022.
 */
public class SettingsFragment extends Fragment {

    private Button configureRepartoCiboFragment;
    private Button configureRepartoBibiteFragment;
    private Button configureRepartoCocktailFragment;
    private Button configureRepartoAltroFragment;

    private NavController navController;

    public SettingsFragment() {
        // empty
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        container.removeAllViews();
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        navController = Navigation.findNavController(getActivity().findViewById(R.id.fragmentPlaceholder));
        initializeViews(v);

        return v;
    }

    private void initializeViews(View v) {
        // button configura cibo
        configureRepartoCiboFragment = v.findViewById(R.id.configure_rep_cibo_button);
        configureRepartoCiboFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = SettingsFragmentDirections.actionSettingsFragmentToConfiguraRepartiFragment(0);
                navController.navigate(action);
            }
        });

        // button configura cibo
        configureRepartoBibiteFragment = v.findViewById(R.id.configure_rep_bibite_button);
        configureRepartoBibiteFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = SettingsFragmentDirections.actionSettingsFragmentToConfiguraRepartiFragment(1);
                navController.navigate(action);
            }
        });

        // button configura cibo
        configureRepartoCocktailFragment = v.findViewById(R.id.configure_rep_cocktail_button);
        configureRepartoCocktailFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = SettingsFragmentDirections.actionSettingsFragmentToConfiguraRepartiFragment(2);
                navController.navigate(action);
            }
        });

        // button configura cibo
        configureRepartoAltroFragment = v.findViewById(R.id.configure_rep_altro_button);
        configureRepartoAltroFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = SettingsFragmentDirections.actionSettingsFragmentToConfiguraRepartiFragment(3);
                navController.navigate(action);
            }
        });
    }

}
