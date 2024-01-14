package com.gatrium.gatrium;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PetProfileCarouselAdapter extends RecyclerView.Adapter<PetProfileCarouselAdapter.ViewHolder>{
    private ArrayList<Pet_Data> pet_profiles;
    private int pet_id_selected;
    private basic_user_data user;
    private FragmentActivity homeFragment;
    public PetProfileCarouselAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_carousel_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull PetProfileCarouselAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.pet_name.setText(pet_profiles.get(position).getPet_name());
        if(pet_profiles.get(position).getSpecie()&&!pet_profiles.get(position).isAddButton()){ // Selected pet profile is a cat (Pet_Data.specie = true)
            holder.icon.setImageResource(R.drawable.ic_cat_walk);
            holder.rel_lay_color.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.rel_lay_color.setBackgroundColor(R.color.main_purple);
                    pet_id_selected = position;
                    basic_user_data.getInstance().setLast_pet_id_selected_carousel(pet_id_selected);
                }
            });
        } else if(!pet_profiles.get(position).getSpecie()&&!pet_profiles.get(position).isAddButton()){ // Selected pet profile is a dog (Pet_Data.specie = false)
            holder.icon.setImageResource(R.drawable.ic_baseline_pets_24);
            holder.rel_lay_color.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.rel_lay_color.setBackgroundColor(R.color.main_purple);
                    pet_id_selected = position;
                    basic_user_data.getInstance().setLast_pet_id_selected_carousel(pet_id_selected);
                }
            });
        } else if(pet_profiles.get(position).isAddButton()){
            holder.icon.setImageResource(R.drawable.ic_baseline_add_24);
            holder.rel_lay_color.setBackgroundColor(R.color.main_purple);
            holder.rel_lay_color.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(homeFragment,getting_started_one_pet.class);
                    intent.putExtra("fromHomeFragmentCarousel",true);
                    homeFragment.startActivity(intent);
                }
            });
        }
        if(position==pet_id_selected){
            holder.rel_lay_color.setBackgroundColor(R.color.main_orange);
        } else{
            holder.rel_lay_color.setBackgroundColor(R.color.white);
        }
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
    public void setHomeFragment(FragmentActivity homeFragment){
        this.homeFragment = homeFragment;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView pet_name;
        private ImageView icon;
        private RelativeLayout rel_lay_color;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pet_name = itemView.findViewById(R.id.pet_carousel_pet_profile_name);
            icon = itemView.findViewById(R.id.pet_carousel_item_icon);
            rel_lay_color = itemView.findViewById(R.id.pet_carousel_item_rel_lay);
        }
    }
}
