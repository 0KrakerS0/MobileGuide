package st.pawel.mobilnyprzewodnik.object.ui.adapter;

import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.LinkedList;
import java.util.List;
import org.parceler.Parcels;
import st.pawel.mobilnyprzewodnik.city.ui.model.CityView;

public class ObjectCitySpinnerAdapter extends BaseAdapter {

    private static final String CITY_LIST = "CITY_LIST";

    private final List<CityView> cityViews;

    public ObjectCitySpinnerAdapter() {
        cityViews = new LinkedList<>();
    }

    @Override
    public int getCount() {
        return cityViews.size();
    }

    @Override
    public CityView getItem(int position) {
        return cityViews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(CityView markerView) {
        cityViews.add(markerView);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;
        if (convertView != null) {
            result = convertView;
        } else {
            result = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        bindContentView(position, (TextView) result);
        return result;
    }

    private void bindContentView(int position, TextView convertView) {
        final CityView cityView = cityViews.get(position);
        convertView.setText(cityView.cityName());
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(CITY_LIST, Parcels.wrap(cityViews));
    }

    public void onRestoreInstanceState(@Nullable Bundle saveInstanceState) {
        if (saveInstanceState == null) {
            return;
        }
        List<CityView> cityViewList = Parcels.unwrap(saveInstanceState.getParcelable(CITY_LIST));
        cityViews.addAll(cityViewList);
    }
}
