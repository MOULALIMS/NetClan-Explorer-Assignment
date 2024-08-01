package com.example.demoapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class PersonalFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private List<Item> userList;
    private List<Item> filteredList;
    private SearchView searchView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        searchView = view.findViewById(R.id.search_view);

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
        items.add(new Item("Movin Reddy", "Bangalore"));
        items.add(new Item("Indraneel", "Bangalore"));
        items.add(new Item("Chetan Reddy", "Nandyala"));
        items.add(new Item("Vishwesh", "Hyderabad"));
        items.add(new Item("Bhanu Teja", "Anantapur"));
        return items;
    }
}
