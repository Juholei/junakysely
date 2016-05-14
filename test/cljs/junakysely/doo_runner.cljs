(ns junakysely.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [junakysely.core-test]))

(doo-tests 'junakysely.core-test)

