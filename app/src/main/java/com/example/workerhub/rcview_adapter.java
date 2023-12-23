package com.example.workerhub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class rcview_adapter extends FirebaseRecyclerAdapter<rcview_model,rcview_adapter.myviewholder>
{
    public rcview_adapter(@NonNull FirebaseRecyclerOptions<rcview_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull rcview_model model) {
        holder.fullname_cr.setText(model.getFullname());
        holder.phoneno_cr.setText(model.getPhono());

        holder.email_cr.setText(model.getEmail());
        holder.service_cr.setText(model.getServices());
        holder.pincode_cr.setText(model.getPincode());


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView fullname_cr,phoneno_cr,email_cr,service_cr,pincode_cr;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            service_cr = (TextView) itemView.findViewById(R.id.txt1);

            fullname_cr = (TextView) itemView.findViewById(R.id.txt2);
            phoneno_cr = (TextView) itemView.findViewById(R.id.txt3);
            email_cr = (TextView) itemView.findViewById(R.id.txt4);
            pincode_cr = (TextView) itemView.findViewById(R.id.txt5);
        }
    }

}
