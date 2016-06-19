package com.example.kacper_light_erp.bestcalendareu;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kacper-Light-ERP on 2016-06-18.
 */
public class NetworkEventsProvider {
    private static final String EVENTS_URL = "http://androidProject2.azurewebsites.net/Ev/List";

    private final Context context;

    private final List<Event> events = new ArrayList<>();

    public interface OnEventsDownloadedListener {
        void onEventsDownloaded();
    }

    public NetworkEventsProvider(Context context) {
        this.context = context;
    }

    public void getEvents(OnEventsDownloadedListener listener, String SELECTED_DATE) throws IOException, JSONException {
        if (isOnline()) {
            SELECTED_DATE="{ \"Data\" : \""+SELECTED_DATE+"\" }";
            String s = downloadEvents(SELECTED_DATE);
            JSONObject eventObject = new JSONObject(s);
            JSONArray eventArray = eventObject.getJSONArray("returnedList");


            for (int i = 0; i < eventArray.length(); ++i) {
                JSONObject eventsObject = eventArray.getJSONObject(i);

                Event event = new Event();
                        event.setId(eventsObject.getInt("Id"));
                        event.setTytul(eventsObject.getString("Tytul"));
                        event.setData(eventsObject.getString("Data"));
                        event.setGodzina(eventsObject.getString("Godzina"));
                        event.setOpis(eventsObject.getString("Opis"));

                events.add(event);
            }

            listener.onEventsDownloaded();
        } else {
            Toast.makeText(context, "Nie moge polaczyc sie z baza danych", Toast.LENGTH_LONG).show();
        }
    }

    public int getEventsNumber() {
        return events.size();
    }

    public List<Event> getAllEvents() {
        return events;
    }

    private String downloadEvents(String dataJSON) throws IOException {
        return new NetworkRequest(EVENTS_URL, HttpMethod.POST, dataJSON).execute();
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
