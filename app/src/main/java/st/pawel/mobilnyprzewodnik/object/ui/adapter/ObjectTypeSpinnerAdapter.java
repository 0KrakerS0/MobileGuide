package st.pawel.mobilnyprzewodnik.object.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.LinkedList;
import java.util.List;
import st.pawel.mobilnyprzewodnik.map.model.MarkerView;

public class ObjectTypeSpinnerAdapter extends BaseAdapter {

    private final List<MarkerView> markerViews;


    public ObjectTypeSpinnerAdapter() {
        markerViews = new LinkedList<>();
    }

    @Override
    public int getCount() {
        return markerViews.size();
    }

    @Override
    public MarkerView getItem(int position) {
        return markerViews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(MarkerView markerView){
        markerViews.add(markerView);
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
        final MarkerView markerView = markerViews.get(position);
        convertView.setCompoundDrawablesWithIntrinsicBounds(markerView.markerIcon(), 0, 0, 0);
        convertView.setText(markerView.typeRes());
    }
}
