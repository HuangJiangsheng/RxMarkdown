/*
 * Copyright (C) 2016 yydcdut (yuyidong2015@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.yydcdut.rxmarkdown

import android.content.Context
import com.yydcdut.markdown.MarkdownConfiguration
import com.yydcdut.markdown.MarkdownEditText
import com.yydcdut.markdown.syntax.SyntaxFactory
import com.yydcdut.rxmarkdown.RxMarkdown
import rx.Observable
import rx.functions.Func1

/**
 * RxMarkdown for TextView:
 *
 *
 * RxMarkdown.with(content, context)
 * .config(rxMDConfiguration)
 * .factory(AndroidFactory.create())
 * .intoObservable()
 * .subscribeOn(Schedulers.computation())
 * .observeOn(AndroidSchedulers.mainThread())
 * .subscribe();
 *
 *
 * RxMarkdown for EditText:
 *
 *
 * RxMarkdown.live(mEditText)
 * .config(rxMDConfiguration)
 * .factory(EditFactory.create())
 * .intoObservable()
 * .subscribeOn(Schedulers.computation())
 * .observeOn(AndroidSchedulers.mainThread());
 * .subscribe();
 *
 *
 * Created by yuyidong on 16/5/3.
 */
class RxMarkdown {
    private var mContent: String? = null
    private var mMarkdownEditText: MarkdownEditText? = null
    private var mContext: Context
    private var mSyntaxFactory: SyntaxFactory? = null
    private var mMarkdownConfiguration: MarkdownConfiguration? = null

    private constructor(content: String, context: Context) {
        mContent = content
        mContext = context
    }

    private constructor(MarkdownEditText: MarkdownEditText) {
        mMarkdownEditText = MarkdownEditText
        mContext = mMarkdownEditText!!.context
    }

    /**
     * set configuration
     *
     * @param markdownConfiguration [MarkdownConfiguration]
     * @return this(RxMarkdown)
     */
    fun config(markdownConfiguration: MarkdownConfiguration?): RxMarkdown {
        mMarkdownConfiguration = markdownConfiguration
        return this
    }

    /**
     * set factory
     *
     * @param syntaxFactory [TextFactory] and [EditFactory]
     * @return this(RxMarkdown)
     */
    fun factory(syntaxFactory: SyntaxFactory?): RxMarkdown {
        mSyntaxFactory = syntaxFactory
        return this
    }

    /**
     * begin parsing
     *
     * @return RxJava Observable
     */
    fun intoObservable(): Observable<CharSequence> {
        if (mContent != null) {
            return Observable.just<String>(mContent)
                .map<CharSequence>(object : Func1<String, CharSequence> {
                    override fun call(s: String): CharSequence {
                        if (mSyntaxFactory != null) {
                            val config: MarkdownConfiguration = this@RxMarkdown.markdownConfiguration
                            val charSequence = mSyntaxFactory!!.parse(s, config)
                            return charSequence
                        }
                        return s
                    }
                })
        } else {
            return Observable.just<MarkdownEditText?>(mMarkdownEditText)
                .map<CharSequence>(object : Func1<MarkdownEditText, CharSequence> {
                    override fun call(markdownEditText: MarkdownEditText): CharSequence {
                        if (mSyntaxFactory == null) {
                            return markdownEditText.text
                        }
                        markdownEditText.setFactoryAndConfig(
                            mSyntaxFactory!!,
                            this@RxMarkdown.markdownConfiguration
                        )
                        return markdownEditText.text
                    }
                })
        }
    }

    private val markdownConfiguration: MarkdownConfiguration
        /**
         * get configuration
         * if user didn't set configuration, return default.
         *
         * @return [MarkdownConfiguration]
         */
        get() {
            if (mMarkdownConfiguration == null) {
                mMarkdownConfiguration = MarkdownConfiguration.Builder(mContext).build()
            }
            return mMarkdownConfiguration!!
        }

    companion object {
        private val TAG: String = RxMarkdown::class.java.name

        /**
         * get RxMarkdown object
         *
         * @param content the markdown content
         * @param context [Context]
         * @return RxMarkdown object
         */
        @JvmStatic
        fun with(content: String, context: Context): RxMarkdown {
            return RxMarkdown(content, context)
        }

        /**
         * get RxMarkdown object.
         * Live preview in [MarkdownEditText].
         *
         * @param MarkdownEditText RxMDEditText
         * @return RxMarkdown object
         */
        @JvmStatic
        fun live(MarkdownEditText: MarkdownEditText): RxMarkdown {
            return RxMarkdown(MarkdownEditText)
        }
    }
}
