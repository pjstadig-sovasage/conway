(ns conway.core
  (:require
   [clojure.string :as str]))

(defn print-grid [grid]
   (println (str/join "\n" (map #(str/join %) grid))))

(def neighbors [[-1 -1] [-1 0] [-1 1] [0 -1] [0 1] [1 -1] [1 0] [1 1]])

(defn get-neighbors
  [grid row col]
  (for [[x y] neighbors] (get-in grid [(+ row x) (+ col y)])))

(defn count-neighbors [grid row col]
  (transduce (map {" " 0 "O" 1}) + 0 (get-neighbors grid row col)))
