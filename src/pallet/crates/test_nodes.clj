(ns pallet.crates.test-nodes
  "Node definitions for testing pallet nodes.")

(def node-specs
  "Provides the node specs for testing pallet crates."
  {:vmfest
   {:variants
    [{:node-spec
      {:image {:os-family :ubuntu :os-version-matches "13.04"
               :os-64-bit true}}
      :group-suffix "u1304"
      :selectors #{:default :all :ubuntu :ubuntu-13 :ubuntu-13-04}}
     ;; https://s3.amazonaws.com/vmfest-images/ubuntu-12.04.vdi.gz
     {:node-spec
      {:image {:os-family :ubuntu :os-version-matches "12.04"
               :os-64-bit true}}
      :group-suffix "u1204"
      :selectors #{:default :all :ubuntu :ubuntu-12 :ubuntu-12-04}}
     ;; https://s3.amazonaws.com/vmfest-images/ubuntu-10-10-64bit-server.vdi.gz
     {:node-spec
      {:image {:os-family :ubuntu :os-version-matches "10.10"
               :os-64-bit true}}
      :group-suffix "u1010"
      :selectors #{::all :ubuntu :ubuntu-10 :ubuntu-10-10}}
     ;; https://s3.amazonaws.com/vmfest-images/debian-6.0.2.1-64bit-v0.3.vdi.gz
     {:node-spec
      {:image {:os-family :debian :os-version-matches "6.0.2.1"
               :os-64-bit true}}
      :group-suffix "d602"
      :selectors #{:all :debian :debian-6 :debiab-6-0}}
     ;; https://s3.amazonaws.com/vmfest-images/Debian-6.0.1a-64bit.vdi.gz
     {:node-spec
      {:image {:os-family :debian :os-version-matches "6.0.1.*"
               :os-64-bit true}}
      :group-suffix "d601"
      :selectors #{:all :debian :debian-6 :debiab-6-0}}]}
   :aws-ec2
   {:variants
    [{:node-spec
      {:image {:os-family :ubuntu :os-version-matches "12.04"
               :os-64-bit true
               :image-id "us-east-1/ami-2efa9d47"}}
      :group-suffix "u1204"
      :selectors #{:default :all :ubuntu :ubuntu-12 :ubuntu-12-04}}]}})
