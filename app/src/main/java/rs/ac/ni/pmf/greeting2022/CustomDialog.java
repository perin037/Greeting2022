package rs.ac.ni.pmf.greeting2022;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CustomDialog extends DialogFragment {

    public interface CustomDialogListener{
        void onYes(String username, String password);
    }
    private CustomDialogListener _listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        _listener = (CustomDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View layout = getLayoutInflater().inflate(R.layout.custom_dialog, null);

        final EditText editUsername = layout.findViewById(R.id.username);
        final EditText editPassword = layout.findViewById(R.id.password);

        final AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder
                .setView(layout)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        _listener.onYes(editUsername.getText().toString(), editPassword.getText().toString());
                        }
                })
                .create();

        return builder.show();

    }
}
