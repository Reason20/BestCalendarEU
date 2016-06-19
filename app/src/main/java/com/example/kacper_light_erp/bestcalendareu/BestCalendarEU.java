package com.example.kacper_light_erp.bestcalendareu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
        handler = new Handler();
        Date date = Calendar.getInstance().getTime();
        SelectedDate.setDat(date.toString());
        Log.d("",SelectedDate.getDat());
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
                        intent.putExtra(CalendarTaskList.SELECTED_DATE, SelectedDate.getDat());
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
                    SelectedDate.setDat(Integer.toString(year)+"-"+Integer.toString(month+1)+"-"+Integer.toString(dayOfMonth));

                }
            });
        }
    }

}
