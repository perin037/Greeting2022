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

public class MyDialog extends DialogFragment{

    public interface MyDialogListener {
        void onYes();
        void onNo();
        void onCancel();
    }

    private MyDialogListener _listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        _listener = (MyDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder
                .setTitle("Delete Data?")
                .setMessage("Do you realy want to delete data?!")
                .setPositiveButton("Yes", (dialogInterface, i) -> _listener.onYes())
                .setNegativeButton("No", (dialogInterface, i) -> _listener.onNo())
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        _listener.onCancel();
                    }
                });
        return builder.create();
        // return super.onCreateDialog(savedInstanceState);
    }
}
