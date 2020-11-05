package com.example.chowdhuryarif.busrutedhaka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class  CustomAdapter extends BaseExpandableListAdapter {

    private Context context;
    List<String> listDataBusName;
    HashMap<String,List<String>> listDataBusFullRoute;


    public CustomAdapter(Context context, List<String> listDataBusName, HashMap<String, List<String>> listDataBusFullRoute) {
        this.context = context;
        this.listDataBusName = listDataBusName;
        this.listDataBusFullRoute = listDataBusFullRoute;
    }

    @Override
    public int getGroupCount() {
        return listDataBusFullRoute.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listDataBusFullRoute.get(listDataBusName.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataBusName.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listDataBusFullRoute.get(listDataBusName.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerText = (String) getGroup(i);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.group_layout,null);
        }

        TextView textView = view.findViewById(R.id.headerTextViewId);
        textView.setText(headerText);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String childText = (String) getChild(i,i1);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_layout,null);
        }

        TextView textView = view.findViewById(R.id.childTextViewId);
        textView.setText(childText);

        return view;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
