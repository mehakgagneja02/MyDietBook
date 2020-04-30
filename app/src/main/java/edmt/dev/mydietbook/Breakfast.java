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

public class Breakfast extends Fragment {
    Button bbtn,bbutton;
    EditText breakfasttxt,bcalories;
    ArrayList<String> list;
    ListView blist;
    ArrayAdapter<String> barrayAdapter;
    TextView bcal,history;
    DatabaseReference mDatabaseReference;
    public FirebaseAuth firebaseAuth;

    public Breakfast() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.activity_breakfast, container, false);
        bbtn= root.findViewById(R.id.bbtn);
        breakfasttxt=root.findViewById(R.id.breakfasttxt);
        bbutton=root.findViewById(R.id.bbutton);
        blist=root.findViewById(R.id.blist);
        bcalories=root.findViewById(R.id.bcalories);
        bcal = root.findViewById(R.id.bcal);
        history=root.findViewById(R.id.history);

        firebaseAuth = FirebaseAuth.getInstance();

        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("Date_info", 0);
        String strdate = sharedPreferences1.getString("Date", "1");

        FirebaseUser user;
        user = firebaseAuth.getCurrentUser();
        String uid = user.getUid();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Food&Calorie").child(uid).child(strdate).child("Breakfast");



        list = new ArrayList<String>();
        barrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list);
        bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String breakfast = breakfasttxt.getText().toString();
                list.add(breakfast);
                blist.setAdapter(barrayAdapter);
                barrayAdapter.notifyDataSetChanged();
                storefood();
            }
        });

        bbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String calorie = bcalories.getText().toString();
                bcal.setText(calorie);
                storecal();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),history.class);
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
        String text = breakfasttxt.getText().toString();
        Db db=new Db(id,date,text);
        mDatabaseReference.child(text).setValue(db);
    }
    public void storecal(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(calendar.getTime());
        String id = mDatabaseReference.push().getKey();
        String text = bcal.getText().toString();
        Db db=new Db(id,date,text);
        mDatabaseReference.child(text).setValue(db);
    }
    private boolean updateArtist(String id, String name, String genre) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("artists").child(id);

        //updating artist
        Db artist = new Artist(id, name, genre);
        dR.setValue(artist);
        Toast.makeText(getApplicationContext(), "Artist Updated", Toast.LENGTH_LONG).show();
        return true;
    }

}
