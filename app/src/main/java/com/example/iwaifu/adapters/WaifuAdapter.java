package com.example.iwaifu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwaifu.R;
import com.example.iwaifu.listeners.OnItemClickListener;
import com.example.iwaifu.models.WaifuModel;

import java.util.List;

public class WaifuAdapter extends RecyclerView.Adapter<WaifuAdapter.WaifuViewHolder> {
    private List<WaifuModel> localDataSet;
    public static OnItemClickListener itemClickListener;

    public WaifuAdapter(List<WaifuModel> dataSet, OnItemClickListener listener) {
        itemClickListener = listener;
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public WaifuViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_waifu, viewGroup, false);
        return new WaifuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WaifuViewHolder viewHolder, final int position) {
        viewHolder.bind(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class WaifuViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView portrait;
        private final ConstraintLayout layout;

        public WaifuViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.waifu_name);
            portrait =  view.findViewById(R.id.waifu_portrait);
            layout =  view.findViewById(R.id.container);
        }

        public void bind(WaifuModel item){
            name.setText(item.getName());
            portrait.setImageBitmap(item.getPortraitAsBitmap());
            layout.setOnClickListener(v -> itemClickListener.onItemClick(item));
        }
    }
}
