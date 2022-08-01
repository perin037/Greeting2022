package rs.ac.ni.pmf.greeting2022;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MultiChoiseDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final String[] values = {"Jedan", "Dva", "Tri"};
        builder
                .setTitle("Izaberi vrednost")
                .setMultiChoiceItems(values, new boolean[]{true, false, true}, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        Log.i(GreetingActivity.TAG, "Selected: " +i);
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Log.i(GreetingActivity.TAG, "Done, Selected index: " + _selected);
                    }
                })
                .create();

        return builder.show();

    }
}
