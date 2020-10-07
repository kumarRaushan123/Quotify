package com.raushan.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class QuoteAdaptar extends FirebaseRecyclerAdapter<QuoteHelper, QuoteAdaptar.QuoteViewHolder>

{

    Context  context;
    //FirebaseRecyclerOptions<QuoteHelper> options;
    public QuoteAdaptar(@NonNull FirebaseRecyclerOptions<QuoteHelper> options, Context context) {
        super(options);
        this.context =context;
       // this.options = options;

    }

    @Override
    protected void onBindViewHolder(@NonNull final QuoteViewHolder holder, int position, @NonNull final QuoteHelper model) {

        boolean click = false;

        holder.quo.setText(model.getQuotes());
        holder.auth.setText(model.getAuthor());
        holder.likeCount.setText(model.getLikeCount());


        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,holder.quo.getText().toString());
                intent.setType("text/plain");
               context.startActivity(Intent.createChooser(intent, "Send To"));
            }
        });




    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_quotes,parent,false);
        return new QuoteViewHolder(view);
    }

    public  class QuoteViewHolder extends RecyclerView.ViewHolder{

        TextView quo, auth,likeCount;
        ImageView share, like;

        public QuoteViewHolder(@NonNull View itemView) {
            super(itemView);

            quo = itemView.findViewById(R.id.txtQuote);
            auth = itemView.findViewById(R.id.txtauthor);
            share = itemView.findViewById(R.id.share);
           like = itemView.findViewById(R.id.like);
           likeCount = itemView.findViewById(R.id.likeCount);








        }
    }



}