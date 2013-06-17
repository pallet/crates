# Pallet crates

See [crate documentation](http://palletops.com/doc/crates/) for details of the
crates.

This is a project used in [Pallet](http://palletops.com) crates, to provide
common test dependencies.

## Usage

Add to the `:dev` profile of a crate.

In the `pallet.clj` file for the crate:

```clj
(require
 '[pallet.crates.test-nodes :refer [node-specs]])
(defproject lein-crate
  :provider node-specs) ; supported pallet nodes
```

## License

Licensed under [EPL](http://www.eclipse.org/legal/epl-v10.1.1.html)

Copyright 2013 Hugo Duncan.
