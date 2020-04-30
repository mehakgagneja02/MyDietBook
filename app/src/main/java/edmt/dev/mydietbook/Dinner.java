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

public class Dinner extends Fragment {
    Button dbtn,dbutton;
    EditText dinnertxt,dcalories;
    ArrayList<String> list;
    ListView dlist;
    ArrayAdapter<String> darrayAdapter;
    TextView dcal,historydinner;
    DatabaseReference mDatabaseReference;
    public FirebaseAuth firebaseAuth;

    public Dinner() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);

        View root= inflater.inflate(R.layout.activity_dinner, container, false);
        dbtn= root.findViewById(R.id.dbtn);
        dinnertxt=root.findViewById(R.id.dinnertxt);
        dlist=root.findViewById(R.id.dlist);
        dcalories=root.findViewById(R.id.dcalories);
        dbutton=root.findViewById(R.id.dbutton);
        dcal = root.findViewById(R.id.dcal);
        historydinner=root.findViewById(R.id.historydinner);

        firebaseAuth = FirebaseAuth.getInstance();

        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("Date_info", 0);
        String strdate = sharedPreferences1.getString("Date", "");

        FirebaseUser user;
        user = firebaseAuth.getCurrentUser();
        String uid = user.getUid();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Food&Calorie").child(uid).child(strdate).child("Dinner");

        list = new ArrayList<String>();
        darrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list);
        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dinner = dinnertxt.getText().toString();
                list.add(dinner);
                dlist.setAdapter(darrayAdapter);
                darrayAdapter.notifyDataSetChanged();
                storefood();
            }
        });
        dbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String calorie = dcalories.getText().toString();
                dcal.setText(calorie);
                storecal();
            }
        });
        historydinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),historydinner.class);
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
        String text = dinnertxt.getText().toString();
        Db db=new Db(id,date,text);
        mDatabaseReference.child(text).setValue(db);
    }
    public void storecal(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(calendar.getTime());
        String id = mDatabaseReference.push().getKey();
        String text = dcal.getText().toString();
        Db db=new Db(id,date,text);
        mDatabaseReference.child(text).setValue(db);
    }
}
