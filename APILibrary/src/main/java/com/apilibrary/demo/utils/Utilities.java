package com.apilibrary.demo.utils;

import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.apilibrary.demo.base.mvp.BaseActivity;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

import timber.log.Timber;

public class Utilities {

    public static StringBuilder getStringFromErrorBody(byte[] bytes) {
        Charset charset = StandardCharsets.UTF_8;
        CharsetDecoder decoder = charset.newDecoder();
        ByteBuffer srcBuffer = ByteBuffer.wrap(bytes);
        CharBuffer resBuffer = null;
        try {
            resBuffer = decoder.decode(srcBuffer);
        } catch (CharacterCodingException e) {
            Timber.d("Exception : " + e.toString());
        }
        return new StringBuilder(resBuffer);
    }

    public static void setWindowFlag(Context context, final int bits, boolean on) {
        if (context != null && context instanceof BaseActivity) {
            Window win = ((BaseActivity) context).getWindow();

            WindowManager.LayoutParams winParams = win.getAttributes();
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }
}
