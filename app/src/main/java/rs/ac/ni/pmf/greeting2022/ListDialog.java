package rs.ac.ni.pmf.greeting2022;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ListDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final String[] values = {"Jedan", "Dva", "Tri"};

        builder
                .setTitle("Izaberi vrednost")
                .setItems(values, (dialogInterface, i) -> Log.i(GreetingActivity.TAG, "Selected item: " + values[i]))
                .create();

        return builder.show();
    }
}
