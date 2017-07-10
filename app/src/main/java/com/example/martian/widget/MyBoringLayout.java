package com.example.martian.widget;

import android.text.BoringLayout;
import android.text.TextPaint;
import android.text.TextUtils;

/**
 * @author martian on 2017/4/18.
 */

public class MyBoringLayout extends BoringLayout {

  public MyBoringLayout(CharSequence source, TextPaint paint, int outerwidth,
      Alignment align, float spacingmult, float spacingadd, Metrics metrics, boolean includepad) {
    super(source, paint, outerwidth, align, spacingmult, spacingadd, metrics, includepad);
  }

  public MyBoringLayout(CharSequence source, TextPaint paint, int outerwidth,
      Alignment align, float spacingmult, float spacingadd, Metrics metrics, boolean includepad,
      TextUtils.TruncateAt ellipsize, int ellipsizedWidth) {
    super(source, paint, outerwidth, align, spacingmult, spacingadd, metrics, includepad, ellipsize,
        ellipsizedWidth);
  }
}
