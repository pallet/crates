# Pallet crates

See [crate documentation](http://palletops.com/doc/crates/) for details of the
crates.

This is a "meta" repository for [Pallet](http://palletops.com) crates, and is
used in automating some of the tasks around managing the individual crate
repositories and their documentation.

## Usage

The [crate metadata](resources/pallet/crate/meta.edn) is used to drive
everything.

Set up lein to run with plugins from the project.

```
echo "src:resources:dev-resources" > .lein-classpath
```

To generate the docs you can specify a target directory.

```
lein crate-docs output-dir
```

The output directory defaults to the "target" directory.


## License

Licensed under [EPL](http://www.eclipse.org/legal/epl-v10.html)

Copyright 2013 Hugo Duncan.
