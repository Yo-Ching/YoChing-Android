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

import android.os.AsyncTask
import tech.aroma.client.Aroma as AromaClient


/**
 *
 * @author SirWellington
 */

internal const val AROMA_TOKEN = "d145da35-acba-43fc-b73f-49d9d173e12b"

internal object Aroma
{
    private var aroma: AromaClient? = null


    fun send(callback: AromaClient.() -> Unit)
    {
        AsyncTask.execute block@ {

            val aroma = this.aroma ?: AromaClient.create(AROMA_TOKEN) ?: return@block
            callback(aroma)
        }
    }

}