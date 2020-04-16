package mdrayefenam.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mdrayefenam.com.model.FlowerResponseModel;

public class DetailsActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.tv);

        FlowerResponseModel responseModel = (FlowerResponseModel) getIntent().getSerializableExtra("flowers");
        //Take photo from Flower response
        String photoString = responseModel.getPhoto();
        Uri photoUri = Uri.parse(MainActivity.BASE_URL+"photos/"+photoString);
        Picasso.get().load(photoUri).into(imageView);

        textView.setText(responseModel.getName()+"\n"
        +responseModel.getCategory()+"\n"
        +responseModel.getInstructions()+ "\n"
        //responseModel.getProductId()+"\n"
        +responseModel.getPrice());
    }
    public void back(View view) {
        startActivity(new Intent(DetailsActivity.this, MainActivity.class));
    }
}
