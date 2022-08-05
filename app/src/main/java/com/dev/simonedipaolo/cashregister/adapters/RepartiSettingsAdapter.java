package com.dev.simonedipaolo.cashregister.adapters;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.simonedipaolo.cashregister.R;
import com.dev.simonedipaolo.cashregister.entities.Reparto;
import com.dev.simonedipaolo.cashregister.entities.TipologiaReparto;
import com.dev.simonedipaolo.cashregister.fragments.ConfiguraRepartiFragmentArgs;
import com.dev.simonedipaolo.cashregister.room.StandDatabase;
import com.dev.simonedipaolo.cashregister.utils.ConstantsUtils;
import com.dev.simonedipaolo.cashregister.utils.EditRepartoDialog;
import com.dev.simonedipaolo.cashregister.utils.OpenDatabase;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Simone Di Paolo on 31/07/2022.
 */
public class RepartiSettingsAdapter extends RecyclerView.Adapter<RepartiSettingsAdapter.RepartiViewHolder> {

    private Context context;
    private Fragment fragment;

    private StandDatabase db;
    private List<TipologiaReparto> reparti;
    private int tipologiaRepartoIndex;

    public RepartiSettingsAdapter(Context context, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;

        db = OpenDatabase.openDB(fragment.getContext(), ConstantsUtils.DATABASE_NAME);
        reparti = db.standDao().getAllTipologiaReparto();
        // default value
        this.tipologiaRepartoIndex = 0;
    }

    public RepartiSettingsAdapter(Context context, Fragment fragment, int tipologiaRepartoIndex) {
        this.context = context;
        this.fragment = fragment;

        db = OpenDatabase.openDB(fragment.getContext(), ConstantsUtils.DATABASE_NAME);
        reparti = db.standDao().getAllTipologiaReparto();
        this.tipologiaRepartoIndex = tipologiaRepartoIndex;
    }

    @NonNull
    @Override
    public RepartiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.edit_reparti_row, parent, false);

        return new RepartiViewHolder((v));
    }

    @Override
    public void onBindViewHolder(@NonNull RepartiViewHolder holder, int position) {
        // prendo il name del reparto in base alla tipologia reparto che ho cliccato
        String repartoName = reparti.get(tipologiaRepartoIndex).getListaReparti().get(holder.getAdapterPosition());

        holder.editText.setText(repartoName);
        holder.editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_DONE) {
                    // prendo la tipologia reparto
                    TipologiaReparto tempTipologiaReparto = reparti.get(tipologiaRepartoIndex);
                    // prendo la lista dei nomi
                    List<String> nomiRepartiToUpdate = tempTipologiaReparto.getListaReparti();
                    // aggiorno il nome all'i-esima posizione
                    nomiRepartiToUpdate.set(holder.getAdapterPosition(), holder.editText.getText().toString());
                    //sostituisco la lista nella tipologia reparto
                    tempTipologiaReparto.setListaReparti(nomiRepartiToUpdate);

                    // aggiorno la tipologia reparto nel DB
                    db.standDao().updateTipologiaReparto(tempTipologiaReparto);
                    reparti = db.standDao().getAllTipologiaReparto();
                    notifyItemRangeChanged(holder.getAdapterPosition(), getItemCount());

                    return false;
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
                // prendo la tipologia reparto
                TipologiaReparto tempTipologiaReparto = reparti.get(tipologiaRepartoIndex);
                // prendo la lista dei nomi
                List<String> nomiRepartiToUpdate = tempTipologiaReparto.getListaReparti();
                // aggiorno il nome all'i-esima posizione
                nomiRepartiToUpdate.remove(holder.getAdapterPosition());
                //sostituisco la lista nella tipologia reparto
                tempTipologiaReparto.setListaReparti(nomiRepartiToUpdate);
                // aggiorno la tipologia reparto nel DB
                db.standDao().updateTipologiaReparto(tempTipologiaReparto);
                reparti = db.standDao().getAllTipologiaReparto();
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    public void addNewReparto() {
        String tempReparto = context.getResources().getString(R.string.default_reparto_name);
        List<String> tempListaRepartiToUpdate = reparti.get(tipologiaRepartoIndex).getListaReparti();
        tempListaRepartiToUpdate.add(tempReparto);
        reparti.get(tipologiaRepartoIndex).setListaReparti(tempListaRepartiToUpdate);

        db.standDao().updateTipologiaReparto(reparti.get(tipologiaRepartoIndex));
        reparti = db.standDao().getAllTipologiaReparto();
        notifyItemInserted(reparti.get(tipologiaRepartoIndex).getListaReparti().size());
    }

    public boolean saveAllRepartiInDatabase() {
        return false;
    }


    @Override
    public int getItemCount() {
        return reparti.get(tipologiaRepartoIndex).getListaReparti().size();
    }


    // inner class
    public class RepartiViewHolder extends RecyclerView.ViewHolder {

        private EditText editText;
        //private ImageView saveIcon;
        private ImageView deleteIcon;

        public RepartiViewHolder(@NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.repartoNameEditField);
            //saveIcon = itemView.findViewById(R.id.saveIcon);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
        }
    }

}
