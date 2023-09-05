package com.zcf.testapp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.MeasureSpec.UNSPECIFIED
import android.view.View.MeasureSpec.makeMeasureSpec
import androidx.appcompat.widget.AppCompatTextView

class AutoSizeTextView(
  context: Context,
  attrs: AttributeSet?
) : AppCompatTextView(context, attrs) {

  private var minTextSize: Float
  private var previousWidthMeasureSpec = -1

  init {
    context.obtainStyledAttributes(attrs, intArrayOf(androidx.appcompat.R.attr.autoSizeMinTextSize)).apply {
      val defaultMinTextSize = DEFAULT_MIN_TEXT_SIZE_SP * resources.displayMetrics.density
      minTextSize = px2sp(this.getDimension(0, defaultMinTextSize))
      recycle()
    }
  }

  /**
   * Try adjust text size in either onSizeChanged or onMeasure.
   * IN CASE view height doesn't update after fixTextSize, do this in onMeasure.
   */
  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    var textSizeUnit = px2sp(this.textSize)
    fixTextSize(w, h)
  }

  /**
   * Try adjust text size in either onSizeChanged or onMeasure,
   * According to the context, the check of widthMode might be different.
   */
  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    val widthMode = MeasureSpec.getMode(widthMeasureSpec)
    val heightMode = MeasureSpec.getMode(heightMeasureSpec)
    val widthSize = MeasureSpec.getSize(widthMeasureSpec)
    val heightSize = MeasureSpec.getSize(heightMeasureSpec)

    if (widthMeasureSpec != previousWidthMeasureSpec && widthMode == MeasureSpec.EXACTLY) {
      fixTextSize(widthSize, heightSize)
    }

    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
  }

  private fun fixTextSize(w: Int, h: Int) {
    var textSize = px2sp(this.textSize)
    this.measure(makeMeasureSpec(w, UNSPECIFIED), makeMeasureSpec(h, UNSPECIFIED))
    while (this.measuredWidth > w && textSize > minTextSize) {
      textSize -= 2
      this.textSize = textSize
      this.measure(makeMeasureSpec(w, UNSPECIFIED), makeMeasureSpec(h, UNSPECIFIED))
    }
  }

  companion object {
    const val DEFAULT_MIN_TEXT_SIZE_SP = 10f
  }
}

fun View.px2sp(size: Float) = size / resources.displayMetrics.density
