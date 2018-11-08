package com.wpf.rxjavaretrofitdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.wpf.rxjavaretrofitdemo.R;
import com.wpf.rxjavaretrofitdemo.base.BaseActivity;
import com.wpf.rxjavaretrofitdemo.fragment.FirstFragment;
import com.wpf.rxjavaretrofitdemo.fragment.SecondFragment;
import com.wpf.rxjavaretrofitdemo.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;
    @BindView(R.id.main_navigation)
    BottomNavigationView mainNavigation;
    private List<Fragment> fragmentList;
    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initDrawer(savedInstanceState);
        initNavigation();
        initContent();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_main;
    }

    private void initDrawer(Bundle savedInstanceState) {
        IProfile profile = new ProfileDrawerItem()
                .withName("000000")
                .withEmail("0000000@gmail.com")
                .withIcon(R.drawable.profile2)
                .withIdentifier(100);
        IProfile profile2 = new ProfileDrawerItem()
                .withName("111111")
                .withEmail("111111111@gmail.com")
                .withIcon(R.drawable.profile3)
                .withIdentifier(101);
        IProfile profile3 = new ProfileDrawerItem()
                .withName("222222")
                .withEmail("2222222@gmail.com")
                .withIcon(R.drawable.profile4)
                .withIdentifier(102);
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(profile,
                        profile2,
                        profile3,
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBar().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(100000),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(100001))
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        switch ((int) profile.getIdentifier()) {
                            case 100:
                                Toast.makeText(MainActivity.this, "profile is clicked", Toast.LENGTH_SHORT).show();
                                break;
                            case 101:
                                Toast.makeText(MainActivity.this, "profile2 is clicked", Toast.LENGTH_SHORT).show();
                                break;
                            case 102:
                                Toast.makeText(MainActivity.this, "profile3 is clicked", Toast.LENGTH_SHORT).show();
                                break;
                            case 100000:
                                Toast.makeText(MainActivity.this, "Add Account is clicked", Toast.LENGTH_SHORT).show();
                                break;
                            case 100001:
                                Toast.makeText(MainActivity.this, "Manage Account is clicked", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withSliderBackgroundColorRes(R.color.accent) //除去Header其余的背景
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true) //汉堡 箭头 图标动画
                .addDrawerItems(
                        new SectionDrawerItem().withName("Menu").withDivider(false),
                        initDrawerItems("iOS", R.drawable.drawer_icon_ios, 1),
                        initDrawerItems("福利", R.drawable.drawer_icon_girl, 2),
                        initDrawerItems("前端", R.drawable.drawer_icon_client, 3),
                        initDrawerItems("瞎推荐", R.drawable.drawer_icon_recommend, 4),
                        initDrawerItems("App", R.drawable.drawer_icon_app, 5),
                        initDrawerItems("拓展资源", R.drawable.drawer_icon_resource, 6),
                        initDrawerItems("休息视频", R.drawable.drawer_icon_video, 7)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(MainActivity.this, GankOtherActivity.class);
                        Bundle bundle = new Bundle();
                        switch (position) {
                            case 2:
                                bundle.putString("gank_type", "iOS");
                                break;
                            case 3:
                                bundle.putString("gank_type", "福利");
                                break;
                            case 4:
                                bundle.putString("gank_type", "前端");
                                break;
                            case 5:
                                bundle.putString("gank_type", "瞎推荐");
                                break;
                            case 6:
                                bundle.putString("gank_type", "App");
                                break;
                            case 7:
                                bundle.putString("gank_type", "拓展资源");
                                break;
                            case 8:
                                bundle.putString("gank_type", "休息视频");
                                break;
                            default:
                                bundle.putString("gank_type", "all");
                                break;
                        }
                        intent.putExtra("gank_type", bundle);
                        ActivityCompat.startActivity(MainActivity.this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                        drawer.deselect();
                        drawer.closeDrawer();
                        return true;
                    }
                })
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {

                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {

                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        int offset = (int) (drawerView.getWidth() * slideOffset);
                        mainContent.setTranslationX(offset);
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
        drawer.getDrawerLayout().setFitsSystemWindows(false);
        drawer.deselect();
    }

    private void initNavigation() {
        mainNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mainViewpager.setCurrentItem(0, false);
                        return true;
                    case R.id.navigation_read:
                        mainViewpager.setCurrentItem(1, false);
                        return true;
                    case R.id.navigation_me:
                        mainViewpager.setCurrentItem(2, false);
                        return true;
                }
                return false;
            }
        });
    }

    private void initContent() {
        initFragment();
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), fragmentList);
        mainViewpager.setAdapter(mainAdapter);
        mainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mainNavigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        mainNavigation.setSelectedItemId(R.id.navigation_read);
                        break;
                    case 2:
                        mainNavigation.setSelectedItemId(R.id.navigation_me);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new FirstFragment());
        fragmentList.add(new SecondFragment());
        fragmentList.add(new ThirdFragment());
    }

    private IDrawerItem initDrawerItems(String name, int iconId, int identifier) {
        return new PrimaryDrawerItem()
                .withName(name)
//                .withDescription("This is a PrimaryDrawerItem")
                .withIcon(iconId)
                .withSetSelected(false)
                .withIdentifier(identifier);
    }
}
