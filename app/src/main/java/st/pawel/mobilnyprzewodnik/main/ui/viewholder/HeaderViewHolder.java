package st.pawel.mobilnyprzewodnik.main.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.util.ImageLoaderProvider;
import st.pawel.mobilnyprzewodnik.main.ui.model.UserView;


public class HeaderViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.menu_header_user_image)
    ImageView userImage;
    @Bind(R.id.menu_header_user_name)
    TextView userName;
    @Bind(R.id.menu_header_user_email)
    TextView userEmail;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(UserView userView) {
        ImageLoaderProvider.newInstance(userImage.getContext())
                .displayImage(userView.userImageUrl(), userImage, ImageLoaderProvider.provideCircleDisplayImageOptions());
        userName.setText(userView.userFullName());
        userEmail.setText(userView.userEmail());
    }
}
