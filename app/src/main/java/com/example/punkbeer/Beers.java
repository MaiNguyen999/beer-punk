package com.example.punkbeer;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.punkbeer.adapters.BeerAdapter;
import com.example.punkbeer.models.BeerItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Beers extends AppCompatActivity {
    String beer, food, ibu, abv;
    private RecyclerView mRecyclerView;
    private BeerAdapter mBeerAdapter;
    private ArrayList<BeerItem> mBeerList;
    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);

        beer = getIntent().getStringExtra("beer");
        food = getIntent().getStringExtra("food");
        ibu = getIntent().getStringExtra("ibu");
        abv = getIntent().getStringExtra("abv");
        Toast.makeText(this, beer, Toast.LENGTH_LONG).show();

        mRecyclerView = findViewById(R.id.recycler_view);
        //mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //new LinearLayoutManager(this)
        mBeerList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }
    private void parseJSON(){
        //String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
        String url = "https://api.punkapi.com/v2/beers";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i < response.length(); i++){
                        JSONObject beer = response.getJSONObject(i);

                        String name = beer.getString("name");
                        String imageUrl = beer.getString("image_url");
                        String description = beer.getString("description");

                        mBeerList.add(new BeerItem(name, imageUrl, description));
                    }
                    mBeerAdapter = new BeerAdapter(Beers.this, mBeerList);
                    mRecyclerView.setAdapter(mBeerAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}