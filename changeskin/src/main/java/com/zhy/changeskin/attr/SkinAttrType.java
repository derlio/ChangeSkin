package com.zhy.changeskin.attr;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhy.changeskin.ResourceManager;
import com.zhy.changeskin.SkinManager;


/**
 * Created by zhy on 15/9/28.
 */
public enum SkinAttrType {
    BACKGROUD("background") {
        @Override
        public void apply(View view, String resName) {
            Drawable drawable = getResourceManager().getDrawableByName(resName);
            if (drawable == null) return;
            view.setBackgroundDrawable(drawable);
        }
    }, COLOR("textColor") {
        @Override
        public void apply(View view, String resName) {
            ColorStateList colorlist = getResourceManager().getColorStateList(resName);
            if (colorlist == null) return;
            ((TextView) view).setTextColor(colorlist);
        }
    }, SRC("src") {
        @Override
        public void apply(View view, String resName) {
            if (view instanceof ImageView) {
                Drawable drawable = getResourceManager().getDrawableByName(resName);
                if (drawable == null) return;
                ((ImageView) view).setImageDrawable(drawable);
            }

        }
    }, LIST_DIVIDER("divider") {
        @Override
        public void apply(View view, String resName) {
            if (view instanceof ListView) {
                Drawable drawable = getResourceManager().getDrawableByName(resName);
                if (drawable == null) return;
                ((ListView) view).setDivider(drawable);
                ((ListView) view).setDividerHeight(1);
            }
        }
    }, ALPHA("alpha") {
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void apply(View view, String resName) {
            float value = getResourceManager().getFloat(resName);
            if (value < 0 || value > 1) {
                return;
            }
            view.setAlpha(value);
        }
    }, LIST_SELECTOR("listSelector") {
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void apply(View view, String resName) {
            Drawable drawable = getResourceManager().getDrawableByName(resName);
            if (drawable == null) return;
            ((ListView) view).setSelector(drawable);
        }
    };

    String attrType;

    SkinAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrType() {
        return attrType;
    }


    public abstract void apply(View view, String resName);

    public ResourceManager getResourceManager() {
        return SkinManager.getInstance().getResourceManager();
    }

}
