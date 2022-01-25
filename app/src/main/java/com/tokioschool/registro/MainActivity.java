package com.tokioschool.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.adapters.AdapterViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.tokioschool.registro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadSpinner();
        listeners();
    }

    private void listeners() {
        binding.loginTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(binding.loginTextName.getText().toString().length()>0){
                    if(binding.loginTextName.getText().toString().contains("@")||binding.loginTextName.getText().toString().contains("!")){
                        binding.loginTextName.setError(getString(R.string.login_text_error));
                    }
                }
            }
        });
        binding.loginTextSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(binding.loginTextSurname.getText().toString().length()>0){
                    if(binding.loginTextSurname.getText().toString().contains("@")||binding.loginTextSurname.getText().toString().contains("!")){
                        binding.loginTextSurname.setError(getString(R.string.login_text_error));
                    }
                }
            }
        });
        binding.loginSpinnerAges.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(position<3){
                    binding.loginSpinnerAges.setError((getString(R.string.login_age_error)));
                }else{
                    binding.loginSpinnerAges.setError(null);
                }
            }
        });
    }

    private void loadSpinner() {
        ArrayAdapter<String> listAgesAdapter= new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.listAges));
        binding.loginSpinnerAges.setAdapter(listAgesAdapter);
    }



}