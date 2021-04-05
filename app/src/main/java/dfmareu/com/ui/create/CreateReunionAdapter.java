package dfmareu.com.ui.create;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dfmareu.com.databinding.ActivityCreateReunionItemBinding;

public class CreateReunionAdapter extends RecyclerView.Adapter<CreateReunionViewHolder> {
    final List<String> mParticipantsList;

    public CreateReunionAdapter(List<String> mParticipantsList) {
        this.mParticipantsList = mParticipantsList;
    }

    @NonNull
    @Override
    public CreateReunionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ActivityCreateReunionItemBinding activityCreateReunionItemBinding = ActivityCreateReunionItemBinding.inflate(layoutInflater, parent, false);
        return new CreateReunionViewHolder(activityCreateReunionItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CreateReunionViewHolder holder, int position) {
        holder.bindView(mParticipantsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mParticipantsList.size();
    }
}
