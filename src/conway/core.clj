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

(defn step
  [grid]
  (let [sum (examine-cell grid 1 1)]  
    (if (zero? (get-in grid [1 1]))
      (if (= sum 3)
        (assoc-in grid [1 1] 1)
        grid)
      (cond
        (< sum 2) (assoc-in grid [1 1] 0)
        (> sum 3) (assoc-in grid [1 1] 0)
        :else grid))))

(defn -main [& _args]
  (loop [grid [[0 0 0] [0 1 0] [0 0 0]]]
    (println (apply str (repeat (count grid) \-)))
    (Thread/sleep 1000)
    (print-grid grid)
    (recur (step grid))))
