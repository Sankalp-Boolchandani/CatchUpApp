package com.sankalp.chatapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sankalp.chatapp.Adapters.UsersAdapter;
import com.sankalp.chatapp.Models.Users;
import com.sankalp.chatapp.R;
import com.sankalp.chatapp.databinding.FragmentChatsFragmentsBinding;

import java.util.ArrayList;


public class ChatsFragments extends Fragment {

    FragmentChatsFragmentsBinding binding;
    ArrayList<Users> list=new ArrayList<>();
    FirebaseDatabase database;

    public ChatsFragments() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentChatsFragmentsBinding.inflate(inflater, container, false);


        final UsersAdapter adapter=new UsersAdapter(list ,getContext());
        binding.chatRecyclerView.setAdapter(adapter);
        database=FirebaseDatabase.getInstance("https://chatapp-61c7f-default-rtdb.firebaseio.com/");
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        binding.chatRecyclerView.setLayoutManager(layoutManager);

        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()){
                    Users users=childSnapshot.getValue(Users.class);
                    users.setUserId(childSnapshot.getKey());
                    list.add(users);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return binding.getRoot();
    }
}
