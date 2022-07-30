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

    public static final String PERSON_DATA = "PERSON_DATA";

    private EditText _editFistName;
    private EditText _editLastName;
    private EditText _editAge;

    private Person _person;


    public static ActivityResultContract<Person, Person> DETAILS_ACTIVITY_CONTRACT = new ActivityResultContract<Person, Person>() {
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Person input) {
            final Intent intent = new Intent(context, DetailsActivity.class);
            //intent.putExtra(DEFAULT_AGE, input);
            intent.putExtra(PERSON_DATA, input);
            return intent;
        }

        @Override
        public Person parseResult(int resultCode, @Nullable Intent intent) {
            if (resultCode == RESULT_CANCELED) {
                Log.i(TAG, "Age selection canceled");
                return null;
            }

            if (resultCode == RESULT_OK) {
                if (intent == null) {
                    return null;
                }
                return (Person) intent.getParcelableExtra(PERSON_DATA);

            }
            return null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //TextView editAge = findViewById(R.id.editAge);
        //editAge.setText(age);

        _editFistName = findViewById(R.id.editFirstName);
        _editLastName = findViewById(R.id.editLastName);
        _editAge = findViewById(R.id.editAge);

        final Intent intent = getIntent();
        _person = (Person) intent.getParcelableExtra(PERSON_DATA);

        showPersonData();

        findViewById(R.id.btnAcceptDetails).setOnClickListener(this::accept);
        findViewById(R.id.btnCancel).setOnClickListener(this::cancel);
    }

    private void showPersonData() {
        if(_person != null){
            _editFistName.setText(_person.getFirstName());
            _editLastName.setText(_person.getLastName());
            _editAge.setText(String.valueOf(_person.getAge()));
        }
    }

    private void accept(View view) {

        final String firstName = _editFistName.getText().toString();
        final String lastName = _editLastName.getText().toString();
        final String editAge = _editAge.getText().toString();

        int age;

        try {
            age = Integer.parseInt(editAge);
        }catch (final Exception e){
            Log.i(TAG, e.getMessage());
            age = _defaultAge;
        }

        final Intent resultIntent = new Intent();//prazan jer ne pokrece akciju
        resultIntent.putExtra(PERSON_DATA, new Person(firstName,lastName,age));

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}