package rs.ac.ni.pmf.greeting2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class DetailsActivity extends AppCompatActivity {

    public static final String TAG = "Greeting_info";
    private int _defaultAge = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        final Intent intent = getIntent();
        _defaultAge = intent.getIntExtra("DEFAULT_AGE", -1);

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
        resultIntent.putExtra("AGE_DATA", age);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}