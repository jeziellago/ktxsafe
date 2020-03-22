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

/**
 * Calls the specified function [block] with `value` as its argument if `value` is not null
 *
 * val value: String? = ...
 * withNotNull(value) {
 *    ...do something
 * }
 * */
inline fun <A> withNotNull(
    value: A?,
    block: A.() -> Unit
) {
    if (value != null) block(value)
}

/**
 * Calls the specified function [block] with `a` and `b` as argument if `a` and `b` are not null
 *
 * val a: String? = ...
 * val b: Int? = ...
 * withNotNull(a, b) { safeA, safeB ->
 *    ...do something
 * }
 * */
inline fun <A, B> withNotNull(
    a: A?,
    b: B?,
    block: (A, B) -> Unit
) {
    if (a != null && b != null) block(a, b)
}

/**
 * Calls the specified function [block] with `a`,`b` and `c` as argument if them are not null
 *
 * val a: String? = ...
 * val b: Int? = ...
 * val c: Boolean? = ...
 * withNotNull(a, b, c) { safeA, safeB, safeC ->
 *    ...do something
 * }
 * */
inline fun <A, B, C> withNotNull(
    a: A?,
    b: B?,
    c: C?,
    block: (A, B, C) -> Unit
) {
    if (a != null && b != null && c != null) block(a, b, c)
}

/**
 * Calls the specified function [block] if `values` contains not null elements
 *
 * val a: String? = ...
 * val b: Int? = ...
 * val c: Boolean? = ...
 * val d: Float? = ...
 * val e: Any? = ...
 *
 * withAllNotNull(a, b, c, d, e...n) {
 *   ...do something
 * }
 * */
inline fun withAllNotNull(
    vararg values: Any?,
    block: () -> Unit
) {
    if (values.none { it == null }) block()
}

/**
 * Return the specified function [block] if `this` is not null
 * or return function with null value. Also used with [orElse]
 *
 * val value: String? = ...
 * value.whenNotNull {
 *      ...do something
 *  }.orElse {
 *     ...
 *  }
 * */
inline fun <A, B> A?.whenNotNull(
    crossinline block: A.() -> B?
): (() -> B?) {
    return if (this != null) {
        { block(this) }
    } else {
        { null }
    }
}

/**
 * Return the specified function [block] if `a` and `b` are not null
 * or return function with null value. Also used with [orElse]
 *
 * val a: String? = ...
 * val b: Boolean? = ...
 * whenNotNull(a, b) { safeA, safeB ->
 *     ...
 * }.orElse {
 *    ...
 * }
 *
 * or
 *
 * val result = whenNotNull(a, b) { safeA, safeB ->
 *     ...
 * }.orElse {
 *    ...
 * }
 * */
inline fun <A, B, R> whenNotNull(
    a: A?,
    b: B?,
    crossinline block: (A, B) -> R?
): (() -> R?) {
    return if (a != null && b != null) {
        { block(a, b) }
    } else {
        { null }
    }
}

/**
 * Return the specified function [block] if `a`, `b` and `c` are not null
 * or return function with null value. Also used with [orElse]
 *
 * val a: String? = ...
 * val b: Int? = ...
 * val c: Boolean? = ...
 *
 * whenNotNull(a, b, c) { safeA, safeB, safeC ->
 *    ...
 * }.orElse {
 *    ...
 * }
 *
 * or
 *
 * val result = whenNotNull(a, b, c) { safeA, safeB, safeC ->
 *    ...
 * }.orElse {
 *    ...
 * }
 * */
inline fun <A, B, C, R> whenNotNull(
    a: A?,
    b: B?,
    c: C?,
    crossinline block: (A, B, C) -> R?
): (() -> R?) {
    return if (a != null && b != null && c != null) {
        { block(a, b, c) }
    } else {
        { null }
    }
}

/**
 * Return the specified function [block] if `values` contains not null elements
 * or return function with null value. Also used with [orElse]
 *
 * val a: String? = ...
 * val b: Int? = ...
 * val c: Boolean? = ...
 * val d: Float? = ...
 * val e: Any? = ...
 *
 * whenAllNotNull(a, b, c, d, e...n) {
 *   ...do something
 * }.orElse {
 *   ...
 * }
 * */
inline fun whenAllNotNull(
    vararg value: Any?,
    crossinline block: () -> Unit
): (() -> Any?) {
    return if (value.none { it == null }) {
        { block() }
    } else {
        { null }
    }
}

inline fun <R> (() -> R?).orElse(block: () -> R): R = this.invoke() ?: block()
