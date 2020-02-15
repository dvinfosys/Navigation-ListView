package com.dvinfosys.androidcustomnavigationdrawerusingexpandablelistview.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.dvinfosys.androidcustomnavigationdrawerusingexpandablelistview.R;
import com.dvinfosys.androidcustomnavigationdrawerusingexpandablelistview.Utils.Common;
import com.dvinfosys.model.ChildModel;
import com.dvinfosys.model.HeaderModel;
import com.dvinfosys.ui.NavigationListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private NavigationListView listView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = MainActivity.this;

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listView = findViewById(R.id.expandable_navigation);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView.init(this)
                .addHeaderModel(new HeaderModel("Home"))
                .addHeaderModel(new HeaderModel("Cart",  R.drawable.ic_cardbackgroud, true,true, false, Color.WHITE))
                .addHeaderModel(
                        new HeaderModel("Categories", -1,true)
                                .addChildModel(new ChildModel("Men's Fashion"))
                                .addChildModel(new ChildModel("Woman's Fashion"))
                                .addChildModel(new ChildModel("Babies and Family"))
                                .addChildModel(new ChildModel("Health"))
                )
                .addHeaderModel(new HeaderModel("Orders"))
                .addHeaderModel(new HeaderModel("Wishlist"))
                .addHeaderModel(new HeaderModel("Notifications"))
                .build()
                .addOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        listView.setSelected(groupPosition);

                        //drawer.closeDrawer(GravityCompat.START);
                        if (id == 0) {
                            //Home Menu
                            Common.showToast(context, "Home Select");

                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 1) {
                            //Cart Menu
                            Common.showToast(context, "Cart Select");
                            drawer.closeDrawer(GravityCompat.START);
                        } /*else if (id == 2) {
                            //Categories Menu
                            Common.showToast(context, "Categories  Select");
                        }*/ else if (id == 3) {
                            //Orders Menu
                            Common.showToast(context, "Orders");
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 4) {
                            //Wishlist Menu
                            Common.showToast(context, "Wishlist Selected");
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 5) {
                            //Notifications Menu
                            Common.showToast(context, "Notifications");
                            drawer.closeDrawer(GravityCompat.START);
                        }
                        return false;
                    }
                })
                .addOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        listView.setSelected(groupPosition, childPosition);
                        if (id == 0) {
                            Common.showToast(context, "Man's Fashion");
                        } else if (id == 1) {
                            Common.showToast(context, "Woman's Fashion");
                        } else if (id == 2) {
                            Common.showToast(context, "Babies and Family");
                        } else if (id == 3) {
                            Common.showToast(context, "Health");
                        }

                        drawer.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });
        //listView.expandGroup(2);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       /* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
