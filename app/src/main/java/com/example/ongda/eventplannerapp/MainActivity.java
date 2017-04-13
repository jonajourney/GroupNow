package com.example.ongda.eventplannerapp;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity {

    Event newEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CoordinatorLayout cordLayout = (CoordinatorLayout) findViewById(R.id.CordLayout);

        if (Globals.isEventAdded()) {
            Snackbar.make(cordLayout, "Successfully created an Event", Snackbar.LENGTH_SHORT)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int eventAddedIndex = Globals.getEvents().size() - 1;
                            Globals.getEvents().remove(eventAddedIndex);
                        }
                    })
                    .show();
        } else {
            Snackbar.make(cordLayout, "Canceled an Event", Snackbar.LENGTH_SHORT).show();
        }

        showEvents();

    }

    public void showEvents() {
        int size = Globals.getEvents().size();
        for (int i=0; i<size; i++) {
//            if (Globals.getEvents().get(i).getType());


            System.out.println("Event: " + Globals.getEvents().get(i).getName());
        }
    }


}
