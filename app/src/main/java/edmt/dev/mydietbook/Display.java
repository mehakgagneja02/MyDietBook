package edmt.dev.mydietbook;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class Display extends AppCompatActivity {

    TextView tv1, tv2, tv3, tv4;
    Button btncontinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        SharedPreferences sharedPreferences = getSharedPreferences("Name_info", 0);
        String strname = sharedPreferences.getString("Name", "");

        SharedPreferences sharedPreferences1 = getSharedPreferences("Date_info", 0);
        String strdate = sharedPreferences1.getString("Date", "");

        tv2 = findViewById(R.id.tv2);
        tv1 = findViewById(R.id.tv1);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        btncontinue=findViewById(R.id.btncontinue);
        tv2.setText(strname + " ");
        tv4.setText(strdate + " ");
        text1_text2_text3();

        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Fragment.class);
                startActivity(i);
            }
        });
    }

    void text1_text2_text3() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);
                tv4.setVisibility(View.VISIBLE);
                btncontinue.setVisibility(View.VISIBLE);
                Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
                tv1.startAnimation(animFadeIn);
                tv2.startAnimation(animFadeIn);
                tv3.startAnimation(animFadeIn);
                tv4.startAnimation(animFadeIn);
                btncontinue.startAnimation(animFadeIn);
            }
        }, 700);
    }
}