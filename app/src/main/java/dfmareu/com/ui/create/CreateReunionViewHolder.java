package dfmareu.com.ui.create;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dfmareu.com.databinding.ActivityCreateReunionItemBinding;

public class CreateReunionViewHolder extends RecyclerView.ViewHolder{

    final ActivityCreateReunionItemBinding activityCreateReunionItemBinding;

    public CreateReunionViewHolder(@NonNull ActivityCreateReunionItemBinding activityCreateReunionItemBinding) {
        super(activityCreateReunionItemBinding.getRoot());
        this.activityCreateReunionItemBinding = activityCreateReunionItemBinding;
    }

    public void bindView(String participant) {
        activityCreateReunionItemBinding.activityCreateReunionItemGuestListTxtView.setText(participant);
    }
}