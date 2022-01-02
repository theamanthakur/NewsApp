package com.ttl.dailynews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScienceFragment extends Fragment {

    String api = "8a654f123c4f410486afced266e56d79";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country = "us";
    private RecyclerView recyclerViewScience;
    private String category = "science";


    public ScienceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_science, container, false);
         recyclerViewScience = view.findViewById(R.id.recyclerScience);
        modelClassArrayList = new ArrayList<>();
         recyclerViewScience.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelClassArrayList);
         recyclerViewScience.setAdapter(adapter);

        findNews();
        return view;
    }

    private void findNews() {

        ApiUtilities.getApiInterface().getCategoryNews(country, category,100, api)
                .enqueue(new Callback<mainNews>() {
                    @Override
                    public void onResponse(Call<mainNews> call, Response<mainNews> response) {

                        if (response.isSuccessful()){
                            modelClassArrayList.addAll(response.body().getArticles());
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<mainNews> call, Throwable t) {

                    }
                });
    }
}