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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BestCalendarEU extends AppCompatActivity {
    Handler handler;
    String SelectedDate;
    ArrayList<Event> listaEventow = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_calendar_eu);
        handler = new Handler();
        Date foo = new Date();
        SelectedDate = foo.toString();
        Log.wtf("", SelectedDate);
        findEvents();
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
                    SelectedDate=Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(dayOfMonth);
                    findEvents();
                }
            });
        }
    }
    public void findEvents(){
        ConnectionClass conn = new ConnectionClass();
        try {
            Connection con = conn.CONN();
            if (con == null) {
                //throw new SQLException();
            } else {
                listaEventow.clear();
                String query = "select * from Events where Data='"+SelectedDate+"'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                Event event = new Event();
                while (rs.next())
                {
                    event = new Event();
                    event.setTytul(rs.getObject(2).toString());
                    event.setData(rs.getObject(3).toString());
                    event.setGodzina(rs.getObject(4).toString());
                    event.setOpis(rs.getObject(5).toString());
                    listaEventow.add(event);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_best_calendar_eu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
