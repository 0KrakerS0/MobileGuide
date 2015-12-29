package st.pawel.mobilnyprzewodnik.city.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.city.ui.model.CityView;
import st.pawel.mobilnyprzewodnik.common.util.ImageLoaderProvider;

public class CityViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.city_icon)
    ImageView cityIcon;

    @Bind(R.id.travel_city_name)
    TextView cityName;

    public CityViewHolder(View citysView) {
        super(citysView);
        ButterKnife.bind(this, citysView);
    }

    public void bind(CityView cityView) {
        ImageLoaderProvider.newInstance(cityIcon.getContext()).displayImage(cityView.cityImageUrl(), cityIcon);
        cityName.setText(cityView.cityName());
    }
}
