(ns junakysely.routes.home
  (:require [junakysely.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]
            [clojure.tools.logging :as log]
            [junakysely.db.core :as db]))

(defn home-page []
  (layout/render "home.html"))

(defn save-comment [comment]
    (log/info comment)
    (db/create-comment! {:comment comment}))

(defroutes home-routes
  (GET "/" [] (home-page))
  (POST "/api/comments" [text] (save-comment text)))

