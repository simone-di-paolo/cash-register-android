package com.dev.simonedipaolo.cashregister.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dev.simonedipaolo.cashregister.R;

/**
 * Created by Simone Di Paolo on 28/07/2022.
 */
public class CashRegisterFragment extends Fragment {

    public CashRegisterFragment() {
        // empty
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cash_register, container, false);

        return v;
    }
}
