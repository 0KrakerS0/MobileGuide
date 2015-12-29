package st.pawel.mobilnyprzewodnik.travels.ui.viewholder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.util.ImageLoaderProvider;
import st.pawel.mobilnyprzewodnik.travels.ui.model.TravelView;

public class TravelViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.travel_name)
    TextView travelname;

    @Bind(R.id.travel_image)
    ImageView image;

    @Bind(R.id.travel_city_name)
    TextView cityName;

    @Bind(R.id.object_number)
    TextView objectNumber;

    @Bind(R.id.travel_rating)
    RatingBar travelRating;

    public TravelViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(TravelView travelView) {
        //TODO dla wszystkich tymczasowo ten sam rysunek
        ImageLoaderProvider.newInstance(image.getContext()).displayImage(travelView.travelImageUrl(), image);
        travelname.setText(travelView.travelName());
        cityName.setText(travelView.cityName());
        travelRating.setRating(travelView.travelRate());
        objectNumber.setText(String.valueOf(travelView.objectNumber()));
    }
}