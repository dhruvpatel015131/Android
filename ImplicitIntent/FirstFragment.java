package com.example.implicitintend;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.implicitintend.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

private FragmentFirstBinding binding;
    Button button;
    EditText url;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentFirstBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       view.findViewById(R.id.open).setOnClickListener(view1 ->
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment)
        );

       button = view.findViewById(R.id.button);
       url = view.findViewById(R.id.url);
       button.setOnClickListener(view12 -> {
           String urlText = url.getText().toString();
           Toast.makeText(getContext(), urlText, Toast.LENGTH_SHORT).show();
           //Implicit Intent to open URL
           Uri webpage = Uri.parse(urlText);
           Intent intent = new Intent(Intent.ACTION_VIEW);
           intent.setData(webpage);
           startActivity(intent);
           //..
       });
    }



}