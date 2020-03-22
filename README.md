# KtxSafe
Kotlin functions and extensions to do safe unwrap and functions.

## Nullable values
Handle multiples nullable values on conditions is hard and decrease readability in binary conditions.

*KtxSafe* improve readability:
```kotlin
val nA: String? = ...  
val nB: Boolean? = ... 
val nC: Int? = ... 

withNotNull(nA, nB, nC) { a, b, c -> 
    // execute only `nA`, `nB` and `nC` are not null
}
```
### Handle null conditions with `orElse`:
```kotlin
whenNotNull(nA, nB, nC) { a, b, c -> 
    // execute only `nA`, `nB` and `nC` are not null
}.orElse {
   // do something if any is null
}
```

### List of nullable values:
```kotlin
withAllNotNull(a, b, c, d, e...n) { 
    ...
}
```
or 
```kotlin
whenAllNotNull(a, b, c, d, e,...n) { 
    ...
}.orElse {
    ...
}
```

### With single values:
```kotlin
val value: Any? = ...

withNotNull(value) { 
   // do something if `value` is not null
}
```
or
```kotlin
val value: Any? = ...

value.whenNotNull { 
   // do something if `value` is not null
}.orElse {
   // do something if `value` is null
}
```

## Extract values safely

### Extract value or throw
```kotlin
anyNullableValue.getOrThrow(Exception("Must be non-null"))
```

### Extract value or default
```kotlin
anyNullableValue.getOrDefault(defaultValue)
```

### Extract value or else
```kotlin
anyNullableValue.getOrElse { "return this value as default" }
```

### Safe try block
```kotlin
val result = tryOrNull { doSomething() }
```
or
```kotlin
val result = tryOrNull({ /* log exception */ }, { doSomething() })
```