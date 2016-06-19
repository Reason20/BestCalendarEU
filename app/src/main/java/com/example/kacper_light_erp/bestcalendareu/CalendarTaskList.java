package com.example.kacper_light_erp.bestcalendareu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;


public class CalendarTaskList extends ActionBarActivity {

    public static final String SELECTED_DATE = "date";
    public String SelectedDate;
    private static final String TAG = CalendarTaskList.class.getSimpleName();


    private ListView lv;
    private Handler handler;

    private EventsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_task_list);

        handler = new Handler();

        Intent i = getIntent();
        SelectedDate = new String();
        SelectedDate = i.getExtras().getString(SELECTED_DATE);
        Toast.makeText(CalendarTaskList.this, SelectedDate, Toast.LENGTH_LONG).show();

        initializeList();

        new Thread(new Runnable() {

            @Override
            public void run() {
                getEventsFromNetwork();
            }
        }).start();
    }

    private void getEventsFromNetwork() {
        try {
            fetchEvents();
        } catch (IOException e) {
            Log.e(TAG, "IOException while fetching recipes", e);
            showToast("IOException while fetching recipes");
        } catch (JSONException e) {
            Log.e(TAG, "JSONException while fetching recipes", e);
            showToast("JSONException while fetching recipes");
        }
    }

    private void fetchEvents() throws IOException, JSONException {
        final NetworkEventsProvider networkEventsProvider = new NetworkEventsProvider(this);
        networkEventsProvider.getEvents(new NetworkEventsProvider.OnEventsDownloadedListener() {

            @Override
            public void onEventsDownloaded() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        Log.d(TAG, "Fetched " + networkEventsProvider.getEventsNumber() + " events");
                        adapter.setEvents(networkEventsProvider.getAllEvents());
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }, SelectedDate);
    }

    private void showToast(final String tost) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CalendarTaskList.this, tost, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.notifyDataSetChanged();
    }

    private void initializeList() {
        lv = (ListView) findViewById(R.id.listView);
        adapter = new EventsAdapter(this);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = adapter.getItem(position);
                showEvent(event);
            }
        });
    }

    private void showEvent(Event event) {
        Intent i = new Intent(this, EventsDetailsActivity.class);

        i.putExtra(EventsDetailsActivity.EVENT_KEY, event);

        startActivity(i);
    }

}
