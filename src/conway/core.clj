(ns conway.core 
  (:require
   [clojure.string :as str]))

(defn print-grid [grid]
   (println (str/join "\n" (map #(str/join %) grid))))
    
(defn count-neighbors [grid row col])
  
