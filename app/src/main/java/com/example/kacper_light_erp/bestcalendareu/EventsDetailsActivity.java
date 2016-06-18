package com.example.kacper_light_erp.bestcalendareu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Kacper-Light-ERP on 2016-06-18.
 */
public class EventsDetailsActivity extends ActionBarActivity {
    public static final String EVENT_KEY = "event";

    private Event event;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_event_details);

        Button button = (Button) findViewById(R.id.button);
        final ScrollView sv = (ScrollView) findViewById(R.id.scroll);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sv.scrollTo(0,0);
            }
        });

        Intent i = getIntent();
        event = (Event) i.getExtras().getSerializable(EVENT_KEY);

        showEvent(event);
    }

    private void showEvent(Event event) {
        TextView title = (TextView) findViewById(R.id.Tytul);
        TextView time = (TextView) findViewById(R.id.Godzina);
        TextView description = (TextView) findViewById(R.id.Opis);

        title.setText(event.getTytul());
        time.setText(event.getGodzina());
        description.setText(event.getOpis());
    }
}
