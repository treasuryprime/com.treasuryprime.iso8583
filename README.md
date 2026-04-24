# com.treasuryprime.iso8583

[![CI](https://github.com/treasuryprime/com.treasuryprime.iso8583/actions/workflows/ci.yaml/badge.svg)](https://github.com/treasuryprime/com.treasuryprime.iso8583/actions/workflows/ci.yaml)

A Clojure library for parsing ISO8583 messages into Clojure maps.

## Versioning

This is an updated fork of [alpian/clj-iso8583]. The core logic is largely unchanged,
but the project has been updated to use more modern Clojure practices.

[alpian/clj-iso8583]:https://github.com/alpian/clj-iso8583

## Usage

This library is published to [GitHub Packages](https://github.com/treasuryprime/com.treasuryprime.iso8583/packages)
(not Clojars). Consumers must authenticate to GitHub Packages.

### deps.edn

```clojure
{:deps {com.treasuryprime/iso8583 {:mvn/version "0.8.0"}}

 :mvn/repos
 {"github-treasuryprime"
  {:url "https://maven.pkg.github.com/treasuryprime/com.treasuryprime.iso8583"}}}
```

### Authentication

The GitHub Packages Maven repo requires a GitHub token with `read:packages`.
Add credentials to `~/.m2/settings.xml`:

```xml
<settings>
  <servers>
    <server>
      <id>github-treasuryprime</id>
      <username>YOUR_GITHUB_USERNAME</username>
      <password>YOUR_GITHUB_PAT</password>
    </server>
  </servers>
</settings>
```

The `<id>` must match the key used under `:mvn/repos`.

### Parsing

```clj
((parser field-definitions) message-bytes)
```

### Writing

```clj
(write field-definitions {:pan "1111222233334444" :processing-code "010000" :transaction-amount "000000110000"})
```

## Development

```bash
clojure -M:test                 # run unit tests (Kaocha)
clojure -M:test/watch           # run tests in watch mode
clojure -M:cljfmt check         # lint formatting
clojure -M:cljfmt fix           # auto-fix formatting
clojure -T:build clean          # clean target/
clojure -T:build jar            # build target/iso8583-<ver>.jar
clojure -T:build install        # install to ~/.m2 for local consumers
```

## Releasing

Releases are cut manually from a maintainer's laptop.

1. Bump `version` in `build.clj` (e.g. `"0.8.0"` -> `"0.8.1"`).
2. Update `CHANGELOG.md`.
3. Commit, open PR, merge to `main`.
4. Tag the merge commit: `git tag v0.8.1 && git push origin v0.8.1`.
5. Deploy:
   ```bash
   export CLOJARS_USERNAME=<your-github-username>
   export CLOJARS_PASSWORD=<github-pat-with-write:packages>
   clojure -T:build deploy
   ```
   (`slipset/deps-deploy` reads `CLOJARS_*` env vars; our `build.clj` points them at GitHub Packages, not Clojars.)

## License

Original project copyright © 2012 Ian Davies.
Additional changes copyright © 2022 Chris Oakman.
Additional changes copyright © 2024 Treasury Prime.

Distributed under the [Eclipse Public License](LICENSE.txt).
