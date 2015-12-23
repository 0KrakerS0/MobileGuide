package st.pawel.mobilnyprzewodnik.city.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.city.ui.model.CityView;

public class CityViewHolder extends RecyclerView.ViewHolder{

    //@Bind(R.id.city_icon)
    //ImageView cityicon;

    @Bind(R.id.city_name)
    TextView cityname;

    public CityViewHolder(View citysView) {
        super(citysView);
        ButterKnife.bind(this, citysView);
    }

    public void bind(CityView cityView) {
        //cityicon.setImageResource(cityView.cityIcon());
        cityname.setText(cityView.cityName());
    }
}
