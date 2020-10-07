package com.raushan.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewQutes extends AppCompatActivity {

    EditText quotes, author;
    Button add;
    String currentUser;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference likeRefernce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_qutes);


        quotes = findViewById(R.id.quotes);
        author  = findViewById(R.id.author);
        add = findViewById(R.id.addNew);



        currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                String fireQuotes = quotes.getEditableText().toString();
                String fireAuthor = author.getEditableText().toString();
                String fireUser = currentUser;


                if(fireQuotes.trim().isEmpty()){
                    Toast.makeText(NewQutes.this,"quotes should not be empty",Toast.LENGTH_LONG).show();

                }else {

                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("quotes");


                    QuoteHelper helper = new QuoteHelper();

                    helper.setQuotes(fireQuotes);
                    helper.setAuthor(fireAuthor);
                    helper.setUser(fireUser);
                    helper.setLikeCount("0");


                    String key = reference.child("quotes").push().getKey();

                    reference.child(key).setValue(helper);



                    // add for the like
                    likeRefernce = rootNode.getReference("likes");

                    likeData helperLike = new likeData();

                    helperLike.setUserID(fireUser);
                    helperLike.setLike("0");
                    helperLike.setQuotesID(key);
                    helperLike.setCountLike("0");


                    likeRefernce.push().setValue(helperLike);


                    Toast.makeText(NewQutes.this, "Added", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(NewQutes.this, MainActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
}
