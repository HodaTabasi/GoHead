package com.app.goaheadapp.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.app.goaheadapp.fragment.notelist.NoteFragment;
import com.app.goaheadapp.fragment.setting.AboutUsFragment;
import com.app.goaheadapp.fragment.setting.ContactUsFragment;
import com.app.goaheadapp.fragment.profile.DriverProfileFragment;
import com.app.goaheadapp.fragment.ratedriver.ListDriverRateFragment;
import com.app.goaheadapp.fragment.notifacation.NotificationFragment;
import com.app.goaheadapp.fragment.drivertrak.OrderDriverTrakingFragment;
import com.app.goaheadapp.fragment.privacy.PrivacyFragment;
import com.app.goaheadapp.fragment.setting.ShareAppFragment;
import com.app.goaheadapp.R;
import com.app.goaheadapp.Utils.FragmentsUtil;
import com.app.goaheadapp.models.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import io.paperdb.Paper;

public class MainDriverActivity extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout mContainer;

    private FrameLayout mMyContainer;
    private ImageView mMore;
    private ImageView mUncheckedNotificationImage;
    private ConstraintLayout mCheckedNotificationImage;
    private FrameLayout mNotification;
    private ImageView mUncheckedNoteImage;
    private ConstraintLayout mCheckedNoteImage;
    private FrameLayout mNotes;
    private ImageView mUncheckedOrderImage;
    private ConstraintLayout mCheckedOrderImage;
    private FrameLayout mOrder;


    private int REQUEST_EXTERNAL_STORAGE = 1;

    DrawerLayout drawer;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_driver);

        getPermission();

        initView();

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(selectedListener);

        FragmentsUtil.addFragment(this, R.id.my_container, new OrderDriverTrakingFragment());
    }

    NavigationView.OnNavigationItemSelectedListener selectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.profile:
                    FragmentsUtil.replaceFragment(MainDriverActivity.this, R.id.my_container, new DriverProfileFragment());
                    break;
                case R.id.ratting:
                    FragmentsUtil.replaceFragment(MainDriverActivity.this, R.id.my_container, new ListDriverRateFragment());
                    break;
                case R.id.about:
                    FragmentsUtil.replaceFragment(MainDriverActivity.this, R.id.my_container, new AboutUsFragment());
                    break;

                case R.id.contact:
                    FragmentsUtil.replaceFragment(MainDriverActivity.this, R.id.my_container, new ContactUsFragment());
                    break;
                case R.id.policy:
                    FragmentsUtil.replaceFragment(MainDriverActivity.this, R.id.my_container, new PrivacyFragment());
                    break;
                case R.id.share:
                    FragmentsUtil.replaceFragment(MainDriverActivity.this, R.id.my_container, new ShareAppFragment());
                    break;
                case R.id.rate:
//                    FragmentsUtil.replaceFragment(MainDriverActivity.this, R.id.my_container, new ProfileFragment());
                    break;
                case R.id.logout:
                    User user = Paper.book().read("data");
                    Paper.book().destroy();
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("user_"+user.getId());
                    Intent logout = new Intent(MainDriverActivity.this, Splash.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    logout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(logout);
                    finish();
                    break;
            }
            onBackPressed();
            return false;
        }
    };

    private void initView() {
        mMore = (ImageView) findViewById(R.id.more);
        mMore.setOnClickListener(this);
        mUncheckedOrderImage = (ImageView) findViewById(R.id.unchecked_order_image);
        mCheckedOrderImage = (ConstraintLayout) findViewById(R.id.checked_order_image);
        mContainer = (ConstraintLayout) findViewById(R.id.container);
        mMyContainer = (FrameLayout) findViewById(R.id.my_container);
        mUncheckedNotificationImage = (ImageView) findViewById(R.id.unchecked_notification_image);
        mCheckedNotificationImage = (ConstraintLayout) findViewById(R.id.checked_notification_image);
        mNotification = (FrameLayout) findViewById(R.id.notification);
        mNotification.setOnClickListener(this);
        mUncheckedNoteImage = (ImageView) findViewById(R.id.unchecked_note_image);
        mCheckedNoteImage = (ConstraintLayout) findViewById(R.id.checked_note_image);
        mNotes = (FrameLayout) findViewById(R.id.notes);
        mNotes.setOnClickListener(this);
        mOrder = (FrameLayout) findViewById(R.id.order);
        mOrder.setOnClickListener(this);
    }

    private void getPermission() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "please acespt for request ", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, REQUEST_EXTERNAL_STORAGE);
            }

        } else {
//            Toast.makeText(this, "Generate", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        mPermissionGranted = false;
        if (requestCode == REQUEST_EXTERNAL_STORAGE) {

            Toast.makeText(this, "Generate", Toast.LENGTH_SHORT).show();
            //                if (grantResults.length > 0) {
//                    for (int i = 0; i < grantResults.length; i++) {
//                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
//                            mPermissionGranted = false;
//                            Log.e("fff2", "2");
//                            return;
//                        }
//                    }
//                    mPermissionGranted = true;
//                    Log.e("fff2", "1");
//                    //initialize our map
//                }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.more:
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START);
                } else {
                    drawer.closeDrawer(GravityCompat.END);
                }
                break;
            case R.id.notification:
                mCheckedNoteImage.setVisibility(View.GONE);
                mUncheckedNoteImage.setVisibility(View.VISIBLE);

                mCheckedNotificationImage.setVisibility(View.VISIBLE);
                mUncheckedNotificationImage.setVisibility(View.GONE);
                mCheckedOrderImage.setVisibility(View.GONE);
                mUncheckedOrderImage.setVisibility(View.VISIBLE);
                mMyContainer.setVisibility(View.VISIBLE);
                FragmentsUtil.replaceFragment(this, R.id.my_container, new NotificationFragment());
                break;
            case R.id.notes:
                mUncheckedNoteImage.setVisibility(View.GONE);
                mCheckedNoteImage.setVisibility(View.VISIBLE);
                mCheckedNotificationImage.setVisibility(View.GONE);
                mUncheckedNotificationImage.setVisibility(View.VISIBLE);
                mCheckedOrderImage.setVisibility(View.GONE);
                mUncheckedOrderImage.setVisibility(View.VISIBLE);
                mMyContainer.setVisibility(View.VISIBLE);

                Bundle bundle = new Bundle();
                bundle.putBoolean("isnote",true);
                NoteFragment fragment = new NoteFragment();
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(this, R.id.my_container, fragment);

                break;
            case R.id.order:
                mCheckedNoteImage.setVisibility(View.GONE);
                mUncheckedNoteImage.setVisibility(View.VISIBLE);
                mCheckedNotificationImage.setVisibility(View.GONE);
                mUncheckedNotificationImage.setVisibility(View.VISIBLE);
                mCheckedOrderImage.setVisibility(View.VISIBLE);
                mUncheckedOrderImage.setVisibility(View.GONE);
                mMyContainer.setVisibility(View.VISIBLE);
                FragmentsUtil.replaceFragment(this, R.id.my_container, new OrderDriverTrakingFragment());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof DriverProfileFragment) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onBackPressed() {
        //Checks if the navigation drawer is open -- If so, close it
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        // If drawer is already close -- Do not override original functionality
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle(getResources().getString(R.string.home));
    }
}