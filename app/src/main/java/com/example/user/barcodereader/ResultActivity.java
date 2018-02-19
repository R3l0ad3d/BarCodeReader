package com.example.user.barcodereader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultActivity extends AppCompatActivity {

    TextView res;
    static String BASE_URL;
    private APICall apiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        res = findViewById(R.id.result);

        String barcode = getIntent().getStringExtra("code");

        res.setText(barcode);

    }

    public void submitBarCode(View view) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCall = retrofit.create(APICall.class);
        Call<ReturnType> returnTypeCall = apiCall.getRespons("");

        returnTypeCall.enqueue(new Callback<ReturnType>() {
            @Override
            public void onResponse(Call<ReturnType> call, Response<ReturnType> response) {
                if(response.code()==200){
                    ReturnType returnType = response.body();
                    Toast.makeText(getApplicationContext(),returnType.getMessage(),Toast.LENGTH_LONG);
                }else {
                    Toast.makeText(getApplicationContext(),response.errorBody().toString(),Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<ReturnType> call, Throwable t) {

            }
        });
    }
}
