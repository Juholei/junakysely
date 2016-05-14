(ns junakysely.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [junakysely.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[junakysely started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[junakysely has shutdown successfully]=-"))
   :middleware wrap-dev})
