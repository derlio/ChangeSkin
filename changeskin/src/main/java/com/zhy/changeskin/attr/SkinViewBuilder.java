package com.zhy.changeskin.attr;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhongde
 * On 2016/01/26 14:20
 */
public class SkinViewBuilder {

    private Context mContext;
    private View mView;
    private List<SkinAttr> mSkinAttrs;

    public SkinViewBuilder(Context context, View view) {
        mContext = context;
        mView = view;
        mSkinAttrs = new ArrayList<>();
    }

    public SkinViewBuilder attr(SkinAttrType attrType, String resName){
        mSkinAttrs.add(new SkinAttr(attrType, resName));
        return this;
    }

    public SkinViewBuilder attr(SkinAttrType attrType, int resId){
        return attr(attrType, SkinAttrSupport.getResourceEntryName(mContext, resId));
    }

    public SkinView build(){
        return new SkinView(mView, mSkinAttrs);
    }
}
