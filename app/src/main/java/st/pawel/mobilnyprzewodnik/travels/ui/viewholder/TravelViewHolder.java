package st.pawel.mobilnyprzewodnik.travels.ui.viewholder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.travels.ui.model.TravelView;

public class TravelViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.travel_name)
    TextView travelname;

    @Bind(R.id.image)
    ImageView image;

    @Bind(R.id.city_name)
    TextView cityName;

    @Bind(R.id.rate)
    TextView rate;

    @Bind(R.id.object_number)
    TextView objectNumber;


    public TravelViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(TravelView travelView) {

        travelname.setText(travelView.name());
        cityName.setText(travelView.cityName());
        rate.setText(String.valueOf(travelView.rate()));
        objectNumber.setText(String.valueOf(travelView.objectNumber()));
    }
}