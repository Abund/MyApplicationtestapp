package com.example.myapplicationtestapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplicationtestapp.model.User;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.facebook.login.LoginManager;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView bloodp,bloods,cal,goal,clientName;
    Button medi;
    private ImageView imageViewCalories,imageViewBloodPressure,imageViewBloodSugar;
    FirebaseAuth firebaseAuth;
    FirebaseUser userf;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=database.getReference("users");
        myRef = FirebaseDatabase.getInstance().getReference().child("users");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        bloodp = (TextView) findViewById(R.id.bloodPressureT);
        bloods = (TextView) findViewById(R.id.bloodSugarT);
        imageViewCalories = (ImageView) findViewById(R.id.imageViewCalories);
        imageViewBloodPressure = (ImageView) findViewById(R.id.imageViewBloodPressure);
        imageViewBloodSugar = (ImageView) findViewById(R.id.imageViewBloodSugar);
        cal = (TextView) findViewById(R.id.calories);
        goal = (TextView) findViewById(R.id.goals);
        medi=(Button) findViewById(R.id.medibutton);
        clientName = (TextView) findViewById(R.id.clientName);
        firebaseAuth =FirebaseAuth.getInstance();
        userf=firebaseAuth.getCurrentUser();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> keys = new ArrayList<>();
                User user= new User();
                user=dataSnapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue(User.class);
                clientName.setText(user.getFirstName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HomeScreen.this,"Oppss... something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

        bloodp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getSupportFragmentManager().beginTransaction().add(R.id.content_frame,new BloodpressureActivity()).commit();
            }
        });

        bloods.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getSupportFragmentManager().beginTransaction().add(R.id.content_frame,new BloodSugarActivity()).commit();
            }
        });


        imageViewCalories.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getSupportFragmentManager().beginTransaction().add(R.id.content_frame,new CaloriesActivity()).commit();
            }
        });
        imageViewBloodPressure.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getSupportFragmentManager().beginTransaction().add(R.id.content_frame,new BloodpressureActivity()).commit();
            }
        });
        imageViewBloodSugar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getSupportFragmentManager().beginTransaction().add(R.id.content_frame,new BloodSugarActivity()).commit();
            }
        });

        cal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getSupportFragmentManager().beginTransaction().add(R.id.content_frame,new CaloriesActivity()).commit();
            }
        });

        goal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getSupportFragmentManager().beginTransaction().add(R.id.content_frame,new GoalActivity()).commit();
            }
        });

        medi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getSupportFragmentManager().beginTransaction().add(R.id.content_frame,new MedicationDashBoard()).commit();
            }
        });

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
        getMenuInflater().inflate(R.menu.homescreen, menu);
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
        //FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent at = new Intent(HomeScreen.this, HomeScreen.class);
            startActivity(at);
        } else if (id == R.id.nav_bpressure) {
            Fragment newFragment =  new BloodpressureActivity();
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            ft.add(R.id.content_frame, newFragment).commit();
            replaceFragment(newFragment);

        } else if (id == R.id.nav_bsugar) {
            Fragment newFragment =  new BloodSugarActivity();
            replaceFragment(newFragment);

        } else if (id == R.id.nav_calorie) {
            Fragment newFragment =  new CaloriesActivity();
            replaceFragment(newFragment);

        } else if (id == R.id.nav_mreminder) {
            Fragment newFragment =  new MedicationDashBoard();
            replaceFragment(newFragment);

        } else if (id == R.id.nav_goal) {
            Fragment newFragment =  new GoalActivity();
            replaceFragment(newFragment);

        }else if (id == R.id.sign_out) {

            firebaseAuth = FirebaseAuth.getInstance();
            LoginManager.getInstance().logOut();
            firebaseAuth.signOut();
            Intent at = new Intent(HomeScreen.this, MainActivity.class);
            startActivity(at);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment destFragment)
    {
        // First get FragmentManager object.
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        // Begin Fragment transaction.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the layout holder with the required Fragment object.
        fragmentTransaction.replace(R.id.content_frame, destFragment);

        // Commit the Fragment replace action.
        fragmentTransaction.commit();
    }
}
