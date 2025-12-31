(ns conway.core)

(defn print-cell
  [cell]
  (if cell
    (print "O")
    (print " ")))

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

(defn step
  [grid]
  grid)

(defn -main [& _args]
  (loop [grid (vec (repeat 3 (vec (repeat 3 true))))]
    (println (apply str (repeat (count grid) \-)))
    (Thread/sleep 1000)
    (print-grid grid)
    (recur (step grid))))
