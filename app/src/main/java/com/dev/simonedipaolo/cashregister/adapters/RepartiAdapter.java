package com.dev.simonedipaolo.cashregister.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.simonedipaolo.cashregister.R;
import com.dev.simonedipaolo.cashregister.utils.EditRepartoDialog;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * Created by Simone Di Paolo on 31/07/2022.
 */
public class RepartiAdapter extends RecyclerView.Adapter<RepartiAdapter.RepartiViewHolder> {

    private Context context;
    private Fragment fragment;

    private List<String> repartiNames;
    private int index;
    private int howManyReparti;
    private boolean isNomiTipologiaRepartoEmpty;

    public RepartiAdapter(Context context, Fragment fragment, List<String> repartiNames, boolean isNomiTipologiaRepartoEmpty) {
        this.context = context;
        this.fragment = fragment;
        this.repartiNames = repartiNames;
        this.index = index;
        this.howManyReparti = 0;
        this.isNomiTipologiaRepartoEmpty = isNomiTipologiaRepartoEmpty;
    }

    @NonNull
    @Override
    public RepartiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.reparti_row, parent, false);

        return new RepartiViewHolder((v));
    }

    @Override
    public void onBindViewHolder(@NonNull RepartiViewHolder holder, int position) {
        if (isNomiTipologiaRepartoEmpty) {
            holder.text.setHint(R.string.configura_reparti_nelle_impostazioni);
        } else {
            if (CollectionUtils.isNotEmpty(repartiNames)) {
                holder.text.setText(repartiNames.get(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        return repartiNames.size();
    }

    // inner class
    public class RepartiViewHolder extends RecyclerView.ViewHolder {

        TextView text;

        public RepartiViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.repartiRowText);
        }
    }

}
