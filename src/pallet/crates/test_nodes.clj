(ns pallet.crates.test-nodes
  "Node definitions for testing pallet nodes.")

(defn aws-node
  [pallet-aws? os-family [maj min]  os-64-bit image-id login-user & selectors]
  (let [os-family-name (name os-family)
        os-version (format "%s.%s" maj min)
        image-id (if pallet-aws?
                   image-id
                   (format "us-east-1/%s" image-id))]
    {:node-spec
     {:image
      {:os-family os-family
       (if pallet-aws? :os-version :os-version-matches) os-version
       :os-64-bit os-64-bit
       (if pallet-aws? :login-user :override-login-user) login-user
       :image-id image-id}
      :location { :location-id "us-east-1a"}}
     :group-suffix (str (first os-family-name) maj min)
     :selectors (into #{:default
                        :all
                        (keyword os-family-name)
                        (keyword (format "%s-%s" os-family-name maj))
                        (keyword (format "%s-%s-%s" os-family-name maj min))}
                      selectors)}))

;; Ubuntu AWS images from:
;;   - http://cloud-images.ubuntu.com/locator/ec2/
;; debian AWS images from:
;; https://aws.amazon.com/marketplace/ordering/ref=dtl_psb_continue?ie=UTF8&productId=8e939b13-41b6-40bc-b2ba-481f2d6c5d79&region=us-east-1
(defn aws-nodes [pallet-aws?]
  (vec
   (map
    #(apply aws-node pallet-aws? %)
    [[:amzn-linux ["2013" "092"] true "ami-bba18dd2" "ec2-user"]
     [:ubuntu ["10" "04"] true "ami-6b350a02" "ubuntu"]
     [:ubuntu ["12" "04"] true "ami-0b9c9f62" "ubuntu"]
     [:ubuntu ["13" "04"] true "ami-1d132274" "ubuntu"]
     [:ubuntu ["13" "10"] true "ami-89181be0" "ubuntu"]
     [:centos ["6" "5"] true "ami-05ebd06c" "root"] ;; RightImage_CentOS_6.5_x64_v13.5.2.2_EBS
     ;; disabled because we already have 6.5
     ;;[:centos ["6" "4"] true "ami-d14c34b8" "root"] ;; RightImage_CentOS_6.4_x64_v13.5.0.2
     [:debian ["7" "4"] true "ami-d3675dba" "admin"] ;; ebs https://wiki.debian.org/Cloud/AmazonEC2Image/Wheezy
     ;; (marketplace) [:debian ["7" "3"] true "ami-0da18864" "admin"] ;; ebs https://wiki.debian.org/Cloud/AmazonEC2Image
     ;;disabled because we already have 7.4
     ;;[:debian ["7" "1"] true "ami-9e95e8f7" "admin"] ;; ebs https://wiki.debian.org/Cloud/AmazonEC2Image
     [:debian ["6" "0"] true "ami-e00df089" "root"] ;; ebs https://wiki.debian.org/Cloud/AmazonEC2Image
     ])))

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
      :selectors #{:all :debian :debian-6 :debian-6-0 :debian-6-0-2}}
     ;; https://s3.amazonaws.com/vmfest-images/Debian-6.0.1a-64bit.vdi.gz
     {:node-spec
      {:image {:os-family :debian :os-version-matches "6.0.1.*"
               :os-64-bit true}}
      :group-suffix "d601"
      :selectors #{:all :debian :debian-6 :debian-6-0 :debian-6-0-1}}]}
   :aws-ec2 {:variants (aws-nodes false)}
   :pallet-ec2 {:variants (aws-nodes true)}})
