(ns junakysely.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[junakysely started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[junakysely has shutdown successfully]=-"))
   :middleware identity})
