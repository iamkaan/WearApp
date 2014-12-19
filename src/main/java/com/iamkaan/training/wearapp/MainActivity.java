package com.iamkaan.training.wearapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private WearableListView listView;
    private ArrayList<String> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                listView = (WearableListView) findViewById(R.id.listView);

                countryList = new ArrayList<>();
                countryList.add("Turkey");
                countryList.add("Russia");
                countryList.add("Germany");

                listView.setAdapter(new MyAdapter(MainActivity.this));
            }
        });
    }

    private class MyAdapter extends WearableListView.Adapter {
        private final LayoutInflater mInflater;

        private MyAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new WearableListView.ViewHolder(
                    mInflater.inflate(R.layout.item_country, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
            TextView title = (TextView) holder.itemView.findViewById(R.id.title);
            CircledImageView smallImage = (CircledImageView) holder.itemView.findViewById(R.id.smallImage);
            title.setText(countryList.get(position));
            smallImage.setImageResource(R.drawable.city);
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return countryList.size();
        }
    }
}