package com.dev.simonedipaolo.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.simonedipaolo.cashregister.entities.Reparto;
import com.dev.simonedipaolo.cashregister.entities.TipologiaReparto;
import com.dev.simonedipaolo.cashregister.fragments.SettingsFragmentDirections;
import com.dev.simonedipaolo.cashregister.room.StandDatabase;
import com.dev.simonedipaolo.cashregister.utils.ConstantsUtils;
import com.dev.simonedipaolo.cashregister.utils.OpenDatabase;

import java.util.List;

/**
 * Created by Simone Di Paolo on 31/07/2022.
 */
public class TipologiaRepartiSettingsAdapter extends RecyclerView.Adapter<TipologiaRepartiSettingsAdapter.RepartiViewHolder> {

    private Context context;
    private Fragment fragment;

    private NavController navController;

    private StandDatabase db;
    private List<TipologiaReparto> reparti;

    public TipologiaRepartiSettingsAdapter(Context context, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;

        db = OpenDatabase.openDB(fragment.getContext(), ConstantsUtils.DATABASE_NAME);
        reparti = db.standDao().getAllTipologiaReparto();
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
        // setto il testo nell'edit text leggendolo dal database
        holder.editText.setText(reparti.get(holder.getAdapterPosition()).getTipologiaReparto());

        // gestisco il save del singolo row
        holder.saveIcon.setImageResource(R.drawable.ic_baseline_save_24);
        holder.saveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.standDao().updateTipologiaReparto(new TipologiaReparto(reparti.get(holder.getAdapterPosition()).getTipologiaRepartoUid(), holder.editText.getText().toString()));
                reparti = db.standDao().getAllTipologiaReparto();
                notifyItemRangeChanged(holder.getAdapterPosition(), getItemCount());
            }
        });

        // gestisco il delete del singolo row
        holder.deleteIcon.setImageResource(R.drawable.ic_baseline_delete_24);
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cancella elemento presente in DB ed aggiorna l'adapter
                db.standDao().deleteTiplogoaReparto(new TipologiaReparto(reparti.get(holder.getAdapterPosition()).getTipologiaRepartoUid(), holder.editText.getText().toString()));
                reparti = db.standDao().getAllTipologiaReparto();
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), getItemCount());
            }
        });

        holder.configureRepartiForTipologiaRepartoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = SettingsFragmentDirections.actionSettingsFragmentToConfiguraRepartiFragment(holder.getAdapterPosition());
                navController.navigate(action);
            }
        });
    }

    public void addNewReparto() {
        db.standDao().insertTipologiaReparto(new TipologiaReparto());
        reparti = db.standDao().getAllTipologiaReparto();
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
            saveIcon = itemView.findViewById(R.id.tipologia_reparto_save_button);
            deleteIcon = itemView.findViewById(R.id.tipologia_reparto_delete_button);
            configureRepartiForTipologiaRepartoButton = itemView.findViewById(R.id.tipologia_reparto_conf_reparti_button);
        }
    }

}
