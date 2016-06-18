package com.example.kacper_light_erp.bestcalendareu;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutAuthors extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_authors);

        final Button sendMessage = (Button) findViewById(R.id.sendMessage);
        assert sendMessage != null;
        sendMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"support@bestcalendareu.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "BestCallendarEU");
                i.putExtra(Intent.EXTRA_TEXT, "Your BestCallendarEU APP Rocks!");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(AboutAuthors.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



}
