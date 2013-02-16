(ns pallet.crates.test-nodes
  "Node definitions for testing pallet nodes.")

(def node-specs
  "Provides the node specs for testing pallet crates."
  {:vmfest
   {:variants
    [{:node-spec
      {:image {:os-family :ubuntu :os-version-matches "12.04"
               :os-64-bit true}}
      :group-suffix "u1204"
      :selectors #{:default :all :ubuntu :ubuntu-12 :ubuntu-12-04}}
     {:node-spec
      {:image {:os-family :debian :os-version-matches "6.0.2.1"
               :os-64-bit true}}
      :group-suffix "d60"
      :selectors #{:all :debian :debian-6 :debiab-6-0}}]}})
