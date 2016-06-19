package com.example.kacper_light_erp.bestcalendareu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BestCalendarEU extends AppCompatActivity {
    Handler handler;
    Data SelectedDate = new Data();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_calendar_eu);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        handler = new Handler();
        //Date date = Calendar.getInstance().getTime();
        //SelectedDate.setDat(date.toString());
        Button dodaj = (Button) findViewById(R.id.newevent);

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), AddEventActivity.class);
                        startActivity(intent);
                    }
                }, 250);
            }
        });

        Button lista = (Button) findViewById(R.id.list);
        
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), CalendarTaskList.class);
                        intent.putExtra(CalendarTaskList.SELECTED_DATE, SelectedDate);
                        startActivity(intent);
                    }
                }, 250);
            }
        });

        Button about = (Button) findViewById(R.id.about);

        assert about != null;
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), AboutAuthors.class);
                        startActivity(intent);
                    }
                }, 250);
            }
        });

        Button calendar = (Button) findViewById(R.id.calendar);

        assert calendar != null;
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), DisplayCalendar.class);
                        startActivity(intent);
                    }
                }, 250);
            }
        });



        CalendarView cv= (CalendarView) findViewById(R.id.calendarView);
        if (cv != null) {
            cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    SelectedDate.setDat(Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(dayOfMonth));

                }
            });
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void startAddEventActivity() {
        Intent i = new Intent(this, AddEventActivity.class);
        startActivity(i);
    }

    private void startAboutAuthorsActivity() {
        Intent i = new Intent(this, AboutAuthors.class);
        startActivity(i);
    }

    private void startCalendarTaskList() {
        Intent i = new Intent(this, CalendarTaskList.class);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_event: {
                startAddEventActivity();
                return true;
            }
            case R.id.about:{
                startAboutAuthorsActivity();
                return true;
            }
            case R.id.calendar_list_view:{
                startCalendarTaskList();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }

    }

}
