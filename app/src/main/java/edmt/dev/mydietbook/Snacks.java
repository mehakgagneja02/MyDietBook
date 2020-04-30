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

public class Snacks extends Fragment {
    Button sbtn,sbutton;
    EditText snackstxt,scalories;
    ArrayList<String> list;
    ListView slist;
    ArrayAdapter<String> sarrayAdapter;
    TextView scal,historysnacks;
    DatabaseReference mDatabaseReference;
    public FirebaseAuth firebaseAuth;

    public Snacks() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater,final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);

        View root = inflater.inflate(R.layout.activity_snacks, container, false);
        sbtn= root.findViewById(R.id.sbtn);
        snackstxt=root.findViewById(R.id.snackstxt);
        sbutton=root.findViewById(R.id.sbutton);
        slist=root.findViewById(R.id.slist);
        scalories=root.findViewById(R.id.scalories);
        scal = root.findViewById(R.id.scal);
        historysnacks=root.findViewById(R.id.historysnacks);

        firebaseAuth = FirebaseAuth.getInstance();

        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("Date_info", 0);
        String strdate = sharedPreferences1.getString("Date", "");

        FirebaseUser user;
        user = firebaseAuth.getCurrentUser();
        String uid = user.getUid();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Food&Calorie").child(uid).child(strdate).child("Snacks");

        list = new ArrayList<String>();
        sarrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list);
        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String snacks = snackstxt.getText().toString();
                list.add(snacks);
                slist.setAdapter(sarrayAdapter);
                sarrayAdapter.notifyDataSetChanged();
                storefood();
            }
        });
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String calorie = scalories.getText().toString();
                scal.setText(calorie);
                storecal();

            }
        });
        historysnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),historysnacks.class);
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
        String text = snackstxt.getText().toString();
        Db db=new Db(id,date,text);
        mDatabaseReference.child(text).setValue(db);
    }
    public void storecal(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(calendar.getTime());
        String id = mDatabaseReference.push().getKey();
        String text = scal.getText().toString();
        Db db=new Db(id,date,text);
        mDatabaseReference.child(text).setValue(db);
    }
}

