package com.gatrium.gatrium;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RemindersRecyclerViewAdapter extends RecyclerView.Adapter<RemindersRecyclerViewAdapter.ViewHolder>{
    private ArrayList<Reminder> all_reminders;
    private int pet_id;
    @NonNull
    @Override
    public RemindersRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.your_reminders_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RemindersRecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.reminder_name.setText(all_reminders.get(position).getReminderId());
        holder.reminder_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCurrentReminder(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return all_reminders.size();
    }

    public void setAll_reminders(ArrayList<Reminder> all_reminders){
        this.all_reminders = all_reminders;
        notifyDataSetChanged();
    }
    public void setPet_id(int pet_id){
        this.pet_id = pet_id;
    }

    public void deleteCurrentReminder(int position){
        basic_user_data user = basic_user_data.getInstance();
        user.deleteReminderToPet(all_reminders.get(position).getReminderId(),pet_id);
        all_reminders.remove(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView reminder_name;
        private ImageView reminder_delete_button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reminder_name = itemView.findViewById(R.id.reminderItemText);
            reminder_delete_button = itemView.findViewById(R.id.reminder_delete_button);
        }
    }
}