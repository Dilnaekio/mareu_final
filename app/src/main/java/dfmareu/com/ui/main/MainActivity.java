package dfmareu.com.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dfmareu.com.R;
import dfmareu.com.api.DeleteListener;
import dfmareu.com.base.BaseActivity;
import dfmareu.com.databinding.ActivityMainBinding;
import dfmareu.com.databinding.ActivityMainItemBinding;
import dfmareu.com.models.Reunion;
import dfmareu.com.repository.ReunionRepository;
import dfmareu.com.ui.create.CreateReunion;

public class MainActivity extends BaseActivity implements DeleteListener {

    //Keys for bundles
    public final static String NAVIGATIONparticipants = "dfmareu.com.Views.NAVIGATIONparticipants";
    public final static String NAVIGATIONsubject = "dfmareu.com.Views.NAVIGATIONsubject";
    public final static String NAVIGATIONroom = "dfmareu.com.Views.NAVIGATIONroom";
    public final static String NAVIGATIONday = "dfmareu.com.Views.NAVIGATIONday";
    public final static String NAVIGATIONmonth = "dfmareu.com.Views.NAVIGATIONmonth";
    public final static String NAVIGATIONyear = "dfmareu.com.Views.NAVIGATIONyear";
    public final static String NAVIGATIONtime = "dfmareu.com.Views.NAVIGATIONtime";
    //Requests codes
    private static final int CREATE_REUNION_ACTIVITY_REQUEST_CODE = 42;
    //Items
    public TextView mItemRoom, mItemHour, mItemParticipants, mItemSubject;
    public ImageButton mItemGarbage;
    //Var for bundles informations about the reunion created
    public int mDay, mMonth, mYear;
    public String mHour, mRoom, mSubject;
    public Bundle mReunion;
    public ArrayList<String> mParticipants = new ArrayList<>();
    //Adapter
    MainActivityAdapter mainActivityAdapter;
    //RecyclerView
    RecyclerView mainActivityRecyclerView;
    //Views
    ActivityMainBinding binding;
    ActivityMainItemBinding itemBinding;
    Bundle bundleCreateReunion = new Bundle();
    ReunionRepository reunionRepository;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (CREATE_REUNION_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {

            try {
                assert data != null;
                mReunion = data.getBundleExtra(CreateReunion.NAVIGATIONbundle);
                mDay = mReunion.getInt(NAVIGATIONday);
                mMonth = mReunion.getInt(NAVIGATIONmonth);
                mYear = mReunion.getInt(NAVIGATIONyear);
                mHour = mReunion.getString(NAVIGATIONtime);
                mRoom = mReunion.getString(NAVIGATIONroom);
                mSubject = mReunion.getString(NAVIGATIONsubject);
                mParticipants = mReunion.getStringArrayList(NAVIGATIONparticipants);

                Reunion reunion = new Reunion(mParticipants, mSubject, mRoom, mDay, mMonth, mYear, mHour);
                reunionRepository.addReunion(reunion);
                mainActivityAdapter.notifyDataSetChanged();

            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("Récupération impossible des informations de réunion");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureBindingViews();
        reunionRepository = getReunionRepository();
        configureRecyclerView();
        configureFabButton();

        bundleCreateReunion = this.getIntent().getExtras();
    }

    protected void configureBindingViews() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.toolbar);

        itemBinding = ActivityMainItemBinding.inflate(getLayoutInflater());
        mItemHour = itemBinding.activityMainItemHour;
        mItemRoom = itemBinding.activityMainItemRoom;
        mItemSubject = itemBinding.activityMainItemSubject;
        mItemParticipants = itemBinding.activityMainParticipants;
        mItemGarbage = itemBinding.activityMainItemGarbage;
    }

    private void configureRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.activityMainRecyclerView.setLayoutManager(layoutManager);

        //View Recyclerview
        mainActivityRecyclerView = binding.activityMainRecyclerView;

        mainActivityAdapter = new MainActivityAdapter(reunionRepository.getReunions(), this);
        binding.activityMainRecyclerView.setAdapter(mainActivityAdapter);
    }

    private void configureFabButton() {
        binding.fab.setOnClickListener(v -> {
            Intent addReunion = new Intent(MainActivity.this, CreateReunion.class);
            startActivityForResult(addReunion, CREATE_REUNION_ACTIVITY_REQUEST_CODE);
        });
    }

    //FILTERS
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Ex: Salle A Ex : 25, ...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Reunion> filteredDateList;
                ArrayList<Reunion> filteredRoomList;

                if (newText == null || newText.length() == 0) {
                    //If filter is empty => add all reunionRepository reunions in filteredList
                    mainActivityAdapter = new MainActivityAdapter(reunionRepository.getReunions(), MainActivity.this);
                } else {
                    String filterPattern = newText.toLowerCase().trim();
                    //If not, create a copy with all reunions in two different arrays. One checking if filterPattern concern date, the second checking rooms
                    filteredDateList = reunionRepository.getFilteredDate(filterPattern);
                    filteredRoomList = reunionRepository.getFilteredRoom(filterPattern);

                    //Check if date are filtered
                    if (!filteredDateList.isEmpty()) {
                        mainActivityAdapter = new MainActivityAdapter(filteredDateList, MainActivity.this);
                    } else {
                        //If date list is empty, check if the room lists contains reunions (at this point, it necessarily contains reunions)
                        if (!filteredRoomList.isEmpty()) {
                            mainActivityAdapter = new MainActivityAdapter(filteredRoomList, MainActivity.this);
                        } else {
                            mainActivityAdapter = new MainActivityAdapter(reunionRepository.getReunions(), MainActivity.this);
                            searchView.setQuery("", false);
                            searchView.clearFocus();
                            Toast toast = Toast.makeText(getApplicationContext(), "Invalid character. You must search Salle A to J or day number 1 to 31)", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                }
                binding.activityMainRecyclerView.setAdapter(mainActivityAdapter);
                return false;
            }
        });
        searchView.setOnCloseListener(() -> false);
        return true;
    }

    //Initialise the public interface "Listener" in MainActivityAdapter
    @Override
    public void onClickDelete(Reunion reunion) {
        getReunionRepository().deleteReunion(reunion);
        mainActivityAdapter.notifyDataSetChanged();
    }

//    //If the screen orientation change, reset the data from the array into the recyclerview
//    @Override
//    public void onConfigurationChanged(@NonNull Configuration configuration) {
//        super.onConfigurationChanged(configuration);
//        int size = reunionRepository.getReunions().size();
//        if (size > 0) {
//            reunionRepository.getReunions().subList(0, size).clear();
//        }
//    }
}

