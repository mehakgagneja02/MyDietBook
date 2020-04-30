package edmt.dev.mydietbook;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;

public class Fragment extends AppCompatActivity {
    private ViewPager mPager;
    private static final int NUM_PAGES = 4;
    private PagerAdapter pagerAdapter;
    private TabLayout tablayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        mPager = findViewById(R.id.vp);
        mPager.setOffscreenPageLimit(NUM_PAGES);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

        tablayout = findViewById(R.id.maintab);
        tablayout.setupWithViewPager(mPager);

    }

}
