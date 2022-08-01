package com.dev.simonedipaolo.cashregister.utils;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

import com.dev.simonedipaolo.cashregister.R;

/**
 * Created by Simone Di Paolo on 01/08/2022.
 */
public class EditRepartoDialog extends AppCompatDialogFragment {

    private EditText repartoNameEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.edit_reparto_dialog, null);

        repartoNameEditText = v.findViewById(R.id.nameRepartoEditText);

        builder.setView(v)
                .setTitle("Modifica reparto")
                .setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Salva", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }

    public static void openDialog(FragmentManager fragmentManager) {
        EditRepartoDialog editRepartoDialog = new EditRepartoDialog();
        editRepartoDialog.show(fragmentManager, "Modifica reparto");
    }

}
