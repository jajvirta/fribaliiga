(ns testi.taulukko
  (:require [re-com.core :as re-com]))

(defn rivi [a b c]
  [[re-com/label :label a :width "20%"]
   [re-com/label :label b :width "10%"]
   [re-com/label :label c :width "10%"]])

(defn otsikko-rivi []
  [re-com/h-box
   :class "rc-div-table-header"
   :children
   (rivi "Nimi" "Ottelut" "Pisteet")])

(defn sarjataulukko-rivi [x]
  [re-com/h-box 
   :class "rc-div-table-row"
   :children 
   (rivi (x :name) (x :matches) (x :points))])

(defn sarjataulukko [tilanne]
  ; (fn []
    [:div
     [re-com/v-box
      :gap "3px"
      :class "rc-div-table"
      :children
      [[otsikko-rivi]
       (for [x tilanne] [sarjataulukko-rivi x])]]]);)
