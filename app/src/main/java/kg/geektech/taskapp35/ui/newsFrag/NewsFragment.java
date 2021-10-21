package kg.geektech.taskapp35.ui.newsFrag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.google.firebase.firestore.FirebaseFirestore;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;
import kg.geektech.taskapp35.R;
import kg.geektech.taskapp35.databinding.FragmentNewsBinding;
import kg.geektech.taskapp35.ui.home.News;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSave.setOnClickListener(v -> saveData());
    }

    private void saveData() {
        String text = Objects.requireNonNull(binding.editText.getText()).toString();
        News news = new News(text);

        saveFirestore(news);

        Bundle bundle = new Bundle();
        bundle.putSerializable("news", news);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
    }
    private void saveFirestore(News news) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("news").add(news).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(requireActivity(), "Saved", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(requireActivity(), "Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
            close();
        });
    }
    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}