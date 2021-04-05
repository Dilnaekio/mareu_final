package dfmareu.com.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dfmareu.com.databinding.ActivityMainItemBinding;
import dfmareu.com.models.Reunion;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityViewHolder> {

    private final Listener callback;
    final ArrayList<Reunion> mReunions;
    public MainActivityAdapter(ArrayList<Reunion> reunions, Listener callback) {
        this.mReunions = reunions;
        this.callback= callback;
    }

    @NonNull
    @Override
    public MainActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ActivityMainItemBinding activityMainItemBinding = ActivityMainItemBinding.inflate(layoutInflater, parent, false);
        return new MainActivityViewHolder(activityMainItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityViewHolder holder, int position) {
        holder.bindView(mReunions.get(position), callback);
    }

    @Override
    public int getItemCount() {
        return mReunions.size();
    }

    public interface Listener {
        void onClickDelete(Reunion reunion);
    }
}