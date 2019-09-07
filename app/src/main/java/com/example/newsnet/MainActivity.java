package com.example.newsnet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

 View loading;
 TextView emp;
 Button trya;
 RecyclerView recyclerView;
 DrawerLayout drawer;
 Toolbar toolbar;
 NavigationView navigationView;
    String url="";
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    int flag=0;

    private String category = "general";
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayoutManager= new LinearLayoutManager(this);
        loading=(ProgressBar)findViewById(R.id.progload);
         emp=findViewById(R.id.empty_view);
         trya = findViewById(R.id.try_again);
         recyclerView=findViewById(R.id.recycler_view);
         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
         navigationView = (NavigationView) findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.general);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);


   sendAndRequestResponse("general");
  }


    private void sendAndRequestResponse(final String category) {

       url="https://newsapi.org/v2/top-headlines?country=in&apiKey=72c214ccffc5464bbb03113859b29ab3&category="+category;//RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               // Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                try {
                    ArrayList<News> news=Query.extract(response);
                    updateUi(news);
                    Log.i("onResponse: ",news.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                sendAndRequestResponse(category);
            }
        });

        mRequestQueue.add(mStringRequest);
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
        loading.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);

        if (id == R.id.general) {
            category="general";
            toolbar.setTitle(R.string.general);
//            getLoaderManager().restartLoader(0,null,MainActivity.this);
            sendAndRequestResponse(category);
        }
        else if (id == R.id.business) {

            category="business";
            toolbar.setTitle(R.string.business);
           // getLoaderManager().restartLoader(0,null,MainActivity.this);
            sendAndRequestResponse(category);
        } else if (id == R.id.science) {

            category="science";
            toolbar.setTitle(R.string.science);
            sendAndRequestResponse(category);// getLoaderManager().restartLoader(0,null,MainActivity.this);

        }
        else if (id == R.id.entertainment) {

            category="entertainment";
            toolbar.setTitle(R.string.entertainment);
            //getLoaderManager().restartLoader(0,null,MainActivity.this);
            sendAndRequestResponse(category);
        }
        else if (id == R.id.health) {
            category="health";
            toolbar.setTitle(R.string.health);
            sendAndRequestResponse(category);// getLoaderManager().restartLoader(0,null,MainActivity.this);


        } else if (id == R.id.technology) {

            category="technology";
            toolbar.setTitle(R.string.technology);
            sendAndRequestResponse(category);//getLoaderManager().restartLoader(0,null,MainActivity.this);

        } else if (id == R.id.sports) {
            category="sports";
            toolbar.setTitle(R.string.sports);//getLoaderManager().restartLoader(0,null,MainActivity.this);
            sendAndRequestResponse(category);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


   /* public boolean checkNetwork(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }
*/


    private void updateUi(ArrayList<News> data) {
        NewsAdapter adapter = new NewsAdapter(this, data);
        loading.setVisibility(View.GONE);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
    }


}
