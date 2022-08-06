package com.dev.simonedipaolo.cashregister.fragments;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.simonedipaolo.cashregister.R;
import com.dev.simonedipaolo.cashregister.adapters.CashRegisterRepartiAdapter;
import com.dev.simonedipaolo.cashregister.entities.CashRegister;
import com.dev.simonedipaolo.cashregister.entities.TipologiaReparto;
import com.dev.simonedipaolo.cashregister.room.StandDatabase;
import com.dev.simonedipaolo.cashregister.utils.ConstantsUtils;
import com.dev.simonedipaolo.cashregister.utils.OpenDatabase;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Simone Di Paolo on 28/07/2022.
 */
public class CashRegisterFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    // CAULCULATORS BUTTONS
    private Button[][] buttons;

    private Button button0;
    private Button clearButton;
    private Button buttonComma;

    private TextView calculatorTextView;

    private Button settingsButton;

    // ---- START CALCULATORS THINGS -----
    private String subtotal;
    // ---- END CALCULATORS THINGS -----

    private Spinner dropdown;

    private RecyclerView recyclerView;

    private int repartiImages[];
    private CashRegisterRepartiAdapter cashRegisterRepartiAdapter;


    private StandDatabase db;
    private CashRegister cashRegister;
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
        reparti = db.cashRegisterDao().getAllTipologiaReparto();

        List<CashRegister> listOfCashRegister = db.cashRegisterDao().getAllCashRegister();
        if (CollectionUtils.isEmpty(listOfCashRegister)) {
            // create just one cash register
            listOfCashRegister.add(new CashRegister());
            db.cashRegisterDao().insertStand(new CashRegister());
        }

        nomiTipologiaReparto = reparti.stream()
                .map(tipologiaReparto -> tipologiaReparto.getTipologiaReparto())
                .collect(Collectors.toList());

        isNomiTipologiaRepartoEmpty = !CollectionUtils.isNotEmpty(nomiTipologiaReparto);

        subtotal = "0";

        calculatorButtonsInitializer(v);
        viewsInitializer(v);
        createDropdownList();

        if(CollectionUtils.isNotEmpty(reparti)) {
            // custom adapter for reparto
            cashRegisterRepartiAdapter = new CashRegisterRepartiAdapter(this.getContext(), this, reparti.get(0).getListaReparti(), isNomiTipologiaRepartoEmpty);
            recyclerView.setAdapter(cashRegisterRepartiAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        }
        return v;
    }

    private void calculatorButtonsInitializer(View v) {

        calculatorTextView = v.findViewById(R.id.calculatorTextView);
        calculatorTextView.setText("0");

        buttons = new Button[3][3];

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                String buttonID = "calculator_button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getActivity().getPackageName());
                buttons[i][j] = (Button) v.findViewById(resID);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!StringUtils.equals(subtotal, "0")) {
                            subtotal += ((Button) v.findViewById(resID)).getText().toString();
                        } else {
                            subtotal = ((Button) v.findViewById(resID)).getText().toString();
                        }
                        calculatorTextView.setText(subtotal);
                    }
                });
            }
        }

        // buttons
        button0 = v.findViewById(R.id.calculator_button_zero);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!StringUtils.equals(subtotal, "0")) {
                    subtotal += button0.getText().toString();
                    calculatorTextView.setText(subtotal);
                }
            }
        });

        // comma
        buttonComma = v.findViewById(R.id.commaButton);
        buttonComma.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(!StringUtils.equals(subtotal, "0")) {
                    DecimalFormat df = new DecimalFormat("#.#", DecimalFormatSymbols.getInstance());
                    subtotal += df.getDecimalFormatSymbols().getDecimalSeparator();
                    calculatorTextView.setText(subtotal);
                }
            }
        });

        // canc button
        clearButton = v.findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(subtotal.length() > 1) {
                    subtotal = subtotal.substring(0, subtotal.length()-1);
                    calculatorTextView.setText(subtotal);
                } else {
                    subtotal = "0";
                    calculatorTextView.setText(subtotal);
                }
            }
        });
        clearButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                subtotal = "0";
                calculatorTextView.setText(subtotal);
                return true;
            }
        });
    }

    private void viewsInitializer(View v) {

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
        cashRegisterRepartiAdapter = new CashRegisterRepartiAdapter(this.getContext(), this, reparti.get(i).getListaReparti(), isNomiTipologiaRepartoEmpty);
        recyclerView.setAdapter(cashRegisterRepartiAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // empty
    }

}
