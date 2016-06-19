package com.example.kacper_light_erp.bestcalendareu;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kacper-Light-ERP on 2016-06-18.
 */
public class EventsAdapter extends BaseAdapter {

    private List<Event> eventsList = new ArrayList<>();
    private Context context;

    public EventsAdapter(Context context) {
        this.context = context;
    }

    public void setEvents(Collection<Event> events) {
        eventsList.clear();
        eventsList.addAll(events);
    }

    @Override
    public int getCount() {
        return eventsList.size();
    }

    @Override
    public Event getItem(int position) {
        return eventsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View eventView;

        if (convertView == null) {
            eventView = LayoutInflater.from(context).inflate(R.layout.list_view_details, parent, false);
        } else {
            eventView = convertView;
        }

        Event event = getItem(position);

        bindEventToView(event, eventView, position);

        return eventView;
    }

    private void bindEventToView(Event event, View eventView, int position) {
        TextView eventTitle = (TextView) eventView.findViewById(R.id.textView);
        eventTitle.setText(event.getTytul());

        TextView eventTime = (TextView) eventView.findViewById(R.id.textView3);
        eventTime.setText(event.getGodzina());
    }

}

