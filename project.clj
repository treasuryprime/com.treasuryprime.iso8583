(defproject com.treasuryprime/iso8583 "0.8.0"
  :repositories [["github" {:url "https://maven.pkg.github.com/treasuryprime/com.treasuryprime.iso8583"
                            :username "treasuryprime"}]]
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :description "Parse ISO 8583 messages into Clojure maps and vice versa (updated fork of alpian/clj-iso8583)"
  :url "https://github.com/treasuryprime/com.oakmac.iso8583"
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :source-paths ["src-clj/"]
  :test-paths ["test/"])
