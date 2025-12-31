(ns conway.core-test
  (:require
    [clojure.test :refer [deftest is]]
    [conway.core :as core]))

(deftest t-step
  (let [grid [[0 0 0] [0 0 0] [0 0 0]]]
    (is (= grid (core/step grid)))))
