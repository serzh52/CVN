package com.example.sergey.contactrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    public final static String ID = "ID";
    public Contact mContact;
    public TextView mName, mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mContact = Contact.getItem(getIntent().getIntExtra(ID, 0));

        mName = (TextView) findViewById(R.id.DETAILS_name);
        mPhone = (TextView) findViewById(R.id.DETAILS_phone);

        mName.setText(mContact.get(Contact.Field.NAME));
        mPhone.setText(mContact.get(Contact.Field.PHONE));
    }
}
