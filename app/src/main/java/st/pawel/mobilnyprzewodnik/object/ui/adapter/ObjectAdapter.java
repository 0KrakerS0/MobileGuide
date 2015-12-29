package st.pawel.mobilnyprzewodnik.object.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.object.ui.model.ObjectView;
import st.pawel.mobilnyprzewodnik.object.ui.viewholder.ObjectViewHolder;

public class ObjectAdapter extends RecyclerView.Adapter<ObjectViewHolder> {

    List<ObjectView> objectViews;

    OnObjectItemClickListener onObjectItemClickListener = OnObjectItemClickListener.NULL;

    public ObjectAdapter() {
        objectViews = new LinkedList<>();

    }

    @Override
    public ObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ObjectViewHolder holder = new ObjectViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_object, parent, false));
        holder.itemView.setOnClickListener(v -> onObjectItemClickListener.onObjectClick(objectViews.get(holder.getAdapterPosition())));
        return holder;
    }

    @Override
    public void onBindViewHolder(ObjectViewHolder holder, int position) {
        holder.bind(objectViews.get(position));
    }

    @Override
    public int getItemCount() {
        return objectViews.size();
    }

    public void setNewListItems(List<ObjectView> objectViews){
        this.objectViews.clear();
        this.objectViews.addAll(objectViews);

    }

    public void setOnObjectItemClickListener(OnObjectItemClickListener listener){
        this.onObjectItemClickListener = listener != null ? listener : OnObjectItemClickListener.NULL;

    }
    public interface OnObjectItemClickListener{
        OnObjectItemClickListener NULL = objectView -> {/*Nic nie rob*/};
        void onObjectClick(ObjectView objectView);
    }
}
