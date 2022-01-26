package com.tokioschool.registro;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.tokioschool.registro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ActivityMainBinding binding;
    private final ActivityResultLauncher<Intent> register=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
       if(result.getResultCode()== Activity.RESULT_OK){
            if(result.getData()!=null){
                //Aquí se recogeria la imagen resultante
            }
       }
    });

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
                enableButton();
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
                enableButton();
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

    private void enableButton() {
        binding.loginBtnOk.setEnabled(false);
        if(binding.loginTextName.getText().toString().length()>0 && binding.loginTextSurname.getText().toString().length()>0){
            binding.loginBtnOk.setEnabled(true);
        }
    }

    private void loadSpinner() {
        ArrayAdapter<String> listAgesAdapter= new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.listAges));
        binding.loginSpinnerAges.setAdapter(listAgesAdapter);
    }

    public void btn_camera_click(View view){
        register.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
    }
    public void link_condiciones_click(View view){
        Uri uri=Uri.parse("https://developers.google.com/ml-kit/terms");
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

}