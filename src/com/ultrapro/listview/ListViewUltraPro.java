package com.ultrapro.listview;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;

import java.util.ArrayList;

@DesignerComponent(
    version = 1,
    description = "ListView Ultra PRO MAX 2.0",
    category = ComponentCategory.USERINTERFACE,
    nonVisible = false,
    iconName = ""
)

@SimpleObject(external = true)
public class ListViewUltraPro extends AndroidViewComponent {

    private Context context;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items;

    public ListViewUltraPro(ComponentContainer container) {
        super(container);
        context = container.$context();

        listView = new ListView(context);
        items = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                items
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                ItemClick(items.get(position), position + 1);
            }
        });

        container.$add(this);
    }

    @Override
    public android.view.View getView() {
        return listView;
    }

    @SimpleFunction(description = "Add item to list")
    public void AddItem(String text) {
        items.add(text);
        adapter.notifyDataSetChanged();
    }

    @SimpleFunction(description = "Clear list")
    public void Clear() {
        items.clear();
        adapter.notifyDataSetChanged();
    }

    @SimpleEvent(description = "Triggered when item is clicked")
    public void ItemClick(String text, int index) {
        EventDispatcher.dispatchEvent(this, "ItemClick", text, index);
    }
}
