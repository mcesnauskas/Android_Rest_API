package lt.mindaugas.androidrestapi.users.ui;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import lombok.Getter;
import lt.mindaugas.androidrestapi.databinding.RecyclerviewItemBinding;

@Getter
public class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView firstName;
    private final TextView lastName;
    private final TextView email;
    private final ImageView avatar;
    private final ConstraintLayout constraintLayout;

    public ViewHolder(
            @NonNull RecyclerviewItemBinding binding,
            OnItemClickListener listener,
            OnItemLongClickListener longClickListener
    ) {
        super(binding.getRoot());
        firstName = binding.firstNameTextView;
        lastName = binding.lastNameTextView;
        email = binding.emailTextView;
        avatar = binding.avatarImageView;
        constraintLayout = binding.itemLayout;

        constraintLayout.setOnClickListener(
                view -> {
                    if (listener != null) {
                        if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                            listener.onItemClick(getBindingAdapterPosition());
                        }
                    }
                }
        );

        constraintLayout.setOnLongClickListener(view -> {
                    if (longClickListener != null) {
                        if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                            longClickListener.onItemLongClick(getBindingAdapterPosition());
                        }
                    }
                    return true;
                }
        );

    }
}
