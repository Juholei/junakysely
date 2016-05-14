(ns user
  (:require [mount.core :as mount]
            [junakysely.figwheel :refer [start-fw stop-fw cljs]]
            junakysely.core))

(defn start []
  (mount/start-without #'junakysely.core/repl-server))

(defn stop []
  (mount/stop-except #'junakysely.core/repl-server))

(defn restart []
  (stop)
  (start))


