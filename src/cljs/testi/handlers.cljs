(ns testi.handlers
(:require [re-frame.core :as re-frame]
          [testi.db :as db]
          [ajax.core :as ajax]))

(re-frame/register-handler
  :load-from-backend
  (fn [db _]
    (println "processing")
    (ajax.core/GET
      "/number"
      {:handler #(re-frame/dispatch [:process-response %1])
       :error-handler #(re-frame/dispatch [:process-response %1])}
      )
    db))

(re-frame/register-handler
  :process-response
  (fn 
    [db [_ response]]
    (println "boom")
    (println response)
    (-> db
      (assoc :results "hohu")
      (assoc :number response))))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   (println "hep") 
   db/default-db))
