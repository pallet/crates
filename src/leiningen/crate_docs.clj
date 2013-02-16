(ns leiningen.crate-docs
  "Generate Crate documents"
  (:require
   [clojure.java.io :refer [file]]
   [pallet.crate.generate-docs :refer [generate]]))

(defn crate-docs
  "Generate crate docs"
  [{:keys [root] :as project} & args]
  (generate (file root "target")))
