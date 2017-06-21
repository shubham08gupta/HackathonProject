package com.hackathonproject.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shubhamgupta on 21/06/2017.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.EventsViewHolder> {

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class EventsViewHolder extends RecyclerView.ViewHolder {

        public EventsViewHolder(View itemView) {
            super(itemView);
        }
    }
}
