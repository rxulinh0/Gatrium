package com.gatrium.gatrium;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class PetProfileCarouselAdapter extends RecyclerView.Adapter<PetProfileCarouselAdapter.ViewHolder>{
    ArrayList<Pet_Data> pet_profiles; // ArrayList of Pet Profiles -->> All pets that appear in Material3 Carousel
    int pet_id_selected;
    basic_user_data user;
    Context context;
    OnItemClickListener onItemClickListener;
    OnItemLongClickListener onItemLongClickListener;

    public PetProfileCarouselAdapter(ArrayList<Pet_Data> pet_profiles, Context context) {
        this.pet_profiles = pet_profiles;
        this.context = context;
    }

    public PetProfileCarouselAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_carousel_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull PetProfileCarouselAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.pet_name.setText(pet_profiles.get(position).getPet_name());
        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(position));
        pet_id_selected = basic_user_data.getLast_pet_id_selected_carousel();
        if(pet_profiles.get(position).getSpecie()&&!pet_profiles.get(position).isAddButton()){ // Selected pet profile is a cat (Pet_Data.specie = true)
            holder.icon.setImageResource(R.drawable.ic_cat_walk);
            /*holder.pet_profile_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.pet_profile_card.setCardBackgroundColor(homeFragment.getResources().getColor(R.color.main_purple));
                    pet_id_selected = position;
                    basic_user_data.getInstance().setLast_pet_id_selected_carousel(pet_id_selected); // Saves in basic_user_data the change in position of the Profile Carousel
                }
            });*/
        } else if(!pet_profiles.get(position).getSpecie()&&!pet_profiles.get(position).isAddButton()){ // Selected pet profile is a dog (Pet_Data.specie = false)
            holder.icon.setImageResource(R.drawable.ic_baseline_pets_24);
            /*holder.pet_profile_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.pet_profile_card.setCardBackgroundColor(homeFragment.getResources().getColor(R.color.main_purple));
                    pet_id_selected = position;
                    basic_user_data.getInstance().setLast_pet_id_selected_carousel(pet_id_selected);
                }
            });*/
        } if(pet_profiles.get(position).isAddButton()){
            //holder.background.setBackgroundColor(homeFragment.getResources().getColor(R.color.main_yellow));
            holder.background.setBackgroundColor(context.getResources().getColor(R.color.main_yellow));
            holder.icon.setImageResource(R.drawable.ic_baseline_add_24);
            //holder.pet_profile_card.setCardBackgroundColor(R.color.main_purple);
            /*holder.pet_profile_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(homeFragment,getting_started_one_pet.class);
                    intent.putExtra("fromHomeFragmentCarousel",true);
                    homeFragment.startActivity(intent);
                }
            });*/
        }
        if(position==pet_id_selected){
            holder.background.setBackgroundColor(context.getResources().getColor(R.color.main_purple));
        } else if (!pet_profiles.get(position).isAddButton()){
            holder.background.setBackgroundColor(context.getResources().getColor(R.color.main_orange));
            //holder.pet_profile_card.setCardBackgroundColor(homeFragment.getResources().getColor(R.color.main_orange));
        }
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public int getItemCount() {
        return pet_profiles.size();
    }

    public void setPet_profiles(ArrayList<Pet_Data> pet_profiles){
        this.pet_profiles = pet_profiles;
        notifyDataSetChanged();
    }

    public void setPet_id_selected(int pet_id_selected){
        this.pet_id_selected = pet_id_selected;
        notifyDataSetChanged();
    }
    public void setUser(basic_user_data user){
        this.user = user;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onClick(int position);
    }
    public interface OnItemLongClickListener{
        void onLongClick();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView pet_name;
        private RelativeLayout background;
        private ImageView icon;
        //private MaterialCardView pet_profile_card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            background = itemView.findViewById(R.id.list_item_background);
            pet_name = itemView.findViewById(R.id.pet_carousel_pet_profile_name);
            icon = itemView.findViewById(R.id.pet_carousel_item_icon);
            // pet_profile_card = itemView.findViewById(R.id.pet_profile_card);
        }
    }
}
