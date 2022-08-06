package com.dev.simonedipaolo.cashregister.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.simonedipaolo.cashregister.R;
import com.dev.simonedipaolo.cashregister.entities.CashRegister;
import com.dev.simonedipaolo.cashregister.entities.TipologiaReparto;
import com.dev.simonedipaolo.cashregister.fragments.SettingsFragmentDirections;
import com.dev.simonedipaolo.cashregister.room.StandDatabase;
import com.dev.simonedipaolo.cashregister.utils.ConstantsUtils;
import com.dev.simonedipaolo.cashregister.utils.OpenDatabase;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Simone Di Paolo on 31/07/2022.
 */
public class TipologiaRepartiSettingsAdapter extends RecyclerView.Adapter<TipologiaRepartiSettingsAdapter.RepartiViewHolder> {

    private Context context;
    private Fragment fragment;

    private NavController navController;

    private StandDatabase db;
    private List<TipologiaReparto> reparti;
    private List<String> repartiNames;

    public TipologiaRepartiSettingsAdapter(Context context, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;

        db = OpenDatabase.openDB(fragment.getContext(), ConstantsUtils.DATABASE_NAME);
        reparti = db.tipologiaRepartoDao().getAllTipologiaReparto();

        navController = Navigation.findNavController(fragment.getActivity().findViewById(R.id.fragmentPlaceholder));
    }

    @NonNull
    @Override
    public RepartiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.tipologia_reparti_row, parent, false);

        return new RepartiViewHolder((v));
    }

    @Override
    public void onBindViewHolder(@NonNull RepartiViewHolder holder, int position) {
        // setto il testo nell'edit text o nell'hint leggendolo dal database
        String tipologiaRepartoName = reparti.get(holder.getAdapterPosition()).getTipologiaReparto();

        if(StringUtils.isEmpty(tipologiaRepartoName) || StringUtils.equals(tipologiaRepartoName, ConstantsUtils.DEFAULT_TIPOLOGIA_REPARTO_NAME)) {
            holder.editText.setHint(tipologiaRepartoName);
            holder.editText.getText().clear();
        } else {
            holder.editText.setText(tipologiaRepartoName);
        }

        holder.editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_DONE) {
                    TipologiaReparto tempTipologiaReparto = reparti.get(holder.getAdapterPosition());
                    tempTipologiaReparto.setTipologiaReparto(holder.editText.getText().toString());
                    db.tipologiaRepartoDao().updateTipologiaReparto(tempTipologiaReparto);

                    // update cash register
                    CashRegister cashRegister = db.cashRegisterDao().getAllCashRegister().get(0);
                    cashRegister.getListTipologiaReparti().set(holder.getAdapterPosition(), tempTipologiaReparto);
                    db.cashRegisterDao().updateCashRegister(cashRegister);

                    reparti = db.tipologiaRepartoDao().getAllTipologiaReparto();
                    notifyItemRangeChanged(holder.getAdapterPosition(), getItemCount());
                }
                return false;
            }
        });

        // gestisco il delete del singolo row
        holder.deleteIcon.setImageResource(R.drawable.ic_baseline_delete_24);
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cancella elemento presente in DB ed aggiorna l'adapter
                db.tipologiaRepartoDao().deleteTiplogiaReparto(new TipologiaReparto(reparti.get(holder.getAdapterPosition()).getTipologiaRepartoUid(), holder.editText.getText().toString()));

                // update cash register
                CashRegister cashRegister = db.cashRegisterDao().getAllCashRegister().get(0);
                cashRegister.getListTipologiaReparti().remove(holder.getAdapterPosition());
                db.cashRegisterDao().updateCashRegister(cashRegister);

                reparti = db.tipologiaRepartoDao().getAllTipologiaReparto();
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), getItemCount());
            }
        });

        // pulsante configura reparti in tipologia reparto
        holder.configureRepartiForTipologiaRepartoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = SettingsFragmentDirections.actionSettingsFragmentToConfiguraRepartiFragment(holder.getAdapterPosition());
                navController.navigate(action);
            }
        });
    }

    public void addNewTipologiaReparto() {
        db.tipologiaRepartoDao().insertTipologiaReparto(new TipologiaReparto());

        // update cash register
        CashRegister cashRegister = db.cashRegisterDao().getAllCashRegister().get(0);
        cashRegister.setListTipologiaReparti(db.cashRegisterDao().getAllTipologiaReparto());
        db.cashRegisterDao().updateCashRegister(cashRegister);

        reparti = db.cashRegisterDao().getAllTipologiaReparto();
        notifyItemInserted(reparti.size());
    }

    public boolean saveAllRepartiInDatabase() {
        return false;
    }


    @Override
    public int getItemCount() {
        return reparti.size();
    }

    // inner class
    public class RepartiViewHolder extends RecyclerView.ViewHolder {

        private EditText editText;
        private ImageView saveIcon;
        private ImageView deleteIcon;
        private Button configureRepartiForTipologiaRepartoButton;

        public RepartiViewHolder(@NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.tipologia_reparto_name_editText);
            //saveIcon = itemView.findViewById(R.id.tipologia_reparto_save_button);
            deleteIcon = itemView.findViewById(R.id.tipologia_reparto_delete_button);
            configureRepartiForTipologiaRepartoButton = itemView.findViewById(R.id.tipologia_reparto_conf_reparti_button);
        }
    }

}
