package rs.ac.ni.pmf.greeting2022;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class DetailsActivity extends AppCompatActivity {

    public static final String TAG = "Greeting_info";
    private int _defaultAge = -1;
    private static final String DEFAULT_AGE = "DEFAULT_AGE";
    private static final String AGE_DATA = "AGE_DATA";

    public static ActivityResultContract<Integer, Integer> DETAILS_ACTIVITY_CONTRACT = new ActivityResultContract<Integer, Integer>() {
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Integer input) {
            final Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra(DEFAULT_AGE, input);

            return intent;
        }

        @Override
        public Integer parseResult(int resultCode, @Nullable Intent intent) {
            if (resultCode == RESULT_CANCELED) {
                Log.i(TAG, "Age selection canceled");
                return null;
            }

            if (resultCode == RESULT_OK) {
                if (intent == null) {
                    return null;
                }
                return intent.getIntExtra(AGE_DATA, -1);

            }
            return null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        final Intent intent = getIntent();
        _defaultAge = intent.getIntExtra(DEFAULT_AGE, -1);

        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel(view);
            }
        });

        findViewById(R.id.btnAcceptDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accept(view);
            }
        });

        //TextView editAge = findViewById(R.id.editAge);
        //editAge.setText(age);
    }

    private void accept(View view) {
        final EditText text = findViewById(R.id.editAge);
        final String value = text.getText().toString();

        int age;

        try {
            age = Integer.parseInt(value);
        }catch (final Exception e){
            Log.i(TAG, e.getMessage());
            age = _defaultAge;
        }

        final Intent resultIntent = new Intent();//prazan jer ne pokrece akciju
        resultIntent.putExtra(AGE_DATA, age);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}