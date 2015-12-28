package st.pawel.mobilnyprzewodnik.main.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.ui.DelegateBaseFragment;
import st.pawel.mobilnyprzewodnik.main.delegate.MenuFragmentDelegate;
import st.pawel.mobilnyprzewodnik.main.model.MainMenu;
import st.pawel.mobilnyprzewodnik.main.ui.adapter.MenuAdapter;
import st.pawel.mobilnyprzewodnik.main.ui.model.MenuItemView;

public class MenuFragment extends DelegateBaseFragment<MenuFragmentDelegate> {

    @Bind(R.id.main_menu_list)
    RecyclerView mainMenuList;


    public static MenuFragment newInstance() {
        MenuFragment menuFragment = new MenuFragment();
        return menuFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareMenuList();
    }

    private void prepareMenuList() {
        mainMenuList.setLayoutManager(new LinearLayoutManager(getContext()));
        MenuAdapter adapter = new MenuAdapter();
        MainMenu[] mainMenus = MainMenu.values();
        for(MainMenu mainMenu : mainMenus){
            adapter.addMenuItem(mainMenu);
        }
        mainMenuList.setAdapter(adapter);
        adapter.setOnMenuItemClick(delegate::onMenuItemClick);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected boolean instanceOfDelegate(Context context) {
        return context instanceof MenuFragmentDelegate;
    }

    @Override
    protected String delegateClassName() {
        return MenuFragmentDelegate.class.getSimpleName();
    }
}
