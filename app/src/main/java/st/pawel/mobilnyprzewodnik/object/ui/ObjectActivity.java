package st.pawel.mobilnyprzewodnik.object.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.ui.BaseActivity;
import st.pawel.mobilnyprzewodnik.object.delegate.ObjectAddDelegate;
import st.pawel.mobilnyprzewodnik.object.model.ObjectModel;
import st.pawel.mobilnyprzewodnik.object.network.PostObjectRequest;
import st.pawel.mobilnyprzewodnik.object.ui.AddObjectFragment;

public class ObjectActivity extends BaseActivity implements ObjectAddDelegate{

    @Bind(R.id.object_toolbar)
    Toolbar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);
        ButterKnife.bind(this);
        prepareActionBar(actionBar);
        if(savedInstanceState != null){
            return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.object_container, AddObjectFragment.newInstance()).commit();
    }

    @Nullable
    @Override
    protected ActionBar prepareActionBar(Toolbar toolbar){
        final ActionBar actionBar = super.prepareActionBar(toolbar);
        actionBar.setDisplayHomeAsUpEnabled(true);
        return actionBar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void addObject(ObjectModel objectModel) {
        PostObjectRequest.instance().withObjectModel(objectModel).request().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    setResult(RESULT_OK);
                    finish();
                }
            }


            @Override
            public void onFailure(Throwable t) {
            }
        });

    }

    public static class IntentFactory {
        public static Intent forDisplay(Context context){
            return new Intent(context, ObjectActivity.class);
        }
    }
}