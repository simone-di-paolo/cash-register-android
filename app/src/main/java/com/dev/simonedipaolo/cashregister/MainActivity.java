package com.dev.simonedipaolo.cashregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.dev.simonedipaolo.cashregister.fragments.CashRegisterFragment;
import com.dev.simonedipaolo.cashregister.utils.EditRepartoDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .add(R.id.fragmentPlaceholder, new CashRegisterFragment());
        fragmentTransaction.commit();
    }

}
