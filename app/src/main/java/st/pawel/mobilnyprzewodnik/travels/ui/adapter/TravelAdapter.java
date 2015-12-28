package st.pawel.mobilnyprzewodnik.travels.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;
import java.util.List;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.travels.ui.model.TravelView;
import st.pawel.mobilnyprzewodnik.travels.ui.viewholder.TravelViewHolder;

public class TravelAdapter extends RecyclerView.Adapter<TravelViewHolder> {

    List<TravelView> travelViews;

    OnTravelItemClickListener onTravelItemClickListener = OnTravelItemClickListener.NULL;


    public TravelAdapter() {
        travelViews = new LinkedList<>();
    }

    @Override
    public TravelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final TravelViewHolder holder = new TravelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_travel, parent, false));
        holder.itemView.setOnClickListener(v -> onTravelItemClickListener.onTravelClick(travelViews.get(holder.getAdapterPosition())));
        return holder;
    }

    @Override
    public void onBindViewHolder(TravelViewHolder holder, int position) {
        holder.bind(travelViews.get(position));
    }

    @Override
    public int getItemCount() {
        return travelViews.size();
    }

    public void setOnTravelItemClickListener(OnTravelItemClickListener listener) {
        this.onTravelItemClickListener = listener!=null ? listener : OnTravelItemClickListener.NULL;
    }

    public void clearAndAddall(List<TravelView> travelViews) {
        this.travelViews.clear();
        this.travelViews.addAll(travelViews);
    }


    public interface OnTravelItemClickListener {

        OnTravelItemClickListener NULL = travelView -> {/*Nic nie rob*/};
        void onTravelClick(TravelView travelView);
    }
}
