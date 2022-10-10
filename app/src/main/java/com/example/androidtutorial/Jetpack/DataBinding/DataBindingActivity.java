package com.example.androidtutorial.Jetpack.DataBinding;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidtutorial.R;
import com.example.androidtutorial.databinding.ActivityJetpackDataBindingBinding;

public class DataBindingActivity extends AppCompatActivity
{
    private DataBindingViewModel mViewModel;
    private ActivityJetpackDataBindingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_jetpack_data_binding);

        mViewModel = new ViewModelProvider(this).get(DataBindingViewModel.class);
        binding.setData(mViewModel);
        binding.setLifecycleOwner(this);

        /* data binding in xml file
        mViewModel.getNumber().observe(this, new Observer<Integer>()
        {
            @Override
            public void onChanged(Integer integer)
            {
                binding.textView.setText(String.valueOf(integer));
            }
        });

        binding.button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mViewModel.add();
            }
        });
        */
    }
}
