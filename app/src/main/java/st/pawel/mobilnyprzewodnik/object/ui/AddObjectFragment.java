package st.pawel.mobilnyprzewodnik.object.ui;


import android.content.Context;
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

import java.security.SecureRandom;

import butterknife.Bind;
import butterknife.ButterKnife;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.ui.BaseFragment;
import st.pawel.mobilnyprzewodnik.common.ui.DelegateBaseFragment;
import st.pawel.mobilnyprzewodnik.object.delegate.ObjectAddDelegate;
import st.pawel.mobilnyprzewodnik.object.model.ObjectModel;

public class AddObjectFragment extends DelegateBaseFragment<ObjectAddDelegate> {

    @Bind(R.id.add_object_name)
    EditText objectName;

    @Bind(R.id.add_object_city_name)
    EditText objectCityName;

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
            final ObjectModel objectModel = new ObjectModel();
            objectModel.setObjectName(objectName.getText().toString());
            objectModel.setObjectImageUrl("http://lorempixel.com/200/400/city/" + new SecureRandom().nextInt(10) + "/");
            objectModel.setObjectType(objectType.getText().toString());
            objectModel.setObjectCityName(objectCityName.getText().toString());
            objectModel.setObjectRate(String.valueOf(objectRate.getRating()));
            delegate.addObject(objectModel);
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean instanceOfDelegate(Context context) {
        return context instanceof  ObjectAddDelegate;
    }

    @Override
    protected String delegateClassName() {
        return ObjectAddDelegate.class.getSimpleName();
    }
}
