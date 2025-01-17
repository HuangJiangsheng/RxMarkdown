// Copyright (C) 2011 Chan Wai Shing
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package prettify.lang

/**
 * Lang class for Java Prettify.
 * Note that the method [.getFileExtensions] should be overridden.
 *
 * @author Chan Wai Shing cws1989@gmail.com
 */
abstract class Lang {
    /**
     * Similar to those in JavaScript prettify.js.
     */
    private var shortcutStylePatterns: List<List<Any>>

    /**
     * Similar to those in JavaScript prettify.js.
     */
    private var fallthroughStylePatterns: List<List<Any>>

    /**
     * See [prettify.lang.LangCss] for example.
     */
    private var extendedLangs: List<Lang>

    /**
     * Constructor.
     */
    init {
        shortcutStylePatterns = ArrayList()
        fallthroughStylePatterns = ArrayList()
        extendedLangs = ArrayList()
    }

    fun getShortcutStylePatterns(): List<List<Any>> {
        val returnList: MutableList<List<Any>> = ArrayList()
        for (shortcutStylePattern in shortcutStylePatterns) {
            returnList.add(ArrayList(shortcutStylePattern))
        }
        return returnList
    }

    fun setShortcutStylePatterns(shortcutStylePatterns: List<List<Any>>?) {
        if (shortcutStylePatterns == null) {
            this.shortcutStylePatterns = ArrayList()
            return
        }
        val cloneList: MutableList<List<Any>> = ArrayList()
        for (shortcutStylePattern in shortcutStylePatterns) {
            cloneList.add(ArrayList(shortcutStylePattern))
        }
        this.shortcutStylePatterns = cloneList
    }

    fun getFallthroughStylePatterns(): List<List<Any>> {
        val returnList: MutableList<List<Any>> = ArrayList()
        for (fallthroughStylePattern in fallthroughStylePatterns) {
            returnList.add(ArrayList(fallthroughStylePattern))
        }
        return returnList
    }

    fun setFallthroughStylePatterns(fallthroughStylePatterns: List<List<Any>>?) {
        if (fallthroughStylePatterns == null) {
            this.fallthroughStylePatterns = ArrayList()
            return
        }
        val cloneList: MutableList<List<Any>> = ArrayList()
        for (fallthroughStylePattern in fallthroughStylePatterns) {
            cloneList.add(ArrayList(fallthroughStylePattern))
        }
        this.fallthroughStylePatterns = cloneList
    }

    /**
     * Get the extended languages list.
     *
     * @return the list
     */
    fun getExtendedLangs(): List<Lang> {
        return ArrayList(extendedLangs)
    }

    /**
     * Set extended languages. Because we cannot register multiple languages
     * within one [Lang], so it is used as an solution. See
     * [prettify.lang.LangCss] for example.
     *
     * @param extendedLangs the list of [Lang]s
     */
    fun setExtendedLangs(extendedLangs: List<Lang>) {
        this.extendedLangs = ArrayList(extendedLangs)
    }

    companion object {
        val fileExtensions: List<String>
            /**
             * This method should be overridden by the child class.
             * This provide the file extensions list to help the parser to determine which
             * [Lang] to use. See JavaScript prettify.js.
             *
             * @return the list of file extensions
             */
            get() = ArrayList()
    }
}
