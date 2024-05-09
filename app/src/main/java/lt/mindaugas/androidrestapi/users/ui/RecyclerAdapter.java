package lt.mindaugas.androidrestapi.users.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import lt.mindaugas.androidrestapi.R;
import lt.mindaugas.androidrestapi.databinding.RecyclerviewItemBinding;
import lt.mindaugas.androidrestapi.entity.User;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<User> list;
    private final Context context;
    private final OnItemClickListener listener;
    private final OnItemLongClickListener longClickListener;

    public RecyclerAdapter(
            Context context,
            OnItemClickListener listener,
            OnItemLongClickListener longClickListener
    ) {
        this.context = context;
        this.listener = listener;
        this.longClickListener = longClickListener;
        this.list = new ArrayList<>();
    }

    public void addList(List<User> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public long getUserByPosition(int position) {
        return list.get(position).getId();
    }

    public User getUserDetails(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewItemBinding binding =
                RecyclerviewItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false
                );

        return new ViewHolder(binding, listener, longClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getFirstName().setText(list.get(position).getFirstName());
        holder.getLastName().setText(list.get(position).getLastName());
        holder.getEmail().setText(list.get(position).getEmail());

        Glide.with(context)
                .load(list.get(position).getAvatar())
                .into(holder.getAvatar());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
