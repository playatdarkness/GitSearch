package com.rahulwadhwa238.gitsearch;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rahulwadhwa238.gitsearch.controller.ProfileDetails;
import com.rahulwadhwa238.gitsearch.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> items;
    private Context context;

    public ItemAdapter(Context applicationContext, List<Item> itemArrayList)    {
        this.context = applicationContext;
        this.items = itemArrayList;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_profile, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder viewHolder, int i)  {
        viewHolder.username.setText(items.get(i).getLogin());
        viewHolder.profile_link.setText(items.get(i).getLinkURL());

        Picasso.get()
                .load(items.get(i).getAvatarURL())
                .placeholder(R.drawable.load)
                .into(viewHolder.dp);
    }

    @Override
    public int getItemCount()   {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView username, profile_link;
        private ImageView dp;

        public ViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.username);
            profile_link = view.findViewById(R.id.profile_link);
            dp = view.findViewById(R.id.dp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if  (pos != RecyclerView.NO_POSITION)    {
                        Intent intent = new Intent(context, ProfileDetails.class);
                        intent.putExtra("login", items.get(pos).getLogin());
                        intent.putExtra("profile_url", items.get(pos).getLinkURL());
                        intent.putExtra("avatar", items.get(pos).getAvatarURL());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
