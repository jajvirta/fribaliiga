(ns testi.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(defn not-empty [db]
  nil)

(re-frame/register-sub
  :initialized?
  (fn [db]
    (println (@db :results))
    (reaction 
      (not (empty? (@db :results))))))

(re-frame/register-sub
 :tilanne
 (fn [db]
   (reaction (:tilanne @db))))

(re-frame/register-sub
 :number
 (fn [db]
   (reaction (:number @db))))