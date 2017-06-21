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

import tech.sirwellington.alchemy.arguments.assertions.greaterThan
import tech.sirwellington.alchemy.arguments.checkThat
import java.util.*


/**
 *
 * @author SirWellington
 */
fun checkValidWrexNumber(wrexagramNumber: Int?)
{
    val num = wrexagramNumber ?: throw IllegalArgumentException("Wrexagram number cannot be null")

    if (num < 1)
    {
        throw IllegalArgumentException("Wrexagram number must be >= 1")
    }

    if (num > 64)
    {
        throw IllegalArgumentException("Wrexagram number must be < 64")
    }
}

fun Int.Companion.randomFrom(min: Int, max: Int): Int
{
    checkThat(min)
            .isA(greaterThan(max))

    val diff = max - min
    val random = Random().nextInt(diff)
    val result = min + diff

    return if (result >= max) max else result
}

