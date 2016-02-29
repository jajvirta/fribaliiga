(ns testi.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com]
              [reagent.core :as reagent]
              [testi.taulukko :as taulukko]))

(defn title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [re-com/title
       :style {:text-align "center"}
       :label (str "Tampereen frisbeegolf-liiga")
       :level :level1])))

(defn navi-panel []
  (fn []
    [:div [:a {:href "/foo"} "bar"] [:p]]))

(defn new-top-panel []
  (let [text-val (reagent/atom "")]
  (fn []
    [:div "hello world.."
     ; resetin sijaan pitais tehda dispatch semmoseen metodiin joka paivittaa modelia
     [re-com/input-text :model text-val :on-change #(reset! text-val %)]
     ])))

(defn sarjataulukko []
  (let [tilanne (re-frame/subscribe [:tilanne])]
    (fn []
      [taulukko/sarjataulukko @tilanne])))

(defn main-panel []
  (fn []
    (let [ready? (re-frame/subscribe [:initialized?])]
      (if @ready? (println "ready") (println "not"))
      (println (str "ready " ready?))
      [re-com/v-box
       :height "100%"
       :children [[title] 
                  [navi-panel]
                  [sarjataulukko]
                  ]])))

(defn top-panel []
  (let [ready? (re-frame/subscribe [:initialized?])]
    (fn []
      (if-not @ready?
        [:div [re-com/throbber] [re-com/button :label "boom" :on-click 
                                 #(re-frame/dispatch [:load-from-backend])]]
        [main-panel]))))
