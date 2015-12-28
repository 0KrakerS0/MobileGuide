package st.pawel.mobilnyprzewodnik.city.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;
import java.util.List;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.city.ui.model.CityView;
import st.pawel.mobilnyprzewodnik.city.ui.viewholder.CityViewHolder;


public class CityAdapter extends RecyclerView.Adapter<CityViewHolder> {

    List<CityView> cityViews;

    OnCityItemClickListener onCityItemClickListener = OnCityItemClickListener.NULL;

    public CityAdapter() {
        cityViews = new LinkedList<>();
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final CityViewHolder holder = new CityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_city, parent, false));
        holder.itemView.setOnClickListener(v -> onCityItemClickListener.onCityItemClick(cityViews.get(holder.getAdapterPosition())));
        return holder;
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bind(cityViews.get(position));
    }

    @Override
    public int getItemCount() {
        return cityViews.size();
    }

    public void add(CityView cityView) {
        cityViews.add(cityView);
    }

    public void setOnCityItemClickListener(OnCityItemClickListener listener) {
        this.onCityItemClickListener = listener != null ? listener : OnCityItemClickListener.NULL;
    }

    public void setNewListItems(List<CityView> cityViews) {
        this.cityViews.clear();
        this.cityViews.addAll(cityViews);
    }

    public interface OnCityItemClickListener {

        OnCityItemClickListener NULL = cityView -> {/*Nic nie rób*/};

        void onCityItemClick(CityView cityView);
    }
}

