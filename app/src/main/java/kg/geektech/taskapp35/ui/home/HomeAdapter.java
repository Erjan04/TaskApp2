package kg.geektech.taskapp35.ui.home;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kg.geektech.taskapp35.R;
import kg.geektech.taskapp35.databinding.ItemListBinding;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private final ArrayList<News> list = new ArrayList<>();
    private ItemListBinding binding;

    public HomeAdapter() { }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        binding = ItemListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.onBind(position);
        if (position % 2 == 0){
            binding.textItemTv.setBackgroundResource(R.color.ser);
        }else{
            binding.textItemTv.setBackgroundResource(R.color.white);
        }

    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public void addItem(News news) {
        list.add(news);
        notifyDataSetChanged();
    }
    public void addItems(List<News> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemListBinding binding;

        public ViewHolder(@NonNull @NotNull ItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(int position) {
            binding.timeText.setText(String.valueOf(changeTypeOfDateToAgo(list.get(position).getTime())));

            binding.getRoot().setOnLongClickListener(v -> {
                AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setTitle("Attention !!").setMessage("delete item ?").setPositiveButton("yes", (dialog, which) -> {
                    list.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }).setNegativeButton("No", (dialog, which) -> dialog.dismiss()).show();
                return true;
            });

        }

        private String changeTypeOfDateToAgo(Object createdAt) {

            long milliSecPerMinute = 60 * 1000; //Milliseconds Per Minute
            long milliSecPerHour = milliSecPerMinute * 60; //Milliseconds Per Hour
            long milliSecPerDay = milliSecPerHour * 24; //Milliseconds Per Day
            long milliSecPerMonth = milliSecPerDay * 30; //Milliseconds Per Month
            long milliSecPerYear = milliSecPerDay * 365; //Milliseconds Per Year
            String agoTime;
            //Difference in Milliseconds between two dates
            if (createdAt != null) {
                long msExpired = System.currentTimeMillis() - (Long) createdAt;
                //Second or Seconds ago calculation
                if (msExpired < milliSecPerMinute) {
                    if (Math.round(msExpired / 1000) == 1) {
                        return agoTime = String.valueOf(Math.round(msExpired / 1000)) + " second ago... ";
                    } else {
                        return agoTime = String.valueOf(Math.round(msExpired / 1000) + " seconds ago...");
                    }
                }
                //Minute or Minutes ago calculation
                else if (msExpired < milliSecPerHour) {
                    if (Math.round(msExpired / milliSecPerMinute) == 1) {
                        return agoTime = String.valueOf(Math.round(msExpired / milliSecPerMinute)) + " minute ago... ";
                    } else {
                        return agoTime = String.valueOf(Math.round(msExpired / milliSecPerMinute)) + " minutes ago... ";
                    }
                }
                //Hour or Hours ago calculation
                else if (msExpired < milliSecPerDay) {
                    if (Math.round(msExpired / milliSecPerHour) == 1) {
                        return agoTime = String.valueOf(Math.round(msExpired / milliSecPerHour)) + " hour ago... ";
                    } else {
                        return agoTime = String.valueOf(Math.round(msExpired / milliSecPerHour)) + " hours ago... ";
                    }
                }
                //Day or Days ago calculation
                else if (msExpired < milliSecPerMonth) {
                    if (Math.round(msExpired / milliSecPerDay) == 1) {
                        return agoTime = String.valueOf(Math.round(msExpired / milliSecPerDay)) + " day ago... ";
                    } else {
                        return agoTime = String.valueOf(Math.round(msExpired / milliSecPerDay)) + " days ago... ";
                    }
                }
                //Month or Months ago calculation
                else if (msExpired < milliSecPerYear) {
                    if (Math.round(msExpired / milliSecPerMonth) == 1) {
                        return agoTime = String.valueOf(Math.round(msExpired / milliSecPerMonth)) + "  month ago... ";
                    } else {
                        return agoTime = String.valueOf(Math.round(msExpired / milliSecPerMonth)) + "  months ago... ";
                    }
                }
                //Year or Years ago calculation
                else {
                    if (Math.round(msExpired / milliSecPerYear) == 1) {
                        return agoTime = String.valueOf(Math.round(msExpired / milliSecPerYear)) + " year ago...";
                    } else {
                        return agoTime = String.valueOf(Math.round(msExpired / milliSecPerYear)) + " years ago...";
                    }
                }
            }
            return agoTime = "time not found";
        }
    }
}
