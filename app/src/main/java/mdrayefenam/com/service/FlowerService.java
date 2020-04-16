package mdrayefenam.com.service;

import java.util.List;

import mdrayefenam.com.model.FlowerResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FlowerService {
    @GET("feeds/flowers.json")
    Call<List<FlowerResponseModel>> getAllFlowers();
}
