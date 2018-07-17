package com.nanda.caterfood.adapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nanda.caterfood.activity.MainActivity;
import com.nanda.caterfood.model.MenuModel;
import com.nanda.caterfood.activity.Information;
import com.nanda.caterfood.R;

import java.util.ArrayList;
import java.util.List;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

/**
 * Created by putuguna on 02/06/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private ArrayList<MenuModel> mListImage;
    private Context mContext;


    public ImageAdapter(ArrayList<MenuModel> mListImage, Context mContext) {
        this.mListImage = mListImage;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(holder.mGambar.getContext()).load(Base64.decode(mListImage.get(position).getmGambar(),Base64.DEFAULT)).into(holder.mGambar);
        holder.mNamaMenu.setText(mListImage.get(position).getmNamaMenu());
        holder.mDesk.setText((mListImage.get(position).getmDesk()).substring(0,25)+"...");
        int hrg = Integer.parseInt(mListImage.get(position).getmHarga())/1000;
        String harga = holder.mAddorder.getText()+" "+hrg+"K";
        holder.mAddorder.setText(harga);
    }

    @Override
    public int getItemCount() {
        return mListImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView mGambar;
        public TextView mNamaMenu;
        public TextView mDesk;
        public Button mAddorder;
        public Button mMoreinfo;
        public FloatingTextButton fab;
        public int total=0;

        public ViewHolder(View itemView) {
            super(itemView);
            mGambar = (ImageView) itemView.findViewById(R.id.imagedetail);
            mNamaMenu = (TextView) itemView.findViewById(R.id.nama_makanan);
            mDesk = (TextView) itemView.findViewById(R.id.deskripsi);
            mAddorder = (Button) itemView.findViewById(R.id.addorder);
            mMoreinfo = (Button) itemView.findViewById(R.id.moreinfo);

            mMoreinfo.setOnClickListener(this);
            mAddorder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (v==mMoreinfo) {

                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                ((Activity) mContext), mGambar, Information.TAG_IMAGE_NAME);
                Intent intent = new Intent(mContext, Information.class);
                intent.putExtra("image", (mListImage.get(position).getmGambar()));
                intent.putExtra("name-image", mListImage.get(position).getmNamaMenu());
                intent.putExtra("owner-name", mListImage.get(position).getmDesk());
                intent.putExtra("desc", mListImage.get(position).getmHarga());
                ActivityCompat.startActivity(((Activity) mContext), intent, options.toBundle());
            }else if (v==mAddorder){
                total = Integer.parseInt(mListImage.get(position).getmHarga());
//                String title = fab.getTitle();
                ((MainActivity)mContext).Totalan(total,mListImage.get(position).getmNamaMenu());

            }
        }
    }
}