package com.app.goaheadapp.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.goaheadapp.adapters.MyPagerAdapter;
import com.app.goaheadapp.R;
import com.app.goaheadapp.models.User;

import io.paperdb.Paper;

public class Sliders extends AppCompatActivity {

    private ViewPager mImageViewPager;
    private int dotscount;
    private ImageView[] dots;
    LinearLayout sliderDotspanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        if(Paper.book().contains("data")){
            User user = Paper.book().read("data");
            if (user.getType() == 1)
                startActivity(new Intent(this,MainDriverActivity.class));
            else
                startActivity(new Intent(this,MainOneActivity.class));

            finish();
        }


        mImageViewPager = (ViewPager) findViewById(R.id.pager);

        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), 0);
        mImageViewPager.setAdapter(myPagerAdapter);

        dotscount = myPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_indicator_default));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_indicator_selected));

        mImageViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_indicator_default));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_indicator_selected));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}