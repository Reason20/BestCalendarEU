package com.example.kacper_light_erp.bestcalendareu;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.util.Calendar;

public class AddEventActivity extends AppCompatActivity {

    private static final String EVENTS_URL = "http://androidProject2.azurewebsites.net/Ev/Add";

    String yolo;
    TextView godzina;
    TextView data;
    Button godzButton;
    Button datButton;
    EditText tytul;
    EditText opis;
    Button zatw;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        godzina = (TextView) findViewById(R.id.godz_pocz);
        data = (TextView) findViewById(R.id.data_wybrana);
        godzButton = (Button) findViewById(R.id.timePicker);
        datButton = (Button) findViewById(R.id.data_but);
        tytul = (EditText) findViewById(R.id.title);
        opis = (EditText) findViewById(R.id.description);
        zatw = (Button) findViewById(R.id.save_button);

        godzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                TimePickerDialog dateDial = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        String niceHour = String.valueOf(hourOfDay);
                        String niceMinute = String.valueOf(minute);
                        if (minute < 10)
                            niceMinute = "0" + minute;
                        if (hourOfDay < 10)
                            niceHour = "0" + hourOfDay;
                        godzina.setText(niceHour + ":" + niceMinute);

                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                dateDial.show();
            }
        });
        datButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dateDial = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        monthOfYear += 1;
                        String niceMonth = String.valueOf(monthOfYear);
                        String niceDay = String.valueOf(dayOfMonth);
                        data.setText("" + year + "-" + niceMonth + "-" + niceDay);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dateDial.show();


            }
        });
        zatw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event event = new Event();
                event.setData(data.toString());
                event.setGodzina(godzina.toString());
                event.setOpis(opis.toString());
                event.setTytul(tytul.toString());
                yolo = event.toJSON();
                Log.d("",yolo);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AddEventToDB(yolo);
                    }
                }).start();
                Toast.makeText(AddEventActivity.this, "Probujemy dodac wydarzenie", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void AddEventToDB(String a) {
        try {
            new NetworkRequest(EVENTS_URL, HttpMethod.POST, a).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }*/
}

