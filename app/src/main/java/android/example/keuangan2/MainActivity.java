package android.example.keuangan2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy", /*Locale.getDefault()*/Locale.ENGLISH);

    private CompactCalendarView compactCalendarView;

    private boolean isExpanded = false;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        appBarLayout = findViewById(R.id.app_bar_layout);
        compactCalendarView = findViewById(R.id.compactcalendar_view);
        compactCalendarView.setShouldDrawDaysHeader(true);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                setSubtitle(dateFormat.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                setSubtitle(dateFormat.format(firstDayOfNewMonth));
            }
        });

        // Set current date to today
        setCurrentDate(new Date());

        final ImageView arrow = findViewById(R.id.date_picker_arrow);

        RelativeLayout datePickerButton = findViewById(R.id.date_picker_button);

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rotation = isExpanded ? 0 : 180;
                ViewCompat.animate(arrow).rotation(rotation).start();

                isExpanded = !isExpanded;
                appBarLayout.setExpanded(isExpanded, true);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Add", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,Choose_pengeluaran.class);
                startActivity(intent);
            }
        });

    }
    private void setCurrentDate(Date date) {
        setSubtitle(dateFormat.format(date));
        if (compactCalendarView != null) {
            compactCalendarView.setCurrentDate(date);
        }
    }
    @Override
    public void setTitle(CharSequence title) {
        TextView tvTitle = findViewById(R.id.title);

        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }
    private void setSubtitle(String subtitle) {
        TextView datePickerTextView = findViewById(R.id.date_picker_text_view);

        if (datePickerTextView != null) {
            datePickerTextView.setText(subtitle);
        }
    }


}

