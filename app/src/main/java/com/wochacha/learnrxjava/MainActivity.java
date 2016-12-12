package com.wochacha.learnrxjava;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wochacha.learnrxjava.module.elementary_1.ElementaryFragment;
import com.wochacha.learnrxjava.module.map_2.MapFragment;
import com.wochacha.learnrxjava.module.token_4.TokenFragment;
import com.wochacha.learnrxjava.module.zip_3.ZipFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(android.R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.viewPager) ViewPager viewPager;
    @Bind(R.id.toolBar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return new ElementaryFragment();
                    case 1:
                        return new MapFragment();
                    case 2:
                        return new ZipFragment();
                    case 3:
                        return new TokenFragment();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return "基本";
                    case 1:
                        return "转换(map)";
                    case 2:
                        return "压合(Zip)";
                    case 3:
                        return "Token(flatMap)";
                    default:
                        return "基本";
                }
            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }
}
