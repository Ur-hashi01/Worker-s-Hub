package com.example.workerhub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<CategoryModel>
{

    public CategoryAdapter(@NonNull Context context, ArrayList<CategoryModel> CatergoryList) {
        super(context, 0, CatergoryList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        view = LayoutInflater.from(getContext()).inflate(R.layout.row_items, parent, false);

        CategoryModel cat = getItem(position);
        TextView cat_name = view.findViewById(R.id.cat_name);
        ImageView cat_img = view.findViewById(R.id.cat_img);

        cat_name.setText(cat.getCat_name());
        cat_img.setImageResource(cat.getImg_id());

        return view;
    }

}
