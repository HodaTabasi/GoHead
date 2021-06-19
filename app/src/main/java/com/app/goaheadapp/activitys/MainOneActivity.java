package com.app.goaheadapp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.app.goaheadapp.activitys.savedaddresses.SavedAddress;
import com.app.goaheadapp.fragment.notelist.NoteFragment;
import com.app.goaheadapp.fragment.setting.AboutUsFragment;
import com.app.goaheadapp.fragment.setting.ContactUsFragment;
import com.app.goaheadapp.fragment.deliverydetails.OrderDeiailsFragment;
import com.app.goaheadapp.fragment.favorites.ListMainFragment;
import com.app.goaheadapp.fragment.notifacation.NotificationFragment;
import com.app.goaheadapp.fragment.ordertraking.OrderTrackingFragment;
import com.app.goaheadapp.fragment.addpaymentmethod.PaymentMethodFragment;
import com.app.goaheadapp.fragment.privacy.PrivacyFragment;
import com.app.goaheadapp.fragment.profile.ProfileFragment;
import com.app.goaheadapp.fragment.setting.ShareAppFragment;
import com.app.goaheadapp.R;
import com.app.goaheadapp.Utils.FragmentsUtil;
import com.app.goaheadapp.models.User;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import io.paperdb.Paper;

public class MainOneActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mMore;
    private ImageView mUncheckedLikeImage;
    private ConstraintLayout mCheckedLikeImage;
    private FrameLayout mLike;
    private ImageView mUncheckedOrderImage;
    private ConstraintLayout mCheckedOrderImage;
    private FrameLayout mOrders;
    private ImageView mUncheckedHomeImage;
    private ConstraintLayout mCheckedHomeImage;
    private FrameLayout mHome;
    private ConstraintLayout mContainer;

    private AppBarConfiguration mAppBarConfiguration;
    private FrameLayout mMyContainer;
    DrawerLayout drawer;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);
        initView();

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navigationView.setNavigationItemSelectedListener(selectedListener);

        User user = Paper.book().read("data");

    }

    NavigationView.OnNavigationItemSelectedListener selectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.profile:
                    FragmentsUtil.replaceFragment(MainOneActivity.this, R.id.my_container, new ProfileFragment(),true);
                    break;
                case R.id.savedAddress:
                    Intent intent = new Intent(MainOneActivity.this, SavedAddress.class);
                    startActivity(intent);
                    break;
                case R.id.notification:
                    FragmentsUtil.replaceFragment(MainOneActivity.this, R.id.my_container, new NotificationFragment(),true);
                    break;
                case R.id.notes:
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isnote",true);
                    NoteFragment fragment = new NoteFragment();
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment(MainOneActivity.this, R.id.my_container, fragment,true);
                    break;
                case R.id.paymentMethod:
                    FragmentsUtil.replaceFragment(MainOneActivity.this, R.id.my_container, new PaymentMethodFragment(),true);
                    break;
                case R.id.about:
                    FragmentsUtil.replaceFragment(MainOneActivity.this, R.id.my_container, new AboutUsFragment(),true);
                    break;
                case R.id.call:
                    FragmentsUtil.replaceFragment(MainOneActivity.this, R.id.my_container, new ContactUsFragment(),true);
                    break;
                case R.id.policy:
                    FragmentsUtil.replaceFragment(MainOneActivity.this, R.id.my_container, new PrivacyFragment(),true);
                    break;
                case R.id.share:
                    FragmentsUtil.replaceFragment(MainOneActivity.this, R.id.my_container, new ShareAppFragment(),true);
                    break;
                case R.id.rate:
//                    FragmentsUtil.replaceFragment(MainDriverActivity.this, R.id.my_container, new ProfileFragment());
                    break;
                case R.id.logout:
                    User user = Paper.book().read("data");
                    Paper.book().destroy();
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("user_"+user.getId());
                    Intent logout = new Intent(MainOneActivity.this, Splash.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    logout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(logout);
                    finish();
                    break;
            }
            mMyContainer.setVisibility(View.VISIBLE);
            onBackPressed();
            return false;
        }
    };


    private void initView() {
        mMore = (ImageView) findViewById(R.id.more);
        mMore.setOnClickListener(this);
        mUncheckedLikeImage = (ImageView) findViewById(R.id.unchecked_like_image);
        mCheckedLikeImage = (ConstraintLayout) findViewById(R.id.checked_like_image);
        mLike = (FrameLayout) findViewById(R.id.like);
        mLike.setOnClickListener(this);
        mUncheckedOrderImage = (ImageView) findViewById(R.id.unchecked_order_image);
        mCheckedOrderImage = (ConstraintLayout) findViewById(R.id.checked_order_image);
        mOrders = (FrameLayout) findViewById(R.id.orders);
        mOrders.setOnClickListener(this);
        mUncheckedHomeImage = (ImageView) findViewById(R.id.unchecked_home_image);
        mCheckedHomeImage = (ConstraintLayout) findViewById(R.id.checked_home_image);
        mHome = (FrameLayout) findViewById(R.id.home);
        mHome.setOnClickListener(this);
        mContainer = (ConstraintLayout) findViewById(R.id.container);
        mMyContainer = (FrameLayout) findViewById(R.id.my_container);
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
            case R.id.like:
                mCheckedLikeImage.setVisibility(View.VISIBLE);
                mUncheckedLikeImage.setVisibility(View.GONE);
                mCheckedHomeImage.setVisibility(View.GONE);
                mUncheckedHomeImage.setVisibility(View.VISIBLE);
                mCheckedOrderImage.setVisibility(View.GONE);
                mUncheckedOrderImage.setVisibility(View.VISIBLE);
                mMyContainer.setVisibility(View.VISIBLE);

                Bundle bundle = new Bundle();
                bundle.putBoolean("isnote",false);
                ListMainFragment fragment = new ListMainFragment();
                fragment.setArguments(bundle);

                FragmentsUtil.replaceFragment(this, R.id.my_container, fragment);
//                navController.navigate(R.id.action_homeFragment_to_listMainFragment);
                break;
            case R.id.orders:
                mCheckedLikeImage.setVisibility(View.GONE);
                mUncheckedLikeImage.setVisibility(View.VISIBLE);
                mCheckedHomeImage.setVisibility(View.GONE);
                mUncheckedHomeImage.setVisibility(View.VISIBLE);
                mCheckedOrderImage.setVisibility(View.VISIBLE);
                mUncheckedOrderImage.setVisibility(View.GONE);
                mMyContainer.setVisibility(View.VISIBLE);
                FragmentsUtil.replaceFragment(this, R.id.my_container, new OrderTrackingFragment());
//                navController.navigate(R.id.action_homeFragment_to_orderTrackingFragment);
                break;
            case R.id.home:
                mCheckedLikeImage.setVisibility(View.GONE);
                mUncheckedLikeImage.setVisibility(View.VISIBLE);
                mCheckedHomeImage.setVisibility(View.VISIBLE);
                mUncheckedHomeImage.setVisibility(View.GONE);
                mCheckedOrderImage.setVisibility(View.GONE);
                mUncheckedOrderImage.setVisibility(View.VISIBLE);

                NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
                navController.navigate(R.id.homeFragment3);
                mMyContainer.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        List<Fragment> fragments = navHostFragment.getChildFragmentManager().getFragments();

        for (Fragment fragment:fragments){
            if (fragment instanceof OrderDeiailsFragment){
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