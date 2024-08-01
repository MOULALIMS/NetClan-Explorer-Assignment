package com.example.demoapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class BusinessFragment extends Fragment {
    private UserAdapter adapter;
    private List<Item> userList;
    private List<Item> filteredList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        SearchView searchView = view.findViewById(R.id.search_view);

        userList = fetchUsers();
        filteredList = new ArrayList<>(userList);

        adapter = new UserAdapter(filteredList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(userList);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (Item user : userList) {
                if (user.getName().toLowerCase().contains(lowerCaseQuery) ||
                        user.getLocation().toLowerCase().contains(lowerCaseQuery)) {
                    filteredList.add(user);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private List<Item> fetchUsers() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Moulali", "Prakasam"));
        items.add(new Item("Avinash", "Ananthapur"));
        items.add(new Item("Chandu", "Vizag"));
        items.add(new Item("Michael", "Vijayawada"));
        items.add(new Item("Hemanth", "Hyderabad"));
        return items;
    }
}
