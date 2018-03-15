package com.example.adrian.stelaapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

//import org.parceler.Parcels;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.rvConstellation) RecyclerView rvConstellation;
    public static ArrayList<Constellation> constellations;
    ConstellationAdapter constellationAdapter;
    ConstellationAdapter.ConstellationAdapterListener constellationAdapterListener;
    private int position;
//    RecyclerView rvConstellation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
//        rvConstellation = (RecyclerView) findViewById(R.id.rvConstellation);

        // init the arraylist (data source)
        constellations = new ArrayList<>();

        // construct the adapter from this data source
        constellationAdapter = new ConstellationAdapter(constellations);

        // RecyclerView setup (layout manager, use adapter)
        rvConstellation.setLayoutManager(new LinearLayoutManager(this));

        // set the adapter
        rvConstellation.setAdapter(constellationAdapter);




//        //init the array list (data)
//        constellations = new ArrayList<Constellation>(){
//            public boolean add(Constellation constellation){
//                int index = Collections.binarySearch(this,constellation);
//                if(index<0) index = ~index;
//                super.add(index,constellation);
//                return true;
//            }
//        };
//        // construct the adapter listener
//        constellationAdapterListener = new ConstellationAdapter.ConstellationAdapterListener(){
//            @Override
//            public void onItemSelected(View view, int position, boolean b) {
////                Intent i = new Intent(MainActivity.this, ConstellationActivity.class);
////                i.putExtra("constellation", Parcels.wrap(constellations.get(position)));
////                startActivity(i);
//            }
//        };



        setNavigationView();

        // MAKE CONSTELLATION DATA ************************************************* //

        Constellation cons0 = new Constellation();
        cons0.setName("Orion the Hunter");
        cons0.setDescription("Its name refers to Orion, a hunter in Greek mythology");
        // above info from https://prezi.com/m81twsi44i7-/popular-constellations/
        Constellation cons1 = new Constellation();
        cons1.setName("Leo the Lion");
        cons1.setDescription("Its name is Latin for lion");
        Constellation cons2 = new Constellation();
        cons2.setName("Taurus the Bull");
        cons2.setDescription("The name of the constellation is from the word taurus, which is the" +
                "Latin word for a bull");
        Constellation cons3 = new Constellation();
        cons3.setName("Scorpius the Scorpio");
        cons3.setDescription("It is a large constellation located in the southern hemisphere" +
                "near the center of the Milky Way");

        constellations.add(cons0);
        constellationAdapter.notifyItemInserted(0);
        constellations.add(cons1);
        constellationAdapter.notifyItemInserted(0);
        constellations.add(cons2);
        constellationAdapter.notifyItemInserted(0);



//        //        constellationAdapter = new ConstellationAdapter(constellations,constellationAdapterListener);
////        rvConstellation.setAdapter(constellationAdapter);
//
//        constellationAdapter = new ConstellationAdapter(constellations,constellationAdapterListener);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        rvConstellation.setLayoutManager(linearLayoutManager);
//        rvConstellation.setAdapter(constellationAdapter);


    }

    public void setNavigationView() {
        // set up the navigation view

        // set the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
     //   getSupportActionBar().setDisplayShowTitleEnabled(false);

        // set the drawer layout and button to access it
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // set the navigation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        navigationView.setItemTextColor(null);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.nav_configure:
                startActivity(new Intent(getApplicationContext(), ConfigureActivity.class));
                // set the new animation
                overridePendingTransition(R.anim.fade_in_fast, R.anim.fade_out_fast);
                // close the navigation view
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_move:
                // Go to MoveActivity
                startActivity(new Intent(getApplicationContext(), MoveActivity.class));
                // set the transition
                overridePendingTransition(R.anim.fade_in_fast, R.anim.fade_out_fast);
            default:
                // close the navigation view
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }
    }
}
