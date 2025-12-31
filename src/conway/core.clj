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

(defn step
  [grid]
  (let [sum  (+ (get-in grid [0 0])
               (get-in grid [0 1])
               (get-in grid [0 2])
               (get-in grid [1 0])
               (get-in grid [1 2])
               (get-in grid [2 0])
               (get-in grid [2 1])
               (get-in grid [2 2]))]
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
