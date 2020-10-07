package com.raushan.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    TextView name;
    Button logout,newQuote;
    String UserId;

    QuoteAdaptar quoteAdaptar;
    DatabaseReference likeRefernce;

    boolean likeChecker =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView quolist = findViewById(R.id.quotesList);
        quolist.setLayoutManager(new LinearLayoutManager(this));


        // fetch data from database
        FirebaseRecyclerOptions<QuoteHelper> options =
                new FirebaseRecyclerOptions.Builder<QuoteHelper>().setQuery(FirebaseDatabase.getInstance().getReference().child("quotes"),QuoteHelper.class).build();


        likeRefernce = FirebaseDatabase.getInstance().getReference("likes");

        //  name= findViewById(R.id.name);
      //  mail = findViewById(R.id.email);
        logout =findViewById(R.id.logout);
        newQuote =findViewById(R.id.newQuote);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),LoginQuotify.class);
                startActivity(intent);
            }
        });


        quoteAdaptar =new QuoteAdaptar(options,MainActivity.this);
        quolist.setAdapter(quoteAdaptar);




        newQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NewQutes.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected  void onStart() {

        super.onStart();
        quoteAdaptar.startListening();
    }

    @Override
    protected  void onStop() {

        super.onStop();
        quoteAdaptar.stopListening();
    }

    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        mHandler.postDelayed(mRunnable, 2000);
    }
}
