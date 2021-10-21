package kg.geektech.taskapp35.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.firestore.FirebaseFirestore;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.List;
import kg.geektech.taskapp35.R;
import kg.geektech.taskapp35.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeAdapter adapter;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HomeAdapter();
        readData();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fab.setOnClickListener(v -> openFragment());
        getParentFragmentManager().setFragmentResultListener("rk_news",
                getViewLifecycleOwner(),
                (requestKey, result) -> {
                    News news = (News) result.getSerializable("news");
                    Log.e("Home", "text = " + news.getTitle());
                    adapter.addItem(news);
                });
        initRecycler();
    }

    private void initRecycler() {
        binding.recyclerHome.setAdapter(adapter);
    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.taskFragment);
    }
    private void readData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("news").get().addOnSuccessListener(snapshots -> {
            List<News> list = snapshots.toObjects(News.class);
            adapter.addItems(list);
        });
    }
}