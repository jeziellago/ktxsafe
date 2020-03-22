/*
 * Copyright 2020 Jeziel Lago - jeziellago@gmail.com
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
@file:Suppress("Unused")

package com.ktx.safe

fun <T> T?.getOrThrow(error: Throwable): T {
    return this ?: throw error
}

fun <T> T?.getOrDefault(default: T): T {
    return this ?: default
}

fun <T> T?.getOrElse(defaultBlock: () -> T): T {
    return this ?: defaultBlock()
}

fun <T> tryOrNull(
    catchError: ((Throwable) -> Unit)? = null,
    block: () -> T
): T? {
    return try {
        block()
    } catch (e: Throwable) {
        catchError?.invoke(e)
        null
    }
}