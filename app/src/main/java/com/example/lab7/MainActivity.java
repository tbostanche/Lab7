package com.example.lab7;

import static com.example.lab7.App.CHANNEL_1_ID;
import static com.example.lab7.App.CHANNEL_2_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;
    private Button channel1Button;
    private Button channel2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTitle = findViewById(R.id.titleEditText);
        editTextMessage = findViewById(R.id.messageEditText);

        notificationManager = NotificationManagerCompat.from(this);

        channel1Button = findViewById(R.id.channel1Button);
        channel2Button = findViewById(R.id.channel2Button);

        channel1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOnChannel1(view);
            }
        });

        channel2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOnChannel2(view);
            }
        });
    }

    private void sendOnChannel2(View view) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this, NotificationReciever.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_2_ID);
        notificationBuilder.setSmallIcon(R.drawable.ic_baseline_chat_bubble_outline_24);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(message);
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
        notificationBuilder.setCategory(NotificationCompat.CATEGORY_MESSAGE);
        notificationBuilder.setColor(Color.BLUE);
        notificationBuilder.setContentIntent(contentIntent);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setOnlyAlertOnce(true);
        notificationBuilder.addAction(R.mipmap.ic_launcher, "Toast", actionIntent);
        notificationManager.notify(1, notificationBuilder.build());
    }

    public void sendOnChannel1(View v) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this, NotificationReciever.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_1_ID);
        notificationBuilder.setSmallIcon(R.drawable.ic_baseline_chat_24);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(message);
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationBuilder.setCategory(NotificationCompat.CATEGORY_MESSAGE);
        notificationBuilder.setColor(Color.BLUE);
        notificationBuilder.setContentIntent(contentIntent);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setOnlyAlertOnce(true);
        notificationBuilder.addAction(R.mipmap.ic_launcher, "Toast", actionIntent);
        notificationManager.notify(1, notificationBuilder.build());
    }
}