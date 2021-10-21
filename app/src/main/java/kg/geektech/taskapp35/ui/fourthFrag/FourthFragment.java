package kg.geektech.taskapp35.ui.fourthFrag;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import org.jetbrains.annotations.NotNull;
import kg.geektech.taskapp35.R;
import kg.geektech.taskapp35.databinding.FragmentFouthBinding;
import kg.geektech.taskapp35.sharedpref.Prefs;
import kg.geektech.taskapp35.ui.auth.LoginFragment;

public class FourthFragment extends Fragment {

    private FragmentFouthBinding binding;
    private ActivityResultLauncher<String> resultLauncher;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFouthBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listener();
        editTextSave();
        binding.btnSignOut.setOnClickListener(v ->alertSignOut());
        changeBtn();
        pickImage();
    }
//    private void signOut() {
//        binding.btnSignOut.setOnClickListener(v ->{
//            FirebaseAuth.getInstance().signOut();
//        });
//        googleSignInClient.signOut().addOnCompleteListener(requireActivity(), new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull @NotNull Task<Void> task) {
//                signOut();
//            }
//        });
//    }

    private void editTextSave() {
        binding.nikNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Prefs prefs = new Prefs(requireContext());
                prefs.saveEditText(s.toString());
            }
        });
    }

    private void pickImage() {
        binding.avatarIv.setOnClickListener(v -> resultLauncher.launch("image/*"));
    }

    private void listener() {
        Prefs prefs = new Prefs(requireContext());
        String uri = prefs.getAvatar();
        String name = prefs.getEditText();
        Glide.with(requireActivity()).load(uri).circleCrop().into(binding.avatarIv);
        binding.nikNameEdit.setText(name);
    }

    private void changeBtn() {
        resultLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                result -> {
                    Glide.with(requireActivity()).load(result).circleCrop().into(binding.avatarIv);
                    Prefs prefs = new Prefs(requireContext());
                    prefs.saveAvatar(result);
                });
    }
    public void alertSignOut()
    {
        AlertDialog.Builder alertDialog2 = new
                AlertDialog.Builder(
                getActivity());
        alertDialog2.setTitle("Confirm SignOut");
        alertDialog2.setMessage("Are you sure you want to Sign out?");
        alertDialog2.setPositiveButton("YES",
                (dialog, which) -> {
            FirebaseAuth.getInstance().signOut();
            new Prefs(requireContext()).saveBoardsState();
            NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.loginFragment);
                });
        alertDialog2.setNegativeButton("NO",
                (dialog, which) -> {
                    Toast.makeText(requireActivity(),
                            "You clicked on NO", Toast.LENGTH_SHORT)
                            .show();
                    dialog.cancel();
                });
        alertDialog2.show();
    }
}