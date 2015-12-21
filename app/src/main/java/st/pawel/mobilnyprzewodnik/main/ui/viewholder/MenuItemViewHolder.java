package st.pawel.mobilnyprzewodnik.main.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.main.ui.model.MenuItemView;

public class MenuItemViewHolder extends RecyclerView.ViewHolder {

    ImageView icon;

    TextView label;

    public MenuItemViewHolder(View itemView) {
        super(itemView);

        icon = (ImageView) itemView.findViewById(R.id.menu_item_icon);
        label = (TextView) itemView.findViewById(R.id.menu_item_label);
    }

    public void bind(MenuItemView menuItemView){
        icon.setImageResource(menuItemView.iconRes());
        label.setText(menuItemView.labelRes());
    }
}
