(ns conway.core-test
  (:require
    [clojure.test :refer [deftest is]]
    [conway.core :as core]))

(deftest t-print-grid-prints-a-grid
  (is (= "   \n   \n   \n" (core/print-grid [[0 0 0] [0 0 0] [0 0 0]]))))
