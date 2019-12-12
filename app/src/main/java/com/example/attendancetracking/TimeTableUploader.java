package com.example.attendancetracking;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class TimeTableUploader extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TimeTableUploaderListner mListener;

    public TimeTableUploader() {

    }

    public static TimeTableUploader newInstance(String param1, String param2) {
        TimeTableUploader fragment = new TimeTableUploader();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private ListView listView;
    private FragContainer.EditTextListAdapter listAdapter;
    private Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_upload_timetable, container, true);

        listView = v.findViewById(R.id.subject_collector);
        listView.setItemsCanFocus(true);

        spinner = v.findViewById(R.id.week_selector);
        mListener.setAdapterForListView(listView, listAdapter);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TimeTableUploaderListner) {
            mListener = (TimeTableUploaderListner) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface TimeTableUploaderListner {
        void setAdapterForListView(ListView lv, BaseAdapter ba);
    }
}
