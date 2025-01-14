package com.yydcdut.rxmarkdown

import android.content.Context
import androidx.annotation.ColorInt
import com.yydcdut.markdown.MarkdownConfiguration
import com.yydcdut.markdown.callback.OnLinkClickCallback
import com.yydcdut.markdown.callback.OnTodoClickCallback
import com.yydcdut.markdown.config.BlockQuote
import com.yydcdut.markdown.config.Code
import com.yydcdut.markdown.config.Header
import com.yydcdut.markdown.config.HorizontalRule
import com.yydcdut.markdown.config.Image
import com.yydcdut.markdown.config.Link
import com.yydcdut.markdown.config.Todo
import com.yydcdut.markdown.config.UnOrderList
import com.yydcdut.markdown.loader.MDImageLoader
import com.yydcdut.markdown.theme.Theme
import com.yydcdut.markdown.theme.ThemeDefault

/**
 * Created by yuyidong on 2018/5/6.
 */
class RxMDConfiguration
/**
 * Constructor
 *
 * @param header         Header size style
 * @param blockQuote     block quote style
 * @param horizontalRule horizontal rule style
 * @param code           code style
 * @param theme          code block theme
 * @param todo
 * @param unOrderList    unorder list style
 * @param link           link style
 * @param image          image style
 */
