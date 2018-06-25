package com.vandit.customnavdrawer;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements drawerAdapter.OnItemSelectedListener{

    private static final int POS_DASHBOARD = 0;
    private static final int POS_ACCOUNT = 1;
    private static final int POS_MESSAGES = 2;
    private static final int POS_CART = 3;
    private static final int POS_LOGOUT = 4;

    private String[] screenTitles;
    private Drawable[] screenIcons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SlidingRootNav slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        drawerAdapter adapter = new drawerAdapter(Arrays.asList(
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_MESSAGES),
                createItemFor(POS_CART),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
        adapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);
    }
    private String[] loadScreenTitles() {
        return new String[]{"Dashboard","Account","Messages","Cart","Logout"};
    }
    private Drawable[] loadScreenIcons(){
        return new Drawable[]{getDrawable(R.mipmap.ic_dashboard),getDrawable(R.mipmap.ic_account),getDrawable(R.mipmap.ic_message),getDrawable(R.mipmap.ic_cart),getDrawable(R.mipmap.ic_logout)};
    }


    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withSelectedIconTint(R.color.colorPrimaryDark)
                .withIconTint(R.color.colorPrimary)
                .withSelectedTextTint(R.color.colorPrimaryDark)
                .withTextTint(R.color.colorPrimary);
    }


    @Override
    public void onItemSelected(int position) {

    }

}
