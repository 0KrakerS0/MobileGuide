package st.pawel.mobilnyprzewodnik.main.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.main.ui.model.MenuItemView;
import st.pawel.mobilnyprzewodnik.main.ui.viewholder.MenuItemViewHolder;

public class MenuAdapter extends RecyclerView.Adapter<MenuItemViewHolder>{

    List<MenuItemView> menuItemViews;

    public MenuAdapter(){
        menuItemViews = new LinkedList<>();
    }
    @Override
    public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MenuItemViewHolder holder = new MenuItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_menu_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MenuItemViewHolder holder, int position) {
        holder.bind(menuItemViews.get(position));
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
        return menuItemViews.size();
    }

    public interface OnMenuItemClick{

        void onMenuItemClick(MenuItemView menuItemView);
    }
}