private constructor(
    header: Header,
    blockQuote: BlockQuote,
    horizontalRule: HorizontalRule,
    code: Code,
    theme: Theme,
    todo: Todo,
    unOrderList: UnOrderList,
    link: Link,
    image: Image
) : MarkdownConfiguration(
    header,
    blockQuote,
    horizontalRule,
    code,
    theme,
    todo,
    unOrderList,
    link,
    image
) {
    class Builder(context: Context) {
        private val header = Header()
        private val blockQuote = BlockQuote()
        private val horizontalRule = HorizontalRule()
        private val code = Code()
        private var theme: Theme
        private val todo: Todo
        private val unOrderList: UnOrderList
        private val link: Link
        private val image: Image

        /**
         * Constructor
         *
         * @param context Context
         */
        init {
            theme = ThemeDefault()
            todo = Todo()
            unOrderList = UnOrderList()
            link = Link()
            image = Image(context)
        }

        /**
         * set header h1 relative size
         *
         * @param header1RelativeSize relative to current text size
         * @return self
         */
        fun setHeader1RelativeSize(header1RelativeSize: Float): Builder {
            header.h1 = header1RelativeSize
            return this
        }

        /**
         * set header h2 relative size
         *
         * @param header2RelativeSize relative to current text size
         * @return self
         */
        fun setHeader2RelativeSize(header2RelativeSize: Float): Builder {
            header.h2 = header2RelativeSize
            return this
        }

        /**
         * set header h3 relative size
         *
         * @param header3RelativeSize relative to current text size
         * @return self
         */
        fun setHeader3RelativeSize(header3RelativeSize: Float): Builder {
            header.h3 = header3RelativeSize
            return this
        }

        /**
         * set header h4 relative size
         *
         * @param header4RelativeSize relative to current text size
         * @return self
         */
        fun setHeader4RelativeSize(header4RelativeSize: Float): Builder {
            header.h4 = header4RelativeSize
            return this
        }

        /**
         * set header h5 relative size
         *
         * @param header5RelativeSize relative to current text size
         * @return self
         */
        fun setHeader5RelativeSize(header5RelativeSize: Float): Builder {
            header.h5 = header5RelativeSize
            return this
        }

        /**
         * set header h6 relative size
         *
         * @param header6RelativeSize relative to current text size
         * @return self
         */
        fun setHeader6RelativeSize(header6RelativeSize: Float): Builder {
            header.h6 = header6RelativeSize
            return this
        }

        /**
         * set block quote line color
         *
         * @param lineColor the color
         * @return self
         */
        fun setBlockQuotesLineColor(@ColorInt lineColor: Int): Builder {
            blockQuote.lineColor = lineColor
            return this
        }

        /**
         * set the block quote background color (including nest background color)
         *
         * @param bgColor     the background color
         * @param nestBgColor the nest background color
         * @return self
         */
        fun setBlockQuotesBgColor(bgColor: Int, vararg nestBgColor: Int): Builder {
            blockQuote.bgColorList[0] = bgColor
            if (nestBgColor != null && nestBgColor.size > 0) {
                val count = nestBgColor.size
                for (i in 0 until count) {
                    blockQuote.bgColorList.add(nestBgColor[i])
                }
            }
            return this
        }

        /**
         * set the block quote relative size compared to standard size
         *
         * @param blockQuotesRelativeSize the size
         * @return self
         */
        fun setBlockQuotesRelativeSize(blockQuotesRelativeSize: Float): Builder {
            blockQuote.size = blockQuotesRelativeSize
            return this
        }

        /**
         * set horizontal rules color
         *
         * @param horizontalRulesColor the color
         * @return self
         */
        fun setHorizontalRulesColor(@ColorInt horizontalRulesColor: Int): Builder {
            horizontalRule.color = horizontalRulesColor
            return this
        }

        /**
         * set horizontal rules height
         *
         * @param horizontalRulesHeight horizontal rules height
         * @return self
         */
        fun setHorizontalRulesHeight(horizontalRulesHeight: Int): Builder {
            horizontalRule.height = horizontalRulesHeight
            return this
        }

        /**
         * set code background color
         *
         * @param fontColor the color
         * @return self
         */
        fun setCodeFontColor(@ColorInt fontColor: Int): Builder {
            code.color = fontColor
            return this
        }

        /**
         * set inline code background color
         *
         * @param codeBgColor the color
         * @return self
         */
        fun setCodeBgColor(@ColorInt codeBgColor: Int): Builder {
            code.bgColor = codeBgColor
            return this
        }

        /**
         * set code theme
         *
         * @param theme the code style
         * @return self
         */
        fun setTheme(theme: Theme): Builder {
            this.theme = theme
            return this
        }

        /**
         * set to do color
         *
         * @param todoColor the color
         * @return self
         */
        fun setTodoColor(@ColorInt todoColor: Int): Builder {
            todo.todoColor = todoColor
            return this
        }

        /**
         * set done color
         *
         * @param todoDoneColor the color
         * @return self
         */
        fun setTodoDoneColor(@ColorInt todoDoneColor: Int): Builder {
            todo.doneColor = todoDoneColor
            return this
        }

        /**
         * set _todo(done) click callback
         *
         * @param onTodoClickCallback OnTodoClickCallback, invoked when clicking _todo or done syntax
         * @return self
         */
        fun setOnTodoClickCallback(onTodoClickCallback: OnTodoClickCallback?): Builder {
            todo.onTodoClickCallback = onTodoClickCallback
            return this
        }

        /**
         * set unorder list point color
         *
         * @param unOrderListColor the color
         * @return self
         */
        fun setUnOrderListColor(unOrderListColor: Int): Builder {
            unOrderList.color = unOrderListColor
            return this
        }

        /**
         * set link font color
         *
         * @param linkFontColor the color
         * @return self
         */
        fun setLinkFontColor(linkFontColor: Int): Builder {
            link.color = linkFontColor
            return this
        }

        /**
         * is link underline
         *
         * @param show boolean, whether show link underline
         * @return self
         */
        fun showLinkUnderline(show: Boolean): Builder {
            link.underline = show
            return this
        }

        /**
         * set link click callback
         *
         * @param onLinkClickCallback OnLinkClickCallback, invoked when clicking the link text
         * @return self
         */
        fun setOnLinkClickCallback(onLinkClickCallback: OnLinkClickCallback?): Builder {
            link.callback = onLinkClickCallback
            return this
        }

        /**
         * set loader
         *
         * @param MDImageLoader the loader
         * @return self
         */
        fun setRxMDImageLoader(MDImageLoader: MDImageLoader?): Builder {
            image.loader = MDImageLoader
            return this
        }

        /**
         * set image default size
         *
         * @param width  the default width to display
         * @param height the default height to display
         * @return self
         */
        fun setDefaultImageSize(width: Int, height: Int): Builder {
            image.defaultSize = intArrayOf(width, height)
            return this
        }

        /**
         * get RxMDConfiguration
         *
         * @return RxMDConfiguration
         */
        fun build(): RxMDConfiguration {
            return RxMDConfiguration(
                header,
                blockQuote,
                horizontalRule,
                code,
                theme,
                todo,
                unOrderList,
                link,
                image
            )
        }
    }
}
