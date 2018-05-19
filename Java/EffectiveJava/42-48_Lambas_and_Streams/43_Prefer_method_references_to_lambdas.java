map.merge(key, 1, (count, incr) -> count + incr);

// Method reference
map.merge(key, 1, Integer::sum);


// Class with long name
service.execute(GoshThisClassNameIsHumongous::action);

service.execute(() -> action());

// -------------------------------------------------------------------------------------------------
// Method Ref Type      Example                     Lambda Equivalent
//
// Static               Integer::parseInt           str -> Integer.parseInt(str)
// Bound                Instant.now()::isAfter      Instant then = Instant.now();
//                                                  t -> then.isAfter(t)
// Unbound              String::toLowerCase         str -> str.toLowerCase()
// Class Constructor    TreeMap<K,V>::new           () -> new TreeMap<K,V>
// Array Constructor    int[]::new                  len -> new int[len]
// -------------------------------------------------------------------------------------------------


// NOTE Where method references are shorter and clearer, use them; where they aren’t, stick with
// lambdas.
