package com.zhy.changeskin.attr;

import android.view.View;

import com.zhy.changeskin.SkinManager;

import java.lang.reflect.Method;

/**
 *
 */
public class ReflectableColorSkinAttr extends SkinAttr{

    private String propertyName;

    public ReflectableColorSkinAttr(String propertyName, String resName){
        this.propertyName = propertyName;
        this.resName = resName;
    }

    public void apply(View view) {
        try {
            Method method = view.getClass().getDeclaredMethod(createSetMethodName(propertyName), int.class);
            method.invoke(view, SkinManager.getInstance().getResourceManager().getColor(resName));
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
