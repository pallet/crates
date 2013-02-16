(ns leiningen.crate-docs
  "Generate Crate documents"
  (:require
   [clojure.java.io :refer [file]]
   [leiningen.core.main :refer [debug]]
   [pallet.crate.generate-docs :refer [generate]]))

(defn crate-docs
  "Generate crate docs"
  [{:keys [root] :as project} & args]
  (let [f (first args)
        f (and f (file f))]
    (when (and f (not (.isDirectory f)))
      (throw (ex-info
              (str "Specified target " (.getPath f) " is not a directory.")
              {:f (.getAbsolutePath f)})))
    (let [f (or f (doto (file root "target") (.mkdir)))]
      (debug "Generating to" (.getPath f))
      (generate f))))
