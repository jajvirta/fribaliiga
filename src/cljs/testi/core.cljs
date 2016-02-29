(ns testi.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [re-frame.core :as re-frame]
              [secretary.core :as secretary :include-macros true]
              [testi.views :as views]
              [testi.config :as config]
              [testi.handlers]
              [testi.subs]
              [accountant.core :as accountant]))

;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "2 testi"]
   [:div [:a {:href "/about"} "go to about page"]]])

(defn about-page []
  [:div [:h2 "About testi"]
   [:div [:a {:href "/"} "go to the home page"]]])

; tää pitäis kai tehdä niinku re-framen sivuilla suositellaan
; mutta olkoon toistaiseksi näin
(defn current-page []
  [:div [(session/get :current-page)]])

(secretary/defroute "/" []
  (session/put! :current-page #'views/top-panel))

(secretary/defroute "/about" []
  (session/put! :current-page #'views/new-top-panel))

;(defn mount-root []
;  (reagent/render [current-page] (.getElementById js/document "app")))

(defn mount-root []
  (reagent/render [current-page] 
                  (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!)
  (accountant/dispatch-current!)
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
