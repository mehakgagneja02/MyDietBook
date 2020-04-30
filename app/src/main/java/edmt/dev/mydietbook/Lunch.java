package edmt.dev.mydietbook;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Lunch extends Fragment {

    Button lbtn,lbutton;
    EditText lunchtxt,lcalories;
    ArrayList<String> list;
    ListView llist;
    ArrayAdapter<String> larrayAdapter;
    TextView lcal,historylunch;
    DatabaseReference mDatabaseReference;
    public FirebaseAuth firebaseAuth;
    public Lunch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater,final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);

        View root = inflater.inflate(R.layout.activity_lunch, container, false);
        lbtn= root.findViewById(R.id.lbtn);
        lunchtxt=root.findViewById(R.id.lunchtxt);
        llist=root.findViewById(R.id.llist);
        lcalories=root.findViewById(R.id.lcalories);
        lbutton=root.findViewById(R.id.lbutton);
        lcal = root.findViewById(R.id.lcal);
        historylunch=root.findViewById(R.id.historylunch);

        firebaseAuth = FirebaseAuth.getInstance();

        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("Date_info", 0);
        String strdate = sharedPreferences1.getString("Date", "");

        FirebaseUser user;
        user = firebaseAuth.getCurrentUser();
        String uid = user.getUid();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Food&Calorie").child(uid).child(strdate).child("Lunch");

        list = new ArrayList<String>();
        larrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list);
        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lunch = lunchtxt.getText().toString();
                list.add(lunch);
                llist.setAdapter(larrayAdapter);
                larrayAdapter.notifyDataSetChanged();
                storefood();
            }
        });
        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String calorie = lcalories.getText().toString();
                lcal.setText(calorie);
                storecal();
            }
        });
        historylunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),historylunch.class);
                startActivity(i);
            }
        });
        return root;
    }
    public void storefood(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(calendar.getTime());
        String id = mDatabaseReference.push().getKey();
        String text = lunchtxt.getText().toString();
        Db db=new Db(id,date,text);
        mDatabaseReference.child(text).setValue(db);
    }
    public void storecal(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(calendar.getTime());
        String id = mDatabaseReference.push().getKey();
        String text = lcal.getText().toString();
        Db db=new Db(id,date,text);
        mDatabaseReference.child(text).setValue(db);
    }
}
