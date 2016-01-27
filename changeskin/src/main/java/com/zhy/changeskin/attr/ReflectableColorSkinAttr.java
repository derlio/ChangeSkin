package com.zhy.changeskin.attr;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 *
 */
public class ReflectableColorSkinAttr extends SkinAttr{

    private String propertyName;

    public ReflectableColorSkinAttr(String propertyName, SkinAttrType attrType, String resName){
        this.propertyName = propertyName;
        this.attrType = attrType;
        this.resName = resName;
    }

    public void apply(View view) {
       view.getClass().getDeclaredMethod();
        ObjectAnimator.ofObject()
    }

    private String createSetMethodName(String propertyName) {
        StringBuilder stringBuilder = new StringBuilder("set");
        stringBuilder.append(propertyName.substring(0, 1).toUpperCase());
        stringBuilder.append(propertyName.substring(1));
        return stringBuilder.toString();
    }
}
