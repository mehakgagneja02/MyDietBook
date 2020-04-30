package edmt.dev.mydietbook;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Profile extends AppCompatActivity {

    public TextView txtname,txtwelcome,txtdate;
    public EditText name;
    public Button btnname, btndate;
    Calendar c;
    DatePickerDialog dpd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        txtname=findViewById(R.id.txtname);
        txtwelcome=findViewById(R.id.txtwelcome);
        name=findViewById(R.id.name);
        btnname=findViewById(R.id.btnname);
        btndate=findViewById(R.id.btndate);
        txtdate=findViewById(R.id.txtdate);


        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month=c.get(Calendar.MONTH);
                int day =c.get(Calendar.DAY_OF_MONTH);

                dpd = new DatePickerDialog(Profile.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet( DatePicker datePicker, int myear, int mmonth,
                                                   int mday) {
                                txtdate.setText(mday + "/" + mmonth + "/" + myear + " ");
                            }
                        }, year,month,day);
                dpd.show();
            }
        });

        btnname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Display.class);
                startActivity(i);
                String strname = name.getText().toString();
                String strdate = txtdate.getText().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("Name_info",0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Name",strname);
                editor.commit();
                SharedPreferences sharedPreferences1 = getSharedPreferences("Date_info",0);
                SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                editor1.putString("Date",strdate);
                editor1.commit();
            }
        });
    }
}
