(ns testi.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com]
              [testi.taulukko :as taulukko]))

(defn title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [re-com/title
       :style {:text-align "center"}
       :label (str "Tampereen seudun frisbeegolf-liiga joukkueille")
       :level :level1])))

(defn navi-panel []
  (fn []
    [:div [:a {:href "/foo"} "bar"] [:p]]))

(defn new-top-panel []
  (fn []
    [:div "hello world.."]))

(defn top-panel []
  (let [ready? (re-frame/subscribe [:initialized?])]
    (fn []
      (if-not @ready?
        [:div [re-com/throbber] [re-com/button :label "boom" :on-click 
                                 #(re-frame/dispatch [:load-from-backend])]]
        [:div "Ready..."]))))

(defn main-panel []
  (fn []
    (let [ready? (re-frame/subscribe [:initialized?])]
      (if @ready? (println "ready") (println "not"))
      (println (str "ready " ready?))
      [re-com/v-box
       :height "100%"
       :children [[title] 
                  [navi-panel]
                  [re-com/button :label "boom" :on-click (re-frame/dispatch-sync [:load-from-backend])]
                  ]])))
