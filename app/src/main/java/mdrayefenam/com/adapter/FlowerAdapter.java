package mdrayefenam.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import mdrayefenam.com.R;
import mdrayefenam.com.model.FlowerResponseModel;

import static android.media.CamcorderProfile.get;

public class FlowerAdapter extends ArrayAdapter<FlowerResponseModel> {
    private Context context;
    private List<FlowerResponseModel> flowerResponseModels;

    public FlowerAdapter(@NonNull Context context, List<FlowerResponseModel> flowerResponseModels) {
        super(context, R.layout.display_row,flowerResponseModels);
        this.context = context;
        this.flowerResponseModels = flowerResponseModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.display_row,parent, Boolean.parseBoolean(null));

        TextView nameTV = convertView.findViewById(R.id.flower_name);
        TextView priceTV = convertView.findViewById(R.id.flower_price);

        nameTV.setText(flowerResponseModels.get(position).getName());
        priceTV.setText(String.valueOf(flowerResponseModels.get(position).getPrice()));

        return convertView;
    }
}
