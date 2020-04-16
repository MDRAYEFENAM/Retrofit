package mdrayefenam.com;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mdrayefenam.com.adapter.FlowerAdapter;
import mdrayefenam.com.model.FlowerResponseModel;
import mdrayefenam.com.service.FlowerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "http://services.hanselandpetal.com/";
    private FlowerService service;
    ListView listView;
    private List<FlowerResponseModel> responseModels;
    private FlowerAdapter flowerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        responseModels = new ArrayList<>();

        //1. retrofit object creation
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //2. service object Cre
        service = retrofit.create(FlowerService.class);
        Call<List<FlowerResponseModel>> apiCall = service.getAllFlowers();
        apiCall.enqueue(new Callback<List<FlowerResponseModel>>() {
            @Override
            public void onResponse(Call<List<FlowerResponseModel>> call, Response<List<FlowerResponseModel>> response) {
                if (response.code() == 200){
                    responseModels = response.body();
                    Log.e("onResponse",responseModels.toString());
                    flowerAdapter = new FlowerAdapter(MainActivity.this, responseModels);
                    listView.setAdapter(flowerAdapter);
                }else{

                }
            }

            @Override
            public void onFailure(Call<List<FlowerResponseModel>> call, Throwable t) {

            }
        });
        //to show details after list item click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FlowerResponseModel response = responseModels.get(position);
                startActivity(new Intent(MainActivity.this,
                        DetailsActivity.class)
                        .putExtra("flowers",(response)));
            }
        });
    }
}
