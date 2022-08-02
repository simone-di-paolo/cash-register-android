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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.dev.simonedipaolo.cashregister.R;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Simone Di Paolo on 01/08/2022.
 */
public class EditRepartoDialog extends DialogFragment {

    private EditText repartoNameEditText;
    private EditRepartoDialogListener editRepartoDialogListener;

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
                        EditRepartoDialog.this.dismiss();
                    }
                })
                .setPositiveButton("Salva", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newRepartoName = repartoNameEditText.getText().toString();
                        if(StringUtils.isNotBlank(newRepartoName)) {
                            editRepartoDialogListener.repartoNameRefactor(newRepartoName);
                            EditRepartoDialog.this.dismiss();
                        }
                    }
                });
        return builder.create();
    }

    public static void openDialog(FragmentManager fragmentManager) {
        EditRepartoDialog editRepartoDialog = new EditRepartoDialog();
        editRepartoDialog.show(fragmentManager, "Modifica reparto");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            editRepartoDialogListener = (EditRepartoDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement EditRepartoDialogListener to MainActivity");
        }
    }

    public interface EditRepartoDialogListener {
        void repartoNameRefactor(String newRepartoName);
    }

}
