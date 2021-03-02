package com.example.facemaker;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;

/*
* @author Rachel Madison
* implements listeners that change the appearance of the Face
* */
public class FaceController implements AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private Face myFace;
    public SeekBar redSeekBar;
    public SeekBar greenSeekBar;
    public SeekBar blueSeekBar;


    public FaceController(Face face, SeekBar redSeek, SeekBar greenSeek, SeekBar blueSeek) {
        myFace = face;
        redSeekBar = redSeek;
        greenSeekBar = greenSeek;
        blueSeekBar = blueSeek;
    }

    //Spinner methods
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //selecting hairstyle
        myFace.hairStyle = position;
        myFace.invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    //seekBar methods
    /*
    * looks for which radio button is checked, then resets the color of that part of the face
    * according to the changes made to the seekbar
    * */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        /**
         External Citation
         Date:     1 March 2021
         Problem:  Color of item was changing randomly when new radio button was clicked
         Resource: Professor Tribelhorn
         Solution: I added the if(fromUser) statement that he suggested which fixed the problem
         */
        if(fromUser){
            if (myFace.skinChecked) {
                if (seekBar.getId() == R.id.blueSeekBar) {
                    myFace.skinBlue = progress;
                }
                else if (seekBar.getId() == R.id.GreenSeekBar) {
                    myFace.skinGreen = progress;
                }
                else if (seekBar.getId() == R.id.RedSeekBar) {
                    myFace.skinRed = progress;
                }
                myFace.skinColor = Color.rgb(myFace.skinRed,myFace.skinGreen,myFace.skinBlue);
            }
            else if (myFace.eyeChecked) {
                if (seekBar.getId() == R.id.blueSeekBar) {
                    myFace.eyeBlue = progress;
                }
                else if (seekBar.getId() == R.id.GreenSeekBar) {
                    myFace.eyeGreen = progress;
                }
                else if (seekBar.getId() == R.id.RedSeekBar) {
                    myFace.eyeRed = progress;
                }
                myFace.eyeColor = Color.rgb(myFace.eyeRed,myFace.eyeGreen,myFace.eyeBlue);
            }
            else if (myFace.hairChecked) {
                if (seekBar.getId() == R.id.blueSeekBar) {
                    myFace.hairBlue = progress;
                }
                else if (seekBar.getId() == R.id.GreenSeekBar) {
                    myFace.hairGreen = progress;
                }
                else if (seekBar.getId() == R.id.RedSeekBar) {
                    myFace.hairRed = progress;
                }
                myFace.hairColor = Color.rgb(myFace.hairRed,myFace.hairGreen,myFace.hairBlue);
            }
            myFace.invalidate();
        }
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }




    //random button
    /*
    * randomizes the colors and hairstyle
    * if there was already a radiobutton checked it changes the seekBar values to the new
    * color that was randomly generated
    * */
    @Override
    public void onClick(View v) {

        myFace.randomize();

        //for the case where you already have something checked and then hit the randomize button
        if (myFace.hairChecked) {
            redSeekBar.setProgress(Color.red(myFace.hairColor));
            blueSeekBar.setProgress(Color.blue(myFace.hairColor));
            greenSeekBar.setProgress(Color.green(myFace.hairColor));
        }
        else if (myFace.skinChecked) {
            redSeekBar.setProgress(Color.red(myFace.skinColor));
            blueSeekBar.setProgress(Color.blue(myFace.skinColor));
            greenSeekBar.setProgress(Color.green(myFace.skinColor));
        }
        else if (myFace.eyeChecked) {
            redSeekBar.setProgress(Color.red(myFace.eyeColor));
            blueSeekBar.setProgress(Color.blue(myFace.eyeColor));
            greenSeekBar.setProgress(Color.green(myFace.eyeColor));
        }

        myFace.invalidate();

    }


    //radio buttons
    /*
    * sets seekbars according to which radiobutton is checked
    * */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.HairRadioButton) {
            myFace.hairChecked = true;
            myFace.skinChecked = false;
            myFace.eyeChecked = false;
            myFace.hairSetRGB(myFace.hairColor);
            redSeekBar.setProgress(myFace.hairRed);
            blueSeekBar.setProgress(myFace.hairBlue);
            greenSeekBar.setProgress(myFace.hairGreen);

        }
        else if (checkedId == R.id.SkinRadioButton) {
            myFace.hairChecked = false;
            myFace.skinChecked = true;
            myFace.eyeChecked = false;
            myFace.skinSetRGB(myFace.skinColor);
            redSeekBar.setProgress(myFace.skinRed);
            blueSeekBar.setProgress(myFace.skinBlue);
            greenSeekBar.setProgress(myFace.skinGreen);

        }
        else if (checkedId == R.id.EyesRadioButton) {
            myFace.hairChecked = false;
            myFace.skinChecked = false;
            myFace.eyeChecked = true;
            myFace.eyeSetRGB(myFace.eyeColor);
            redSeekBar.setProgress(myFace.eyeRed);
            blueSeekBar.setProgress(myFace.eyeBlue);
            greenSeekBar.setProgress(myFace.eyeGreen);

        }

        myFace.invalidate();


    }
}
