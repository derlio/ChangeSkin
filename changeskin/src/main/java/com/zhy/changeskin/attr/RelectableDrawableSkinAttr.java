package com.zhy.changeskin.attr;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.zhy.changeskin.SkinManager;

import java.lang.reflect.Method;

/**
 * Created by liuzhongde
 * On 2016/01/27 11:26
 */
public abstract class RelectableDrawableSkinAttr extends SkinAttr{

    private String propertyName;

    public RelectableDrawableSkinAttr(String propertyName, String resName){
        this.propertyName = propertyName;
        this.resName = resName;
    }

    public void apply(View view) {
        try {
            Method method = view.getClass().getDeclaredMethod(createSetMethodName(propertyName), Drawable.class);
            method.invoke(view, SkinManager.getInstance().getResourceManager().getDrawableByName(resName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createSetMethodName(String propertyName) {
        StringBuilder stringBuilder = new StringBuilder("set");
        stringBuilder.append(propertyName.substring(0, 1).toUpperCase());
        stringBuilder.append(propertyName.substring(1));
        return stringBuilder.toString();
    }

}
