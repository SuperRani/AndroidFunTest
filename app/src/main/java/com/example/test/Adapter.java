package com.example.test;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ItemClass> mItems;

    public Adapter(ArrayList<ItemClass> mItems) {
        this.mItems = mItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button deleteButton;
        private TextView textOfList;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            textOfList = itemView.findViewById(R.id.textOfList);

        }
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter.ViewHolder holder, final int position) {
        holder.textOfList.setText(mItems.get(position).getText());
        holder.itemView.setTag((position));


        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#BDBDBD"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#859765"));
        }
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                remove(pos);
                notifyDataSetChanged();

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curName = holder.textOfList.getText().toString();
                Toast.makeText(v.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });


    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }


    //삭제
    private void deleteThisView(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mItems.size());
    }

    public void remove(int position) {
        try {
            mItems.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();

        }
    }


}
