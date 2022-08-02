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

/**
 * Created by Simone Di Paolo on 31/07/2022.
 */
public class RepartiAdapter extends RecyclerView.Adapter<RepartiAdapter.RepartiViewHolder> {

    private Context context;
    private Fragment fragment;

    private String repartiNames[];
    private int repartiImages[];
    private int editIcon;
    private int index;
    private int howManyReparti;

    public RepartiAdapter(Context context, Fragment fragment, String repartiNames[], int repartiImages[], int editIcon, int index) {
        this.context = context;
        this.fragment = fragment;
        this.repartiNames = repartiNames;
        this.repartiImages = repartiImages;
        this.editIcon = editIcon;
        this.index = index;
        this.howManyReparti = 0;
    }

    public RepartiAdapter(Context context, Fragment fragment, String repartiNames[], int repartiImages[], int index, int editIcon, int howManyReparti) {
        this.context = context;
        this.fragment = fragment;
        this.repartiNames = repartiNames;
        this.repartiImages = repartiImages;
        this.editIcon = editIcon;
        this.index = index;
        this.howManyReparti = howManyReparti;
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
        // TODO leggere quanti reparti sono stati creati nella memoria e creare N reparti con N nomi letti
        if(howManyReparti != 0) {
            holder.text.setText(repartiNames[0]);
            holder.image.setImageResource(repartiImages[index]);
        } else {
            holder.text.setText(repartiNames[position]);
            holder.image.setImageResource(repartiImages[index]);
        }
    }

    @Override
    public int getItemCount() {
        if(howManyReparti != 0) {
            return howManyReparti;
        }
        return repartiNames.length;
    }

    // inner class
    public class RepartiViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageView image;

        public RepartiViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.repartiRowText);
            image = itemView.findViewById(R.id.rowIcon);
        }
    }

}
