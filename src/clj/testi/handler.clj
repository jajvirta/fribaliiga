(ns testi.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [hiccup.page :refer [include-js include-css html5]]
            [testi.middleware :refer [wrap-middleware]]
            [environ.core :refer [env]]))

(def mount-target
  [:div#app
      [:h3 "ClojureScript has not been compiled!"]
      [:p "please run "
       [:b "lein figwheel"]
       " in order to start the compiler"]])

(def loading-page
  (html5
   [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1"}]
     (include-css (if (env :dev) "css/site.css" "css/site.min.css"))
     (include-css "vendor/css/re-com.css")
     (include-css "vendor/css/bootstrap.min.css")
     (include-css "vendor/css/material-design-iconic-font.min.css")
     ]
    [:body
     mount-target
     (include-js "js/app.js")]))

(defn- str-to [num]
  (apply str (interpose ", " (range 1 (inc num)))))

(defroutes routes
  (GET "/" [] loading-page)
  (GET "/about" [] loading-page)
  (GET "/foo/:to" [to] (str-to (Integer. to)))
  (GET "/number" [] "123")
  
  (resources "/")
  (not-found "Not Found"))

(def app (wrap-middleware #'routes))
