package com.example.prak5_alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private TextView mTextview;
    private View mTextView;
    private Object TimePickerview;
    private Object TimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.TextView);

        Button buttonTimePicker = (Button)
                findViewById(R.id.button_time_picker);
        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"Time Picker");
            }
        });
        Button buttonCancelAlarm = findViewById(R.id.button_cancel);
        buttonCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }

            private void cancelAlarm() {
            }
        });
        @Override
        public void onTimeSet(TimePicker Object view;
        view, int hourOfDay, int minute) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY,hourOfDay);
            c.set(Calendar.MINUTE,minute);
            c.set(Calendar.SECOND,0);
            updateTimeText(c);
            startAlarm(c);
        }
        private void updateTimeText(Calendar Calendar c;
        c){
            String timeText = "Alarm set for: ";
            timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
            mTextView.setText(timeText);
        }
        private void startAlarm(Calendar c){
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this,AlertReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);
            if(c.before(Calendar.getInstance())){ c.add(Calendar.DATE,1);
            }
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
        }
        private void cancelAlarm(){
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this,AlertReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);
            alarmManager.cancel(pendingIntent);

            mTextView.setText("Alarm Canceled");
        }
    }

    private void startAlarm(Calendar c) {
    }

    private void updateTimeText(Calendar c) {
    }
}
