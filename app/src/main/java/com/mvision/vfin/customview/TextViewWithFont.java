package com.mvision.vfin.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.mvision.vfin.R;


/**
 * Created by kosian on 8/29/2016.
 */
public class TextViewWithFont extends android.support.v7.widget.AppCompatTextView {
    private int defaultDimension = 0;
    private int TYPE_BOLD = 1;
    private int TYPE_ITALIC = 2;
    private int FONT_ARUNDINA = 1;
    private int FONT_KANIT = 2;
    private int FONT_KANIT_REGULAR = 3;
    private int FONT_ROBOTO = 3;
    private int fontType;
    private int fontName;
    private Typeface Kanit;

    public TextViewWithFont(Context context) {
        super(context);
        init(context, null, 0);
    }

    public TextViewWithFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public TextViewWithFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        getFont(context);
        try {
            // Load attributes
            final TypedArray a = getContext().obtainStyledAttributes(
                    attrs, R.styleable.font, defStyle, 0);
            fontName = a.getInt(R.styleable.font_name, defaultDimension);
            fontType = a.getInt(R.styleable.font_type, defaultDimension);
            a.recycle();
            setFontType(Kanit);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void setFontType(Typeface font) {
        if (fontType == TYPE_BOLD) {
            setTypeface(font, Typeface.BOLD);
        } else if (fontType == TYPE_ITALIC) {
            setTypeface(font, Typeface.ITALIC);
        } else {
            setTypeface(font);
        }
    }


    private void getFont(Context context) {
        Kanit = Typeface.createFromAsset(context.getAssets(), "fonts/Kanit-Light.otf");
    }


    public String getTextDataNotNull(String msg) {

        if (getText().toString().isEmpty()) {
            if (msg == null) {
                setError("กรุณากรอกข้อมูลให้ครบ");
            } else {
                setError(msg);
            }

            return "";
        }

        return getText().toString();
    }
}