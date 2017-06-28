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

import tech.redroma.yoching.WrexagramLine.STRONG


/**
 * Represents the results of a single coin toss.
 * A coin can land either heads or tails.
 * @author SirWellington
 */
enum class CoinResult
{
    HEADS,
    TAILS
}

enum class WrexagramLine
{
    STRONG,
    SPLIT
}

fun computeWrexagram(wrexagramLines: List<WrexagramLine>): Int
{
    val wrexString = wrexagramLines.map { if (it == STRONG) "1" else "2" }.joinToString("")

    val wrexNum = wrexString.toIntOrNull() ?: return 1

    val result = when (wrexNum)
    {
        111111 -> 1
        111112 -> 43
        111121 -> 14
        111122 -> 34
        111211 -> 9
        111212 -> 5
        111221 -> 26
        111222 -> 11
        112111 -> 10
        112112 -> 58
        112121 -> 38
        112122 -> 54
        112211 -> 61
        112212 -> 60
        112221 -> 41
        112222 -> 19
        121111 -> 13
        121112 -> 49
        121121 -> 30
        121122 -> 55
        121211 -> 37
        121212 -> 63
        121221 -> 22
        121222 -> 36
        122111 -> 25
        122112 -> 17
        122121 -> 21
        122122 -> 51
        122211 -> 42
        122212 -> 3
        122221 -> 27
        122222 -> 24
        211111 -> 44
        211112 -> 28
        211121 -> 50
        211122 -> 32
        211211 -> 57
        211212 -> 48
        211221 -> 18
        211222 -> 46
        212111 -> 6
        212112 -> 47
        212121 -> 64
        212122 -> 40
        212211 -> 59
        212212 -> 29
        212221 -> 4
        212222 -> 7
        221111 -> 33
        221112 -> 31
        221121 -> 56
        221122 -> 62
        221211 -> 53
        221212 -> 39
        221221 -> 52
        221222 -> 15
        222111 -> 12
        222112 -> 45
        222121 -> 35
        222122 -> 16
        222211 -> 20
        222212 -> 8
        222221 -> 23
        222222 -> 2
        else   -> 1
    }

    return result
}