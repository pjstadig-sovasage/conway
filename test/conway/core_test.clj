(ns conway.core-test
  (:require
    [clojure.test :refer [deftest is]]
    [conway.core :as core]))

(deftest t-print-grid-prints-a-grid
  (is (= "   \n   \n   \n" (with-out-str (core/print-grid [[" " " " " "]
                                                           [" " " " " "]
                                                           [" " " " " "]]))))
  (is (= "O  \n  O\n   \n" (with-out-str (core/print-grid [["O" " " " "]
                                                           [" " " " "O"]
                                                           [" " " " " "]])))))

(deftest t-count-neighbors
  (is (= 0 (core/count-neighbors [[" " " " " "]
                                  [" " "O" " "]
                                  [" " " " " "]] 1 1)))
  (is (= 1 (core/count-neighbors [[" " " " " "]
                                  ["O" "O" " "]
                                  [" " " " " "]] 1 1)))
  (is (= 3 (core/count-neighbors [["O" " " "O"]
                                  [" " "O" " "]
                                  ["O" " " " "]] 1 1)))
  (is (= 4 (core/count-neighbors [[" " "O" " "]
                                  [" " " " "O"]
                                  ["O" "O" " "]] 1 1)))
  (is (= 1 (core/count-neighbors [[" " "O"]
                                  [" " " "]] 1 1))))

(deftest t-count-neighbors-at-edge
  (is (= 0 (core/count-neighbors [[" " "O" " "]
                                  [" " " " " "]
                                  [" " " " " "]] 0 1)))
  (is (= 1 (core/count-neighbors [[" " "O" " "]
                                  ["O" " " " "]
                                  [" " " " " "]] 0 1)))
  (is (= 3 (core/count-neighbors [["O" "O" " "]
                                  ["O" "O" " "]
                                  [" " "O" " "]] 0 1)))
  (is (= 1 (core/count-neighbors [[" " " " " "]
                                  [" " "O" " "]
                                  [" " " " " "]] 0 2))))

(deftest t-step
  (is (= [[" " " " " "]
          [" " " " " "]
          [" " " " " "]]
         (core/step [[" " " " " "]
                     [" " "O" " "]
                     [" " " " " "]])))
  (is (= [[" " "O" " "]
          [" " "O" " "]
          [" " " " " "]]
         (core/step [[" " "O" " "]
                     ["O" " " "O"]
                     [" " " " " "]])))
  (is (= [[" " "O" " "]
          [" " "O" " "]
          [" " "O" " "]]
         (core/step [[" " " " " "]
                     ["O" "O" "O"]
                     [" " " " " "]])))
  (is (= [[" " " " " "]
          ["O" "O" "O"]
          [" " " " " "]]
         (core/step [[" " "O" " "]
                     [" " "O" " "]
                     [" " "O" " "]])))
  (is (= [["O" "O" " "]
          ["O" "O" " "]
          [" " " " " "]]
         (core/step [["O" "O" " "]
                     ["O" "O" " "]
                     [" " " " " "]]))))

(deftest t-tick
  (is (= " O \n O \n O \n"
        (with-out-str
          (core/tick [[" " " " " "]
                      ["O" "O" "O"]
                      [" " " " " "]]))))
  (is (= [[" " "O" " "]
          [" " "O" " "]
          [" " "O" " "]]
        (core/tick [[" " " " " "]
                    ["O" "O" "O"]
                    [" " " " " "]]))))

