package st.pawel.mobilnyprzewodnik.main.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.main.ui.model.MenuItemView;

public class MenuItemViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.menu_item_icon)
    ImageView icon;

    @Bind(R.id.menu_item_label)
    TextView label;

    public MenuItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(MenuItemView menuItemView){
        icon.setImageResource(menuItemView.iconRes());
        label.setText(menuItemView.labelRes());
    }
}
