package com.zhy.changeskin.callback;

/**
 * Created by zhy on 15/9/22.
 */
public interface ISkinChangingCallback {
    public static DefaultSkinChangingCallback DEFAULT_SKIN_CHANGING_CALLBACK = new DefaultSkinChangingCallback();

    void onStart();

    void onError(Exception e);

    void onComplete();

    public class DefaultSkinChangingCallback implements ISkinChangingCallback {
        @Override
        public void onStart() {

        }

        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onComplete() {

        }
    }

}
