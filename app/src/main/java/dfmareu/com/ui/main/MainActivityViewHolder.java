package dfmareu.com.ui.main;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

import dfmareu.com.databinding.ActivityMainItemBinding;
import dfmareu.com.models.Reunion;
import dfmareu.com.util.ReunionUtil;

public class MainActivityViewHolder extends RecyclerView.ViewHolder {

    ActivityMainItemBinding activityMainItemBinding;
    Reunion reunion;

    public MainActivityViewHolder(@NonNull ActivityMainItemBinding activityMainItemBinding) {
        super(activityMainItemBinding.getRoot());
        this.activityMainItemBinding = activityMainItemBinding;
    }

    public void bindView(Reunion reunion, MainActivityAdapter.Listener callback){
        this.reunion = reunion;
        activityMainItemBinding.activityMainItemRoom.setText(reunion.getRoom());
        activityMainItemBinding.activityMainItemHour.setText(reunion.getHours());
        activityMainItemBinding.activityMainItemSubject.setText(reunion.getSubject());
        activityMainItemBinding.activityMainParticipants.setText(Arrays.toString(reunion.getParticipants()).replaceAll("[\\[\\]]",""));
        activityMainItemBinding.activityMainItemGarbage.setOnClickListener(view -> callback.onClickDelete(reunion));
        int color = ReunionUtil.setColorChip(reunion);
        activityMainItemBinding.activityMainItemImageChip.setImageResource(color);
    }
}
