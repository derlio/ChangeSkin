package com.zhy.changeskin;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;

import com.zhy.changeskin.attr.SkinAttrSupport;
import com.zhy.changeskin.utils.L;

/**
 * Created by zhy on 15/9/22.
 */
public class ResourceManager {
    private static final String DEFTYPE_DRAWABLE = "drawable";
    private static final String DEFTYPE_COLOR = "color";
    private Resources mPluginResources;
    private String mPluginPackageName;
    private String mSuffix;

    private Resources mHostResource;
    private String mHostPackageName;

    public ResourceManager(Context context, Resources pluginResource, String pluginPackageName, String suffix) {
        mHostPackageName = context.getPackageName();
        mHostResource = context.getResources();

        mPluginResources = pluginResource;
        mPluginPackageName = pluginPackageName;

        if (suffix == null) {
            suffix = "";
        }
        mSuffix = suffix;

    }

    public Drawable getDrawableByName(String name) {
        try {
            name = appendSuffix(name);
            L.e("name = " + name);
            return mPluginResources.getDrawable(mPluginResources.getIdentifier(name, DEFTYPE_DRAWABLE, mPluginPackageName));
        } catch (Resources.NotFoundException e) {
            try {
                return mPluginResources.getDrawable(mPluginResources.getIdentifier(name, DEFTYPE_COLOR, mPluginPackageName));
            } catch (Resources.NotFoundException e2) {
                try {
                    return mHostResource.getDrawable(mHostResource.getIdentifier(name, DEFTYPE_DRAWABLE, mHostPackageName));
                } catch (Resources.NotFoundException e3) {
                    return mHostResource.getDrawable(mHostResource.getIdentifier(name, DEFTYPE_COLOR, mHostPackageName));
                }
            }
        }
    }

    public Drawable getDrawableByResId(Context context, int id) {
        return getDrawableByName(SkinAttrSupport.getResourceEntryName(context, id));
    }

    public int getColor(String name) {
        try {
            name = appendSuffix(name);
            L.e("name = " + name);
            return mPluginResources.getColor(mPluginResources.getIdentifier(name, DEFTYPE_COLOR, mPluginPackageName));
        } catch (Resources.NotFoundException e) {
            return mHostResource.getColor(mHostResource.getIdentifier(name, DEFTYPE_COLOR, mHostPackageName));
        }

    }

    public int getColorByResId(Context context, int id) {
        return getColor(SkinAttrSupport.getResourceEntryName(context, id));
    }

    public ColorStateList getColorStateList(String name) {
        try {
            name = appendSuffix(name);
            L.e("name = " + name);
            return mPluginResources.getColorStateList(mPluginResources.getIdentifier(name, DEFTYPE_COLOR, mPluginPackageName));
        } catch (Resources.NotFoundException e) {
            try {
                return mPluginResources.getColorStateList(mPluginResources.getIdentifier(name, DEFTYPE_DRAWABLE, mPluginPackageName));
            } catch (Resources.NotFoundException e2) {
                try{
                    return mHostResource.getColorStateList(mHostResource.getIdentifier(name, DEFTYPE_COLOR, mHostPackageName));
                }catch (Resources.NotFoundException e3){
                    return mHostResource.getColorStateList(mHostResource.getIdentifier(name, DEFTYPE_DRAWABLE, mHostPackageName));
                }
            }
        }

    }

    public ColorStateList getColorStateListByResId(Context context, int id) {
        return getColorStateList(SkinAttrSupport.getResourceEntryName(context, id));
    }

    public float getFloat(String name){
        TypedValue value = new TypedValue();
        mPluginResources.getValue(mPluginResources.getIdentifier(name, "integer", mPluginPackageName), value, true);
        if (value.type == TypedValue.TYPE_NULL) {
            mHostResource.getValue(mHostResource.getIdentifier(name, "integer", mHostPackageName), value, true);
        }
        if (value.type == TypedValue.TYPE_NULL) {
            return -1;
        }
        return value.getFloat();
    }

    private String appendSuffix(String name) {
        if (!TextUtils.isEmpty(mSuffix))
            return name += "_" + mSuffix;
        return name;
    }

}
