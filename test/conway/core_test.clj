(ns conway.core-test
  (:require
    [clojure.test :refer [deftest is]]
    [conway.core :as core]))

(deftest t-print-grid-prints-a-grid
  (is (= "   \n   \n   \n" (with-out-str (core/print-grid [[" " " " " "] [" " " " " "] [" " " " " "]])))))

(deftest t-count-neighbors 
  (is (= 0 (core/count-neighbors [[" " " " " "] [" " "O" " "] [" " " " " "]] 1 1)))
  (is (= 1 (core/count-neighbors [[" " " " " "] ["0" "O" " "] [" " " " " "]] 1 1)))
  (is (= 3 (core/count-neighbors [["O" " " "O"] [" " "O" " "] ["O" " " " "]] 1 1)))
  (is (= 4 (core/count-neighbors [[" " "O" " "] [" " " " "O"] ["O" "O" " "]] 1 1))))
