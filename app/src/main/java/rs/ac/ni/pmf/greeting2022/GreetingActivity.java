package rs.ac.ni.pmf.greeting2022;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GreetingActivity extends AppCompatActivity {

    public static final String TAG = "Greeting_info";

    private int REQUEST_DETAILS = 1;
    private int _currentAge = -1;

    private EditText _editText;
    private TextView _label;


    //dodatak zbog deprecated
    private ActivityResultLauncher<Intent> _detailsActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btnGreet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sayHello(view);
            }
        });

        _editText = findViewById(R.id.editName);
        _label = findViewById(R.id.labelHello);


        findViewById(R.id.btnShowDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetails(view);
            }
        });

        //dodatak
        _detailsActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> onDetailsActivityResult(result.getResultCode(), result.getData())
        );
    }



    public void sayHello(View view) {
        final String name = _editText.getText().toString();

        //final String greeting = getResources().getQuantityString(R.plurals., 11, name, 11);
        //final String greeting = getResources().getString(R.string.greeting, name);
        final String greeting = getResources().getString(R.string.greetingg, name, 45);

        _label.setText(greeting);
    }


    private void showDetails(View view) {
        final Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("DEFAULT_AGE", 20);

        //startActivity(intent);
        //startActivityForResult(intent, REQUEST_DETAILS);

        //dodatak
        _detailsActivityLauncher.launch(intent);
    }


    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_DETAILS){
            if(resultCode == RESULT_CANCELED){
                Log.i(TAG, "Age selection canceled");
            }
            if(resultCode == RESULT_OK){
                if(data == null){
                    return;
                }
                _currentAge = data.getIntExtra("AGE_DATA", -1);
                showCurrentAge();
            }
        }
    }*/

    //dodatak - umesto onActivityResult
    private void onDetailsActivityResult(int resultCode, Intent data) {
        if(resultCode == RESULT_CANCELED){
            Log.i(TAG, "Age selection canceled");
        }
        if(resultCode == RESULT_OK){
            if(data == null){
                return;
            }
            _currentAge = data.getIntExtra("AGE_DATA", -1);
            showCurrentAge();
        }

    }

    private void showCurrentAge() {
        final TextView currentAge = findViewById(R.id.labelCurrentAge);
        currentAge.setText("Current age " + _currentAge);
    }
}