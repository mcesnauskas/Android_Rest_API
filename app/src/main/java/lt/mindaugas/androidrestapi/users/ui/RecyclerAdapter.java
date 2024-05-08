package lt.mindaugas.androidrestapi.users.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import lt.mindaugas.androidrestapi.R;
import lt.mindaugas.androidrestapi.databinding.RecyclerviewItemBinding;
import lt.mindaugas.androidrestapi.entity.User;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<User> list;
    private Context context;
    private OnItemClickListener listener;
    private OnItemLongClickListener longClickListener;

    public RecyclerAdapter(
            Context context,
            List<User> list,
            OnItemClickListener listener,
            OnItemLongClickListener longClickListener
    ){
        this.context = context;
        this.list = list;
        this.listener = listener;
        this.longClickListener = longClickListener;
    }

    public void addList(List<User> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewItemBinding binding =
                RecyclerviewItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false
                );

        return new ViewHolder(binding, context, listener, longClickListener);
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
