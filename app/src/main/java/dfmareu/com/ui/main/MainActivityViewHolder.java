package dfmareu.com.ui.main;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

import dfmareu.com.databinding.ActivityMainItemBinding;
import dfmareu.com.models.Reunion;
import dfmareu.com.util.ColorChip;

public class MainActivityViewHolder extends RecyclerView.ViewHolder {

    final ActivityMainItemBinding itemBinding;
    Reunion reunion;

    public MainActivityViewHolder(@NonNull ActivityMainItemBinding activityMainItemBinding) {
        super(activityMainItemBinding.getRoot());
        this.itemBinding = activityMainItemBinding;
    }

    public void bindView(Reunion reunion, MainActivityAdapter.Listener callback){
        this.reunion = reunion;
        itemBinding.activityMainItemRoom.setText(reunion.getRoom());
        itemBinding.activityMainItemHour.setText(reunion.getHours());
        itemBinding.activityMainItemSubject.setText(reunion.getSubject());
        itemBinding.activityMainParticipants.setText(Arrays.toString(reunion.getParticipants()).replaceAll("[\\[\\]]",""));
        itemBinding.activityMainItemGarbage.setOnClickListener(view -> callback.onClickDelete(reunion));
        int color = ColorChip.setColorChip(reunion);
        itemBinding.activityMainItemImageChip.setImageResource(color);
    }
}
