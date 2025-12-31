(ns conway.core
  (:require
   [clojure.string :as str]))

(defn print-grid [grid]
   (println (str/join "\n" (map #(str/join %) grid))))

(def offsets [[-1 -1] [-1 0] [-1 1] [0 -1] [0 1] [1 -1] [1 0] [1 1]])

(defn offset-from
  [row col]
  (fn [[x y]]
    [(max (min (+ row x) 2) 0) (max (min (+ col y) 2) 0)]))

(defn get-neighbors
  [grid row col]
  (let [neighbors (disj (into #{} (map (offset-from row col)) offsets) [row col])]
    (for [[x y] neighbors]
      (get-in grid [x y]))))

(defn count-neighbors [grid row col]
  (transduce (map {" " 0 "O" 1}) + 0 (get-neighbors grid row col)))

(def step-value
  {" " {3 "O"}
   "O" {2 "O"
        3 "O"}})

(defn step-cell [new-grid grid i j]
  (vswap! new-grid assoc-in [i j]
    (get-in step-value [(get-in grid [i j])
                        (count-neighbors grid i j)]
      " ")))

(defn step-row [new-grid grid i]
  (let [j        (volatile! 0)]
    (while (< @j (count (get grid i)))
      (step-cell new-grid grid i @j)
      (vswap! j inc))
    @new-grid))

(defn step [grid]
  (let [i (volatile! 0)
        new-grid (volatile! grid)]
    (while (< @i (count grid))
      (vreset! new-grid (step-row new-grid grid @i))
      (vswap! i inc))
    @new-grid))

(defn tick [grid]
  (let [new-grid (step grid)]
    (print-grid new-grid)
    new-grid))
