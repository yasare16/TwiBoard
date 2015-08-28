package com.example.twiboard;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

//import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
//import android.inputmethodservice.KeyboardView;
//import android.util.AttributeSet;
import android.view.inputmethod.InputMethodSubtype;

public class TwiboardView extends KeyboardView {
    static final int KEYCODE_OPTIONS = -100;
    // TODO: Move this into android.inputmethodservice.Keyboard
    static final int KEYCODE_LANGUAGE_SWITCH = -101;
    
    public TwiboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public TwiboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected boolean onLongPress(Key key) {
        if (key.codes[0] == Keyboard.KEYCODE_CANCEL) {
            getOnKeyboardActionListener().onKey(KEYCODE_OPTIONS, null);
            return true;
        } else {
            return super.onLongPress(key);
        }
    }
    void setSubtypeOnSpaceKey(final InputMethodSubtype subtype) {
        final TwiBoard keyboard = (TwiBoard)getKeyboard();
        //keyboard.setSpaceIcon(getResources().getDrawable(subtype.getIconResId()));
        invalidateAllKeys();
    }
}