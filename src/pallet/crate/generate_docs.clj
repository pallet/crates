(ns pallet.crate.generate-docs
  "Generate documentation for Pallet crates"
  (:require
   [clojure.java.io :refer [file resource]]
   [leiningen.core.main :refer [debug]]
   [stencil.core :refer [render-file]]))

(defn read-edn [f]
  (binding [*read-eval* false]
    (read-string f)))

(def defaults {:mvn-repo :clojars :tag-prefix ""})

(defn crate-doc-file [dir crate]
  (file dir "_posts" (format "2012-01-01-%s.md" (name crate))))

(defn crate-index-file [dir]
  (file dir "index.md"))

(def artifact-id {:clojars "com.palletops" :sonatype "org.cloudhoist"})

(defn version-info [crate values {:keys [mvn-repo] :as v}]
  (->
   {:artifact-id (str (name crate) "-crate")
    :source-path (format "src/pallet/crate/%s.clj" (name crate))
    :group-id (artifact-id (or mvn-repo (:mvn-repo values :clojars)))}
   (merge
    (dissoc values :versions)
    v)
   (update-in [:mvn-repo] name)))

(defn generate-docs [meta output-dir]
  (.mkdirs (file output-dir "_posts"))
  (doseq [[crate {:keys [versions] :as values}] meta
          :let [values (merge defaults values)
                versions (map (partial version-info crate values) versions)
                default-version (last (sort-by :version versions))
                values (-> values
                           (assoc :crate-name (name crate))
                           (assoc :versions versions)
                           (merge default-version))]]
    (debug "Generating for" crate)
    (debug "Generating versions" versions)
    (debug "Generating from" values)
    (spit (crate-doc-file output-dir crate)
          (render-file "pallet/doc/crate.md" values))))

(defn generate-index [meta output-dir]
  (let [values (for [[crate {:keys [header] :as values}] meta]
                 {:header header :crate-name (name crate)})]
    (debug "Generating index" values)
    (spit (crate-index-file output-dir)
          (render-file "pallet/doc/index.md" {:crates values}))))


(defn generate [output-dir]
  (let [f (resource "pallet/crate/meta.edn")
        _ (debug "File" f)
        meta (read-edn (slurp f))]
    (debug "Meta" meta)
    (generate-docs meta output-dir)
    (generate-index meta output-dir)))
