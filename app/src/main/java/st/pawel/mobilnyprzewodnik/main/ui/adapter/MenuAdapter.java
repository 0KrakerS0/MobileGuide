package st.pawel.mobilnyprzewodnik.main.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import lombok.Setter;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.main.ui.model.MenuItemView;
import st.pawel.mobilnyprzewodnik.main.ui.model.UserView;
import st.pawel.mobilnyprzewodnik.main.ui.viewholder.HeaderViewHolder;
import st.pawel.mobilnyprzewodnik.main.ui.viewholder.MenuItemViewHolder;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int HEADER_MENU_ITEM = 0;
    private static final int STANDARD_MENU_ITEM = 1;

    OnMenuItemClick onMenuItemClick = OnMenuItemClick.NULL;

    @Setter
    UserView userView;

    List<MenuItemView> menuItemViews;

    public MenuAdapter(){
        menuItemViews = new LinkedList<>();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case HEADER_MENU_ITEM:
                return prepareHeaderItemViewHolder(parent);
            case STANDARD_MENU_ITEM :
                return prepareMenuItemViewHolder(parent);
            default:
                throw new IllegalArgumentException("Holder dla typu widoku " + viewType + " nie jest wspierany");
        }
    }

    private HeaderViewHolder prepareHeaderItemViewHolder(ViewGroup parent){
        return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user_menu_header, parent, false));
    }

    private MenuItemViewHolder prepareMenuItemViewHolder(ViewGroup parent){
        final MenuItemViewHolder holder = new MenuItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_menu_item, parent, false));
        holder.itemView.setOnClickListener(v -> onMenuItemClick.onMenuItemClick(menuItemViews.get(getFixedAdapterPosition(holder))));
        return holder;
    }

    private int getFixedAdapterPosition(RecyclerView.ViewHolder holder) {
        return holder.getAdapterPosition() - (userView != null ? 1 :0);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case HEADER_MENU_ITEM:
                onBindHeaderViewHolder((HeaderViewHolder) holder);
                return;
            case STANDARD_MENU_ITEM:
                onBindMenuItemViewHolder((MenuItemViewHolder) holder, position);
                return;
            default:
                throw new IllegalArgumentException("Holder dla pozycji " + position + " nie jest wspierany");
        }
    }

    void onBindMenuItemViewHolder(MenuItemViewHolder holder, int position){
        final int fixPosition = position - (userView != null ? 1 : 0);
        holder.bind(menuItemViews.get(fixPosition));
    }

    void onBindHeaderViewHolder(HeaderViewHolder holder){
        holder.bind(userView);
    }

    public void addMenuItem(MenuItemView menuItemView){
        menuItemViews.add(menuItemView);
    }

    public void addMenuItem(List<MenuItemView> menuItemViews){
        this.menuItemViews.addAll(menuItemViews);
    }

    public void clearAdapter(){
        menuItemViews.clear();
    }

    @Override
    public int getItemCount() {
        return menuItemViews.size() + (userView != null ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0 && userView != null){
            return HEADER_MENU_ITEM;
        }
        return STANDARD_MENU_ITEM;
    }

    public void setOnMenuItemClick(OnMenuItemClick listener) {
        this.onMenuItemClick = listener == null ? OnMenuItemClick.NULL : listener;
    }

    public interface OnMenuItemClick{
        OnMenuItemClick NULL = menuItemView -> {/*Nic nie robi*/};

        void onMenuItemClick(MenuItemView menuItemView);
    }
}
