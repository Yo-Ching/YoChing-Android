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

package tech.redroma.yoching.wrexagrams


/**
 *
 * @author SirWellington
 */

private object WrexagramBodies
{
    var bodies: List<String> = listOf()

    fun loadAllBodies(context: android.content.Context)
    {
        tech.redroma.yoching.wrexagrams.WrexagramBodies.bodies = context.assets.list("text")
                .map { context.assets.open("text/$it") }
                .map { java.io.InputStreamReader(it) }
                .map { java.io.BufferedReader(it) }
                .map { it.readText() }
    }
}


internal fun android.content.Context.loadWrexagramBody(wrexagramNumber: Int): String?
{
    if (tech.redroma.yoching.wrexagrams.WrexagramBodies.bodies.isEmpty())
    {
        tech.redroma.yoching.wrexagrams.WrexagramBodies.loadAllBodies(this)
    }

    return tech.redroma.yoching.wrexagrams.WrexagramBodies.bodies.getOrNull(wrexagramNumber - 1)
}