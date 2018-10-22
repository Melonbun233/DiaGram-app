package com.example.ewd.diagram.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ewd.diagram.R;
import com.example.ewd.diagram.utils.CustomViewPager;
import com.example.ewd.diagram.utils.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @BindView(R.id.viewpager)
    CustomViewPager viewPager;

    ViewPagerAdapter viewPagerAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    viewPager.setCurrentItem(0);

                    return true;
                case R.id.navigation_timeline:

                    viewPager.setCurrentItem(1);

                    return true;
                case R.id.navigation_profile:

                    viewPager.setCurrentItem(2);

                    return true;
                case R.id.navigation_post:

                    viewPager.setCurrentItem(3);


                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        ButterKnife.bind(this);
        Intent i = getIntent();

        setupViewPagerAdapter();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    /**
     * Method that sets up ViewPagerAdapter
     */
    private void setupViewPagerAdapter() {
        viewPagerAdapter = new ViewPagerAdapter(NavigationActivity.this.getSupportFragmentManager());

        //Creating instances of all fragments
        FeedFragment feedFragment = FeedFragment.newInstance(47, 47);
        TimelineFragment timelineFragment = TimelineFragment.newInstance(47, 47);
        UserFragment userFragment = UserFragment.newInstance(47, 47);
        AddPostFragment addPostFragment = AddPostFragment.newInstance(47, 47);

        //Add all fragments to viewpager adapter
        viewPagerAdapter.addFragment(feedFragment, "Feed");
        viewPagerAdapter.addFragment(timelineFragment, "Timeline");
        viewPagerAdapter.addFragment(userFragment, "User");
        viewPagerAdapter.addFragment(addPostFragment, "Add post");

        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(viewPagerAdapter);

    }


}
