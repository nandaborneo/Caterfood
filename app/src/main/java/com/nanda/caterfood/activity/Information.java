package com.nanda.caterfood.activity;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nanda.caterfood.R;

public class Information extends AppCompatActivity {

    public static String TAG_IMAGE_NAME = "tag_image_name";

    private ImageView mImage;
    private TextView mNameOfImage;
    private TextView mNameOfOwner;
    private TextView mDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        mImage = (ImageView) findViewById(R.id.detail_image);
        mNameOfImage = (TextView) findViewById(R.id.name_image);
        mNameOfOwner = (TextView) findViewById(R.id.name_owner);
        mDesc = (TextView) findViewById(R.id.desc);

        String image = getIntent().getStringExtra("image");
        String imageName = getIntent().getStringExtra("name-image");
        String ownerName = getIntent().getStringExtra("owner-name");
        String desc = getIntent().getStringExtra("desc");

        ViewCompat.setTransitionName(mImage, TAG_IMAGE_NAME);

        Glide.with(mImage.getContext()).load(Base64.decode(image,Base64.DEFAULT)).into(mImage);
        mNameOfImage.setText(imageName);
        mNameOfOwner.setText(ownerName);
        mDesc.setText(desc);
    }
}