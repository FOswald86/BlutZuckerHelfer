package at.foswald86.blutzuckerhelfer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 0;
    private TextView countdownText;
    private Button add15MinButton;
    private Button add30MinButton;
    private Button add60MinButton;
    private Button add240MinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countdownText = findViewById(R.id.txt_timer);
        add15MinButton = findViewById(R.id.btn_add15min);
        add30MinButton = findViewById(R.id.btn_add30min);
        add60MinButton = findViewById(R.id.btn_add1h);
        add240MinButton = findViewById(R.id.btn_add4h);

        add15MinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTime(15 * 60 * 1000); // add 15 minutes
            }
        });

        add30MinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTime(30 * 60 * 1000); // add 30 minutes
            }
        });

        add60MinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTime(60 * 60 * 1000); // add 60 minutes
            }
        });

        add240MinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTime(240 * 60 * 1000); // add 240 minutes
            }
        });

        startTimer(0); // start the timer with 0 initial time
    }

    private void startTimer(long startTimeInMilliseconds) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(startTimeInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                // do something when the timer finishes
            }
        };

        countDownTimer.start();
    }

    private void addTime(long timeToAddInMilliseconds) {
        startTimer(timeLeftInMilliseconds + timeToAddInMilliseconds);
    }

    private void updateCountdownText() {
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countdownText.setText(timeLeftFormatted);
    }
}
