package com.tanuja.agribuisness;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class ImageAdapter2 extends PagerAdapter {


    Context mContext;

    ImageAdapter2(Context context) {
        this.mContext = context;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    private int[] sliderImageId = new int[]{
            R.drawable.l1, R.drawable.l2, R.drawable.l3,R.drawable.loginmain, R.drawable.login,
    };

    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(sliderImageId[position]);
        ((ViewPager2) container).addView(imageView, 0);
        return imageView;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager2) container).removeView((ImageView) object);
    }

    public int getCount() {
        return sliderImageId.length;
    }
}
