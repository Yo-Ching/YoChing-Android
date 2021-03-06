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

import tech.redroma.yoching.extensions.checkValidWrexNumber


/**
 *
 * @author SirWellington
 */

private object WrexagramBodies
{
    var bodies: List<String> = listOf()

    fun loadAllBodies(context: android.content.Context)
    {
        bodies = context.assets.list("text")
                .map { context.assets.open("text/$it") }
                .map { java.io.InputStreamReader(it) }
                .map { java.io.BufferedReader(it) }
                .map { it.readText() }
    }
}


internal fun android.content.Context.loadWrexagramBody(wrexagramNumber: Int): String?
{
    checkValidWrexNumber(wrexagramNumber)

    if (WrexagramBodies.bodies.isEmpty())
    {
        WrexagramBodies.loadAllBodies(this)
    }

    return WrexagramBodies.bodies.getOrNull(wrexagramNumber - 1)
}