(ns predicates)

(defn sum-f [f g x]
  (+ (f x) (g x)))

(defn less-than [n]
  (fn [k] (< k n)))

(defn equal-to [n]
  (fn [k] (== k n)))

(defn set->predicate [a-set]
  (fn [x] (contains? a-set x)))

(defn pred-and [pred1 pred2]
  (fn [x] (and (pred1 x) (pred2 x))))

(defn pred-or [pred1 pred2]
  (fn [x] (or (pred1 x) (pred2 x))))

(defn whitespace? [character]
  (Character/isWhitespace character))

(defn blank? [string]
  (cond
   (or
    (= nil string)
    (== 0 (count string))
    (every? whitespace? string))
   true
   :else
   false))

(defn has-award? [book award]
  (contains? (:awards book) award))

(defn HAS-ALL-THE-AWARDS? [book awards]
  (let [book-awards (:awards book)]
    (every? (set->predicate book-awards) awards)))

(defn my-some [pred a-seq]
  (first (map pred (filter pred a-seq))))

(defn my-every? [pred a-seq]
  (= a-seq (filter pred a-seq)))

(defn prime? [n]
  (let [divides-by-n? (fn [divisor] (= 0 (mod n divisor)))
        prime-upper-bound (+ 1 (int (/ n 2)))]
    (not (some divides-by-n? (range 2 prime-upper-bound)))))

;^^
