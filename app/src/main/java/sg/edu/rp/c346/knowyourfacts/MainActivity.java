package sg.edu.rp.c346.knowyourfacts;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragmentAdapter adapter;
    ViewPager vPager;
    Button btnReadLater;

    int reqCode = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = (ViewPager) findViewById(R.id.viewpager1);
        btnReadLater = (Button) findViewById (R.id.buttonReadLater);

        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());
        al.add(new Frag3());

        adapter = new MyFragmentAdapter(fm, al);

        vPager.setAdapter(adapter);

        btnReadLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, 5);

                Intent intent = new Intent(MainActivity.this,
                        ScheduledNotificationReciever.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_previous) {
            if (vPager.getCurrentItem() > 0){
                int previousPage = vPager.getCurrentItem() - 1;
                vPager.setCurrentItem(previousPage, true);
            }
        } else if (id == R.id.action_next) {
            int max = vPager.getChildCount();
            if (vPager.getCurrentItem() < max-1){
                int nextPage = vPager.getCurrentItem() + 1;
                vPager.setCurrentItem(nextPage, true);
            }
        } else if (id == R.id.action_random) {
            int max = vPager.getChildCount();
            Random r = new Random();
            int randomInt = r.nextInt(max - 0 + 1);
            vPager.setCurrentItem(randomInt, true);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        int index = vPager.getCurrentItem();
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putInt("index", index);
        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        int index = prefs.getInt("index", 0);
        vPager.setCurrentItem(index, true);

    }
}
