package com.sankalp.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sankalp.chatapp.Adapters.ChatAdapter;
import com.sankalp.chatapp.Models.MessageModal;
import com.sankalp.chatapp.databinding.ActivityChatDetailedBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailedActivity extends AppCompatActivity {

    ActivityChatDetailedBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding=ActivityChatDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        database=FirebaseDatabase.getInstance("https://chatapp-61c7f-default-rtdb.firebaseio.com/");
        auth=FirebaseAuth.getInstance();


        final String senderId=auth.getUid();
        String recieveId=getIntent().getStringExtra("userId");
        String userName=getIntent().getStringExtra("username");
        String profilepic=getIntent().getStringExtra("profilepic");

        binding.tvUserName.setText(userName);
        Picasso.get().load(profilepic).placeholder(R.drawable.ic_android_black_24dp).into(binding.chatboxprofileimage);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChatDetailedActivity.this, MainActivity.class));
            }
        });

        final ArrayList<MessageModal> messageModals=new ArrayList<>();
        final ChatAdapter chatAdapter=new ChatAdapter(messageModals, this);
        binding.chatRecyclerView.setAdapter(chatAdapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.chatRecyclerView.setLayoutManager(layoutManager);

        final String senderRoom=senderId+recieveId;
        final String recieverRoom=recieveId+senderId;

        database.getReference().child("chats")
                .child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshots) {
                messageModals.clear();
                for (DataSnapshot snapshot:snapshots.getChildren()){
                    MessageModal modal=snapshot.getValue(MessageModal.class);
                    messageModals.add(modal);
                }
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        binding.btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=binding.etSendMsg.getText().toString();
                final MessageModal modal=new MessageModal(senderId, msg);
                modal.setTimeStamp(new Date().getTime());
                binding.etSendMsg.setText("");

                database.getReference().child("chats")
                        .child(senderRoom)
                        .push()
                        .setValue(modal).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        database.getReference().child("chats")
                                .child(recieverRoom).push()
                                .setValue(modal).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });
                    }
                });
            }
        });
    }
}
