package com.example.ongda.eventplannerapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.hsalf.smilerating.SmileRating;

/**
 * Created by Ongda on 4/12/17.
 */

public class AlarmPopUp extends Activity{
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        RatingBar rating = new RatingBar(this);
        SmileRating smileRating = (SmileRating) findViewById(R.id.smile_rating);
        View myView = getLayoutInflater().inflate(R.layout.smilerate, null);

        View star = getLayoutInflater().inflate(R.layout.starrating, null);

        rating.setMax(5);
        alertDialogBuilder.setPositiveButton("Rate", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Event Rated!",Toast.LENGTH_LONG).show();
                finish();

            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.setTitle("Event Rating");
        alertDialog.setView(myView);
        alertDialog.setMessage("Rate Event: ");
        alertDialog.show();
        alertDialog.show();



    }

}
