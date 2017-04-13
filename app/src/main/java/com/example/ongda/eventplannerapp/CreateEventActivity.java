package com.example.ongda.eventplannerapp;

import android.app.AlarmManager;
import android.app.DatePickerDialog;

import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.GregorianCalendar;

import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.Serializable;
import java.text.ParseException;

import java.sql.Date;
import java.sql.Time;

public class CreateEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,
                                                                      TimePickerDialog.OnTimeSetListener
{

    Event newEvent;
    TextView EventStartDate, EventEndDate;
    TextView EventStartTime, EventEndTime;
    private boolean isEventStartSelected, isEventEndSelected;
    private boolean isEventStartTimeSelected, isEventEndTimeSelected;

    java.util.Calendar calSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Globals.initalize();
        Globals.setEventAdded(false);

        newEvent = new Event();
        isEventStartSelected = false;
        isEventEndSelected = false;
        isEventStartTimeSelected = false;
        isEventEndTimeSelected = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
    }

    public void setEventName() {
        EditText eventName = (EditText) findViewById(R.id.etEventName);
        newEvent.setName(eventName.getText().toString());
    }

    public void setEventType() {
        Spinner eventType = (Spinner) findViewById(R.id.spnEventType);
        newEvent.setType(eventType.getSelectedItem().toString());
    }

    public void setEventDescription() {
        EditText eventDescription = (EditText) findViewById(R.id.etEventDescription);
        newEvent.setDescription(eventDescription.getText().toString());
    }

    public void setEventLocation() {
        EditText eventLocation = (EditText) findViewById(R.id.etEventLocation);
        newEvent.setLocation(eventLocation.getText().toString());
    }

    // open date picker when clicking date field
    public void datePicker1(View v) {
        isEventStartSelected = true;
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "date");
    }

    public void datePicker2(View v) {
        isEventEndSelected = true;
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), "date");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        System.out.println("Year: " + year + " Month: " + month + " Day: " + day);
        String monthName;
        // do stuff with the date
        switch(month) {
            case 0:
                monthName = "Jan.";
                break;
            case 1:
                monthName = "Feb.";
                break;
            case 2:
                monthName = "Mar.";
                break;
            case 3:
                monthName = "Apr.";
                break;
            case 4:
                monthName = "May.";
                break;
            case 5:
                monthName = "Jun.";
                break;
            case 6:
                monthName = "Jul.";
                break;
            case 7:
                monthName = "Aug.";
                break;
            case 8:
                monthName = "Sep.";
                break;
            case 9:
                monthName = "Oct.";
                break;
            case 10:
                monthName = "Nov.";
                break;
            case 11:
                monthName = "Dec.";
                break;
            default:
                monthName = "Invalid month";
                break;
        }

        String dateString = monthName + " " + String.valueOf(day) + " " + String.valueOf(year);
        EventStartDate = (TextView) findViewById(R.id.tvEventStartDate);
        EventEndDate = (TextView) findViewById(R.id.tvEventEndDate);

        if (isEventEndSelected) {
            newEvent.setEndDate(dateString);
            EventEndDate.setText(dateString);
            isEventEndSelected = false;
        } else if (isEventStartSelected) {
            newEvent.setStartDate(dateString);
            EventStartDate.setText(dateString);
            isEventStartSelected = false;
        } else {
            System.out.println("Date not selected");
        }

    }

    public void timePicker1(View v) {
        isEventStartTimeSelected = true;
        System.out.println("Time picker1 selected");
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.show(getFragmentManager(), "time");
    }
    public void timePicker2(View v) {
        isEventEndTimeSelected = true;
        System.out.println("Time picker2 selected");
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.show(getFragmentManager(), "time");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        System.out.println("Hour: " + hourOfDay + " Minute: " + minute);
        String new_time;
        String stringMin = String.valueOf(minute);

        EventStartTime = (TextView) findViewById(R.id.tvEventStartTime);
        EventEndTime = (TextView) findViewById(R.id.tvEventEndTime);

//        time.set(java.util.Calendar.HOUR, hourOfDay);
//        time.set(java.util.Calendar.MINUTE, minute);
//        time.set(java.util.Calendar.SECOND, 0);


        java.util.Calendar calNow = java.util.Calendar.getInstance();
        calSet = (java.util.Calendar) calNow.clone();

        calSet.set(java.util.Calendar.HOUR_OF_DAY, hourOfDay);
        calSet.set(java.util.Calendar.MINUTE, minute);
        calSet.set(java.util.Calendar.SECOND, 0);
        calSet.set(java.util.Calendar.MILLISECOND, 0);

        if(calSet.compareTo(calNow) <= 0){
            //Today Set time passed, count to tomorrow
            calSet.add(java.util.Calendar.DATE, 1);
        }

        if (minute < 10) {
            stringMin = "0" + stringMin;
        }

        if (hourOfDay > 12) {
            hourOfDay = hourOfDay - 12;
            new_time = String.valueOf(hourOfDay) + ":" + stringMin + " PM" ;
        } else {
            new_time = String.valueOf(hourOfDay) + ":" + stringMin + " AM" ;
        }

        if (isEventStartTimeSelected) {
            newEvent.setStartTime(new_time);
            System.out.println("Event Start Time: " + newEvent.getEndDate());
            EventStartTime.setText(newEvent.getStartTime());
            isEventStartTimeSelected = false;
        } else if (isEventEndTimeSelected) {
            newEvent.setEndTime(new_time);
            System.out.println("Event End Time: " + newEvent.getEndTime());
            EventEndTime.setText(newEvent.getEndTime());
            isEventEndTimeSelected = false;
        } else {
            System.out.println("Time not selected");
        }
    }

    public void doneButtonClicked(View v) {
        Button doneBtn = (Button) findViewById(R.id.btnDone);

        setEventName();
        setEventType();
        setEventLocation();
        setEventDescription();

        Globals.getEvents().add(newEvent);

        /* initialize alarm */
        Intent intentAlarm = new Intent(this, AlarmReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        Globals.setEventAdded(true);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    public void cancelButtonClicked(View v) {
        Globals.setEventAdded(false);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
