package st.pawel.mobilnyprzewodnik.object.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.util.ImageLoaderProvider;
import st.pawel.mobilnyprzewodnik.object.ui.model.ObjectView;


public class ObjectViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.object_name)
    TextView objectName;

    @Bind(R.id.object_image)
    ImageView objecImage;

    @Bind(R.id.object_city_name)
    TextView objectCityName;

    @Bind(R.id.object_type)
    TextView objectType;

    @Bind(R.id.object_rating)
    TextView objectRating;

    public ObjectViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(ObjectView objectView) {

        //TODO dla wszystkich tymczasowo ten sam rysunek

        ImageLoaderProvider.newInstance(objecImage.getContext()).displayImage(objectView.objectImageUrl(), objecImage );
        objectName.setText(objectView.objectName());
        objectType.setText(objectView.objectType());
        objectCityName.setText(objectView.objectCityName());
        objectRating.setText(objectView.objectRate());
    }
}
