package com.example.sergey.contactrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Sergey on 27.06.2016.
 */
public class DataManager extends RecyclerView.Adapter<DataManager.RecyclerViewHolder> {

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView mName, mPhone;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.CONTACT_name);
            mPhone = (TextView) itemView.findViewById(R.id.CONTACT_phone);
        }
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_item, viewGroup, false);
        return new RecyclerViewHolder(v);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int i) {

        final Contact contact = Contact.CONTACTS[i];
        viewHolder.mName.setText(contact.get(Contact.Field.NAME));
        viewHolder.mPhone.setText(contact.get(Contact.Field.PHONE));
    }

    @Override
    public int getItemCount() {
        return Contact.CONTACTS.length;
    }


}
