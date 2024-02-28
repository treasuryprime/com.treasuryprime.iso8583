# com.treasuryprime.iso8583 [![CI Status](https://github.com/treasuryprime/com.treasuryprime.iso8583/actions/workflows/ci.yaml/badge.svg)](https://github.com/treasuryprime/com.treasuryprime.iso8583/actions/workflows/ci.yaml) [![Clojars Project](https://img.shields.io/clojars/v/com.treasuryprime/iso8583.svg)](https://clojars.org/com.treasuryprime/iso8583)

A Clojure library for parsing ISO8583 messages into Clojure maps.

## Versioning

This is an updated fork of [alpian/clj-iso8583]. The core logic is largely unchanged,
but the project has been updated to use more modern Clojure practices.

[alpian/clj-iso8583]:https://github.com/alpian/clj-iso8583

## Usage

### Parsing

```clj
((parser field-definitions) message-bytes)
```

### Writing

```clj
(write field-definitions {:pan "1111222233334444" :processing-code "010000" :transaction-amount "000000110000"})
```

## Development

Install [leiningen], then from the project directory:

```sh
# run unit tests
lein test
```

To deploy to Clojars:

1. make sure that `project.clj` and `CHANGELOG.md` are updated
1. tag with the latest version: `git tag -a v1.2.3 -m "v1.2.3"`
1. login to clojars.org and create a one-time deploy token
1. deploy to clojars: `lein deploy clojars` (use the one-time token as the password)

[leiningen]:https://leiningen.org/

## License

Original project copyright © 2012 Ian Davies.
Additional changes copyright © 2022 Chris Oakman.
Additional changes copyright © 2024 Treasury Prime.

Distributed under the [Eclipse Public License](LICENSE.txt).
