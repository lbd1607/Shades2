package com.missouristate.davis916.shades2;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Laura Davis CIS 262-902
 * 8 March 2018
 * This class is responsible for inflating the list fragment
 * and assisting with the transition between this fragment and
 * the information fragment.
 */

public class ListFragment extends Fragment{
    private OnItemSelectedListener listener;
    List<String> shadelisting;              //Shade list
    List<String> shadeNameDetail;           //Master detail

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        shadelisting = new ArrayList<String>(Arrays.asList(DummyData.shade_name));
        shadeNameDetail = new ArrayList<String>(Arrays.asList(DummyData.shade_detail));

        final ArrayAdapter<String> mShadeAdapter = new ArrayAdapter<String>
                (getActivity(), R.layout.list_item_shade,
                        R.id.list_item_shade_textview, shadelisting);

        View rootView = inflater.inflate(R.layout.list_fragment,
                container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_shades);
        listView.setAdapter(mShadeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String shadeIndexString = mShadeAdapter.getItem(i);
                String information = shadeIndexString + "\n\n\n" +
                        shadeNameDetail.get(i);
                updateDetail(information);
            }//end onItemClick
        });
        return rootView;
    }//end onCreateView()

    public interface OnItemSelectedListener {
        void onShadeItemSelected (String link);
    }//end interface


    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        if(activity instanceof OnItemSelectedListener){
            listener = (OnItemSelectedListener) activity;
        }else {
            throw new ClassCastException(activity.toString() +
                    "must implement MyListFragment.OnItemSelectedListener");
        }
    }//end onAttach()

    public void updateDetail(String information){
        listener.onShadeItemSelected(information);
    }//end updateDetail()

}//end MyListFragment class
