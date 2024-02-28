(ns com.treasuryprime.iso8583.format
  (:require
   [com.treasuryprime.iso8583.binary :refer [bytes-to-ascii bytes-to-hex]]
   [com.treasuryprime.iso8583.util.string :as util.str]))

(defn variable-length-field [field-length]
  {:reader
   (fn [decoder-fn _field-name input]
     (let [[length-bytes remaining-input] (split-at field-length input)
           length (Integer/parseInt (bytes-to-ascii length-bytes))
           [field-bytes remaining-input] (split-at length remaining-input)]
       [(decoder-fn field-bytes) remaining-input]))

   :writer
   (fn [encoder-fn _field-name value]
     (let [e (encoder-fn value)]
       (str
        (util.str/left-pad (str (count e)) field-length "0")
        e)))})

(defn- error [field-name error-msg data]
  {:errors [(str "(field=" (name field-name) ") Error: " error-msg ". The data: [" data "]")]})

(defn fixed-length-field [length]
  {:reader
   (fn [decoder-fn field-name input]
     (if (>= (count input) length)
       (let [[field-bytes remaining-input] (split-at length input)]
         [(decoder-fn field-bytes) remaining-input])
       [(error field-name (str "Less than " length " bytes available") (bytes-to-hex input))]))

   :writer
   (fn [encoder-fn _field-name value]
     (encoder-fn value))})

(defn field-definition
  [field-number
   field-name
   {reader :reader writer :writer} &
   {:keys [codec] :or {codec {:decoder bytes-to-ascii
                              :encoder identity}}}]
  (let [{:keys [decoder encoder]} codec]
    [field-number
     {:name field-name
      :reader (partial reader decoder field-name)
      :writer (partial writer encoder field-name)}]))

(defn make-field-definitions [descriptions]
  (let [make-field-definition #(apply field-definition %)]
    (into {} (map make-field-definition descriptions))))
