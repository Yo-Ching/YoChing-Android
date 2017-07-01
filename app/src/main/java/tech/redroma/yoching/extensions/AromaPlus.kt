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

package tech.redroma.yoching.extensions

import android.content.Context
import android.os.AsyncTask
import java.lang.Thread.UncaughtExceptionHandler
import tech.aroma.client.Aroma as AromaClient


/**
 *
 * @author SirWellington
 */

private const val AROMA_TOKEN = "d145da35-acba-43fc-b73f-49d9d173e12b"

internal object Aroma
{
    private var aroma: AromaClient? = null

    fun send(callback: AromaClient.() -> Unit)
    {
        AsyncTask.execute block@ {

            val aroma = aroma ?: AromaClient.create(AROMA_TOKEN) ?: return@block

            aroma.deviceName = aroma.hostname
            aroma.hostname = this.deviceName

            callback(aroma)
        }
    }

    private val deviceName: String
        get()
        {
            val manufacturer = android.os.Build.MANUFACTURER
            val model = android.os.Build.MODEL

            return if (model.startsWith(manufacturer))
            {
                model.capitalize()
            }
            else
            {
                "${manufacturer.capitalize()} $model"
            }

        }

}

internal class AromaErrorHandler(val delegate: UncaughtExceptionHandler): UncaughtExceptionHandler
{
    override fun uncaughtException(t: Thread?, e: Throwable?)
    {
        Aroma.send{ sendHighPriorityMessage("App Crashed", "Crash on Thread [$t] \n\n $e") }

        delegate.uncaughtException(t, e)
    }

    companion object
    {
        @JvmStatic
        fun register(context: Context)
        {
            if (Thread.getDefaultUncaughtExceptionHandler() is AromaErrorHandler)
            {
                return
            }

            val existingHandler = Thread.getDefaultUncaughtExceptionHandler() ?: return
            Thread.setDefaultUncaughtExceptionHandler(AromaErrorHandler(existingHandler))

            LOG.info("Setting ${AromaErrorHandler.javaClass} as the default error handler with $existingHandler as the delegate")
            Aroma.send { sendLowPriorityMessage("Registering Error Handler", "Using $existingHandler as the delegate error handler") }
        }
    }

}