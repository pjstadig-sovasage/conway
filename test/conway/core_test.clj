(ns conway.core-test
  (:require
    [clojure.test :refer [deftest is]]
    [conway.core :as core]))

(deftest t-step
  (let [grid [[0 0 0] [0 0 0] [0 0 0]]]
    (is (= grid (core/step grid)))))

(deftest t-step-live-cell-dies
  (let [grid [[0 0 0] [0 1 0] [0 0 0]]]
    (is (= [[0 0 0] [0 0 0] [0 0 0]] (core/step grid)))))

(deftest t-step-dead-cell-lives
  (let [grid [[0 0 1] [0 0 0] [1 1 0]]]
    (is (= [[0 0 1] [0 1 0] [1 1 0]] (core/step grid)))))
