(ns junakysely.core
  (:require [reagent.core :as r]
            [reagent.session :as session]
            [secretary.core :as secretary :include-macros true]
            [goog.events :as events]
            [goog.history.EventType :as HistoryEventType]
            [markdown.core :refer [md->html]]
            [junakysely.ajax :refer [load-interceptors!]]
            [ajax.core :refer [GET POST]])
  (:import goog.History))

(defn- submit-comment! [state]
  (POST "/api/comments"
    {:params @state
     :handler (fn [] (swap! state {:name "" :text ""}))}))
     
(defn comment-form []
  (let [form-state (r/atom {:name "" :text ""})]
    (fn []
      [:div.comment-form [:h1 "Millainen junakokemuksesi oli?"]
                         [:form {:on-submit #(do (.preventDefault %) (.log js/console "JEE FORM SUBMIT") (submit-comment! form-state))}
                                [:anti-forgery-field]
                                [:input.text {:type "text"
                                              :placeholder "Palautteesi"
                                              :value (:text @form-state)
                                              :on-change #(swap! form-state assoc :text (-> % .-target .-value))}]
                                [:input {:type "submit" :value "Lähetä palaute"}]]])))

(defn home-page []
  [:div.container
   [:div.jumbotron
    [comment-form]]])

(def pages
  {:home #'home-page})

(defn page []
  [(pages (session/get :page))])

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")

(secretary/defroute "/" []
  (session/put! :page :home))

(secretary/defroute "/about" []
  (session/put! :page :about))

;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
        (events/listen
          HistoryEventType/NAVIGATE
          (fn [event]
              (secretary/dispatch! (.-token event))))
        (.setEnabled true)))

;; -------------------------
;; Initialize app
(defn fetch-docs! []
  (GET (str js/context "/docs") {:handler #(session/put! :docs %)}))

(defn mount-components []
  (r/render [#'page] (.getElementById js/document "app")))

(defn init! []
  (load-interceptors!)
  (fetch-docs!)
  (hook-browser-navigation!)
  (mount-components))
