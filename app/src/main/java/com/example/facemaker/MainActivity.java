package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

/*
 * @author Rachel Madison
 * Initializes listeners
 * */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set up seekbars
        SeekBar redSeekBar = findViewById(R.id.RedSeekBar);
        SeekBar blueSeekBar = findViewById(R.id.blueSeekBar);
        SeekBar greenSeekBar = findViewById(R.id.GreenSeekBar);
        //setting up surface view and controller
        Face faceView = (Face)findViewById(R.id.surfaceView);
        FaceController myFaceController = new FaceController(faceView, redSeekBar, blueSeekBar, greenSeekBar);
        //setting up the spinner
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.HairStyle, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(myFaceController);
        //set Seekbars listeners
        redSeekBar.setOnSeekBarChangeListener(myFaceController);
        blueSeekBar.setOnSeekBarChangeListener(myFaceController);
        greenSeekBar.setOnSeekBarChangeListener(myFaceController);
        //set up RadioGroup
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(myFaceController);
        //Set up the randomize button
        Button randomButton = findViewById(R.id.button);
        randomButton.setOnClickListener(myFaceController);

    }




}