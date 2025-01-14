package com.yydcdut.rxmarkdown

import android.content.Context
import android.util.AttributeSet
import com.yydcdut.markdown.MarkdownEditText

/**
 * Created by yuyidong on 2018/5/6.
 */
class RxMDEditText : MarkdownEditText {
    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}
