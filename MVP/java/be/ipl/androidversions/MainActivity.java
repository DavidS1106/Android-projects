package be.ipl.androidversions;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AndroidVersionModel model = new AndroidVersionModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView versionList = findViewById(R.id.rvAndroidVersionList);

        versionList.setAdapter(new SimpleItemRecyclerViewAdapter(this, model.getVersionsList()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final MainActivity mParentActivity;
        private final List<AndroidVersion> mValues;

    

        SimpleItemRecyclerViewAdapter(MainActivity parent,
                                      List<AndroidVersion> items) {
            mValues = items;
            mParentActivity = parent;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_android_version_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.tvVersionName.setText(mValues.get(position).getName());
            holder.tvVersionNum.setText(mValues.get(position).getVersion());
            holder.ivVersionImage.setImageResource(mValues.get(position).getImageId());
            holder.itemView.setTag(mValues.get(position));
           
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView tvVersionNum;
            final TextView tvVersionName;
            final ImageView ivVersionImage;

            ViewHolder(View view) {
                super(view);
               tvVersionName = view.findViewById(R.id.text_view_android_version_name);
               tvVersionNum = view.findViewById(R.id.text_view_android_version_number);
               ivVersionImage = view.findViewById(R.id.image_view_android_version_icon);
            }
        }
    }

}

