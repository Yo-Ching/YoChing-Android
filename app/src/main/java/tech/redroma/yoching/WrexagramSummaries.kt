/*
 * Copyright 2017 RedRoma, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.redroma.yoching

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.*


/**
 *  Used to
 *
 * @author SirWellington
 */
data class WrexagramSummary(val number: Int,
                            val title: String,
                            val subTitle: String,
                            val whatsUp: String)


private object Summaries
{
    var wrexagramSummaries: List<WrexagramSummary> = listOf()

    fun loadSummaries(context: Context)
    {
        val jsonFile = context.assets.open("json/wrexagrams.json") ?: return

        val string = jsonFile.readToString() ?: return

        val jsonArray = JSONArray(string)

        wrexagramSummaries = (0 until jsonArray.length())
                .map { jsonArray.getJSONObject(it) }
                .map { this.readSummaryFrom(it) }

    }

    private fun readSummaryFrom(json: JSONObject): WrexagramSummary
    {
        val number = json.optInt("number")
        val title = json.optString("title")
        val subtitle = json.optString("subtitle")
        val whatsUp = json.optString("whats-up")

        return WrexagramSummary(number = number,
                                title = title,
                                subTitle = subtitle,
                                whatsUp = whatsUp)
    }

    private fun InputStream.readToString(): String?
    {
        val reader = InputStreamReader(this)
        val buffered = BufferedReader(reader)

        return buffered.use { it.readText() }
    }
}

fun Context.loadWrexagramSummary(wrexagramNumber: Int): WrexagramSummary?
{
    if (Summaries.wrexagramSummaries.isNotEmpty())
    {
        return Summaries.wrexagramSummaries.getOrNull(wrexagramNumber - 1)
    }

    Summaries.loadSummaries(this)

    return Summaries.wrexagramSummaries.getOrNull(wrexagramNumber - 1)
}