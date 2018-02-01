package com.example.hp.shareblood;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView username,email;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference MdatabaseReference = firebaseDatabase.getReference();
    private DatabaseReference MCdatabaseReference = MdatabaseReference.child("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        username=(TextView)findViewById(R.id.username);
        email=(TextView) findViewById(R.id.email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_page, menu);
        return true;
    }

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

    public void dispalySelectedScreen(int itemId) {
        String useremail=getIntent().getStringExtra("useremail");
        String ad=useremail;
        String[] words=ad.split("@");
        switch (itemId) {
            case R.id.myprofile:
                Intent in=new Intent(getApplicationContext(),MyProfile.class);
                in.putExtra("userid",words[0]);
                Toast.makeText(getApplicationContext(),words[0],Toast.LENGTH_SHORT).show();
                startActivity(in);
                break;
            case R.id.eligibility:
                Intent Eligibile = new Intent(getApplicationContext(),Eligibility.class);
                startActivity(Eligibile);
                break;
            case R.id.signout:
                Intent Signout=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(Signout);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        dispalySelectedScreen(item.getItemId());
        return true;
    }
}
