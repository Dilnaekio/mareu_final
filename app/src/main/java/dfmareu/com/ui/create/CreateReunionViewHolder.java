package dfmareu.com.ui.create;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dfmareu.com.databinding.ActivityCreateReunionItemBinding;

public class CreateReunionViewHolder extends RecyclerView.ViewHolder {

    final ActivityCreateReunionItemBinding reunionItemBinding;

    public CreateReunionViewHolder(@NonNull ActivityCreateReunionItemBinding activityCreateReunionItemBinding) {
        super(activityCreateReunionItemBinding.getRoot());
        this.reunionItemBinding = activityCreateReunionItemBinding;
    }

    public void bindView(String participant) {
        reunionItemBinding.activityCreateReunionItemGuestListTxtView.setText(participant);
    }
}