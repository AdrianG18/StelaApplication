package com.example.adrian.stelaapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.rvConstellation) RecyclerView rvConstellation;
    public static List<Constellation> constellations;
    ConstellationAdapter constellationAdapter;
    ConstellationAdapter.ConstellationAdapterListener constellationAdapterListener;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //init the array list (data)
        constellations = new ArrayList<Constellation>(){
            public boolean add(Constellation constellation){
                int index = Collections.binarySearch(this,constellation);
                if(index<0) index = ~index;
                super.add(index,constellation);
                return true;
            }
        };
        // construct the adapter listener
        constellationAdapterListener = new ConstellationAdapter.ConstellationAdapterListener(){
            @Override
            public void onItemSelected(View view, int position, boolean b) {
//                Intent i = new Intent(MainActivity.this, ConstellationActivity.class);
//                i.putExtra("constellation", Parcels.wrap(constellations.get(position)));
//                startActivity(i);
            }
        };

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
        constellations.add(cons1);
        constellations.add(cons2);
        constellationAdapter = new ConstellationAdapter(constellations,constellationAdapterListener);
        rvConstellation.setAdapter(constellationAdapter);

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
        return false;
    }
}
