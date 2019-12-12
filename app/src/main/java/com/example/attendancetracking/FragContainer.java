package com.example.attendancetracking;

import android.app.LauncherActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class FragContainer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TimeTableUploader.TimeTableUploaderListner {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.draw_lay);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new TimeTableUploader())
                    .commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            default:
                break;
        }
        return false;
    }

    @Override
    public void setAdapterForListView(ListView lv, BaseAdapter ba) {
        ba = new EditTextListAdapter();
        lv.setAdapter(ba);
    }

    public class EditTextListAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;
        private ArrayList<ListItem> list;

        public EditTextListAdapter() {
            super();
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            list = new ArrayList<>();
            notifyDataSetChanged();
        }
        public void addMoreItemToList() {
            ListItem listItem = new ListItem();
            listItem.caption = "Subject";
            list.add(listItem);
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if(view == null) {
                holder = new ViewHolder();
                view = layoutInflater.inflate(R.layout.subject_collector_textbox, viewGroup);
                holder.caption = view.findViewById(R.id.textbox_id);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.caption.setText(list.get(i).caption);
            holder.caption.setId(i);

            holder.caption.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus) {
                        final int pos = v.getId();
                        final EditText cap = (EditText) v;
                        list.get(pos).caption = cap.getText().toString();
                    }
                }
            });

            return view;
        }
    }
    class ViewHolder {
        EditText caption;
    }
    class ListItem {
        String caption;
    }
}
