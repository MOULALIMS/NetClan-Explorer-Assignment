package com.example.demoapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class RefineActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner available;
    private EditText editText;
    private Button save;
    private SeekBar rating;
    private TextView display_distance;
    private ImageView return_back;
    private MaterialButtonToggleGroup toggleGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_refine);

        return_back = findViewById(R.id.return_back_btn);
        available = findViewById(R.id.spinner_availability);
        editText = findViewById(R.id.user_status);
        save = findViewById(R.id.refine_submit_btn);
        rating = findViewById(R.id.rating_bar);
        display_distance = findViewById(R.id.count_distance);
        toggleGroup = findViewById(R.id.toggle_group);

        setUpSpinner1();

        rating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                display_distance.setText("Distance: " + i + " km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        available.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Selected: " + selected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Show Your Preference", Toast.LENGTH_SHORT).show();
            }
        });

        return_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RefineActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RefineActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        toggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked) {
                    Button selectedButton = findViewById(checkedId);
                    String buttonText = selectedButton.getText().toString();
                    Toast.makeText(getApplicationContext(), "Selected: " + buttonText, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUpSpinner1() {
        String[] items = {
                "Available | Hey Let Us Connect",
                "Away | Stay Discrete And Watch",
                "Busy | Do not Disturb | Will Catch Up Later",
                "SOS | Emergency | Need Assistance | HELP"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        available.setAdapter(adapter);
        available.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),"Selected : "+ adapterView.getItemAtPosition(i),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getApplicationContext(),"nothing selected",Toast.LENGTH_LONG).show();
    }
}
