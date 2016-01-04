package st.pawel.mobilnyprzewodnik.object.ui.viewholder;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.ui.BaseFragment;

public class AddObjectFragment extends BaseFragment {

    @Bind(R.id.add_object_name)
    EditText objectName;

    @Bind(R.id.add_object_type)
    EditText objectType;

    @Bind(R.id.add_object_rate)
    RatingBar objectRate;

    public static AddObjectFragment newInstance() {
        AddObjectFragment fragment = new AddObjectFragment();
        return fragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View result = inflater.inflate(R.layout.fragment_add_object, container, false);
        ButterKnife.bind(this, result);
        return  result;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_accept, menu);
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.action_accept){
            //TODO dodanie objectModel i poszczegolnych zmiennych
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }
}
