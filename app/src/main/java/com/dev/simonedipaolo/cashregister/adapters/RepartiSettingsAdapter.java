package com.dev.simonedipaolo.cashregister.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.simonedipaolo.cashregister.R;
import com.dev.simonedipaolo.cashregister.entities.Reparto;
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
    private List<Reparto> reparti;

    public RepartiSettingsAdapter(Context context, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;

        db = OpenDatabase.openDB(fragment.getContext(), ConstantsUtils.DATABASE_NAME);
        reparti = db.standDao().getAllReparto();
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
        // setto il testo nell'edit text leggendolo dal database
        holder.editText.setText(reparti.get(holder.getAdapterPosition()).getNomeReparto());

        // gestisco il save del singolo row
        holder.saveIcon.setImageResource(R.drawable.ic_baseline_save_24);
        holder.saveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.standDao().updateReparto(new Reparto(reparti.get(holder.getAdapterPosition()).getRepartoUid(), holder.editText.getText().toString()));
                reparti = db.standDao().getAllReparto();
                notifyItemRangeChanged(holder.getAdapterPosition(), getItemCount());
            }
        });

        // gestisco il delete del singolo row
        holder.deleteIcon.setImageResource(R.drawable.ic_baseline_delete_24);
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cancella elemento presente in DB ed aggiorna l'adapter
                db.standDao().deleteReparto(new Reparto(reparti.get(holder.getAdapterPosition()).getRepartoUid(), holder.editText.getText().toString()));
                reparti = db.standDao().getAllReparto();
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), getItemCount());
            }
        });
    }

    public void addNewReparto() {
        db.standDao().insertReparto(new Reparto());
        reparti = db.standDao().getAllReparto();
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

        public RepartiViewHolder(@NonNull View itemView) {
            super(itemView);
            editText = itemView.findViewById(R.id.repartoNameEditField);
            saveIcon = itemView.findViewById(R.id.saveIcon);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
        }
    }

}
