(ns conway.core)

(defn print-cell
  [cell]
  (if (zero? cell)
    (print " ")
    (print "O")))

(defn print-row
  [row]
  (loop [i 0]
    (when (< i (count row))
      (print-cell (get row i))
      (recur (inc i))))
  (println))

(defn print-grid
  [grid]
  (loop [i 0]
    (when (< i (count grid))
      (print-row (get grid i))
      (recur (inc i))))
  (flush))

(defn examine-cell
  [grid row col]
  (let [sum  (+ (get-in grid [(dec row) (dec col)])
               (get-in grid [(dec row) col])
               (get-in grid [(dec row) (inc col)])
               (get-in grid [row (dec col)])
               (get-in grid [row (inc col)])
               (get-in grid [(inc row) (dec col)])
               (get-in grid [(inc row) col])
               (get-in grid [(inc row) (inc col)]))]
    sum))

(defn update-cell
  [grid row col sum]
  (if (zero? (get-in grid [row col]))
    (if (= sum 3)
      (assoc-in grid [row col] 1)
      grid)
    (cond
      (< sum 2) (assoc-in grid [row col] 0)
      (> sum 3) (assoc-in grid [row col] 0)
      :else grid)))

(defn step-cell
  [new-grid grid row col]
  (update-cell new-grid row col (examine-cell grid row col)))

(defn step-row
  [new-grid grid row]
  (reduce
    (fn [new-grid col]
      (step-cell new-grid grid row col))
    new-grid
    (range (count (get grid row)))))

(defn step
  [grid]
  (reduce
    (fn [new-grid row]
      (step-row new-grid grid row))
    grid
    (range (count grid))))

(defn -main [& _args]
  (loop [grid [[0 0 0] [0 1 0] [0 0 0]]]
    (println (apply str (repeat (count grid) \-)))
    (Thread/sleep 1000)
    (print-grid grid)
    (recur (step grid))))
