package com.yydcdut.rxmarkdown

import android.content.Context
import android.util.AttributeSet
import com.yydcdut.markdown.MarkdownTextView

/**
 * Created by yuyidong on 2018/5/6.
 */
class RxMDTextView : MarkdownTextView {
    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}
