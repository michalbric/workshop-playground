(ns workshop-playground.core
  (:gen-class)
  (:require [clj-http.client :as client])
  (:import (java.awt Point)
           (java.util Date)))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;;;;;;;;;;;;;;;;;;;;;
;; Clojure(Script) ;;
;;;;;;;;;;;;;;;;;;;;;


;;;;;;;;;;;
;; about ;;
;;;;;;;;;;;

; @author Rich Hickey
; Cognitect https://cognitect.com/technologies.html -> Clojure(Script), Datomic
; https://github.com/clojure

; used @
; Clojure       https://clojure.org/community/companies
; ClojureScript https://clojurescript.org/community/companies

;;;;;;;;;;;;;;;
;; resources ;;
;;;;;;;;;;;;;;;

;; videos
; ClojureTV                     https://www.youtube.com/channel/UCaLlzGqiPE2QRj6sSOawJRg
; Clojure for Java Programmers: https://www.youtube.com/watch?v=P76Vbsk_3J0

;; books
; Clojure for the Brave and True  https://www.braveclojure.com/
; Joy of Clojure, Getting Clojure
; Other                           https://clojure.org/community/books

;; setup
; Leiningen    https://leiningen.org/
; IntelliJ IDE https://cursive-ide.com/










;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Language Introduction ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;
;; syntax ;;
;;;;;;;;;;;;

"hi"
3
(+ 1 2)
(def x 8)


;;;;;;;;;;;;;
;; scalars ;;
;;;;;;;;;;;;;

;; integers
31

;; doubles
2.3

;; ratios
10/3

;; symbols
'a

;; keywords
:key
:bob/key
::key

;; nil
nil

;; true/false && truthiness
true false

;; functions
inc

;;;;;;;;;;;;;;;;;
;; collections ;;
;;;;;;;;;;;;;;;;;

;; lists
'(1 2 3

;; vectors
  ["a" "b" "c"])

;; sets && clojure.set
#{1 2 3}

;; maps
{:a "first" :b "second"}
;; assoc, update, assoc-in, update-in
;(update-in my-map [:b] clojure.string/upper-case)
;(assoc-in {:a "fst" :b {:c "third"}} [:b :c] "bla")

;; records
(defrecord Person [name email])
(->Person "bob" "robert@gmail.com")


;;;;;;;;;;;;;;;;;;
;; control flow ;;
;;;;;;;;;;;;;;;;;;

;; if/cond
(if true "yup" "nope")

(cond
    (and (= 0 (mod x 3)) (= 0 (mod x 5))) "fizzbuzz"
    (= 0 (mod x 3)) "fizz"
    (= 0 (mod x 5)) "buzz"
    :else x)

;;;;;;;;;;;;;;;
;; functions ;;
;;;;;;;;;;;;;;;

;; definiting, literals, let variadic
(fn [x] (* 2 x))

(def twice (fn [x] (* 2 x)))

(defn twice [x] (* 2 x))

#(* 2 %)


(defn hello
  ([] "hello, world!")
  ([name] (str "hello, " name "!"))
  ([fst & rest ] (str "hello, " fst " and also " (clojure.string/join ", " rest) "!")))


;;;;;;;;;;;;;;;;
;; mutability ;;
;;;;;;;;;;;;;;;;

;; transient

(persistent! (transient [1 2 3]))
(into [1 2] [3 4 5 6])

;; atoms
(def counter (atom 0))
; deref, @, reset!, swarp!

;; refs
(def email (ref "bob@gmail.com"))
(def name (ref "bob"))
; deref, ref-set, dosync, alter, throw
(dosync
  (ref-set name "tim")
  (throw (new Exception "failed"))
  (ref-set email "timmothy@gmail.com"))

;; agents
(def nums (agent []))
(send agent conj 2)
(restart-agent nums [1])


;; watchers
(add-watch nums :nums-observer
           (fn [key atom old-state new-state]
             (prn (str "nums change from " old-state " to " new-state))))

;;;;;;;;;;;;;;;
;; recursion ;;
;;;;;;;;;;;;;;;

;; basic
(defn contains-val? [coll val]
  (cond 
    (empty? coll) false
    (= (first coll) val) true
    :else (contains-val? (rest coll) val)))


;; recur
(defn contains-val? [coll val]
  (cond 
    (empty? coll) false
    (= (first coll) val) true
    :else (recur (rest coll) val)))

;; loop
(defn len [coll]
  (loop [remaining coll
         acc 0]
    (if (empty? remaining)
      acc
      (recur (rest remaining) (inc acc)))))

;;;;;;;;;;;;;;;;;;
;; java interop ;;
;;;;;;;;;;;;;;;;;;

;; static
(Math/max 22 11)
(Math/PI)

;; instances
(new Date)
(.getHours (new Date))

(.y (new Point 10 250))


;;;;;;;;;;;;;;;
;; threading ;;
;;;;;;;;;;;;;;;

(-> [20 884 912 49 234 590401 19285 95014 5085 203985 203958 23]
    sort
    reverse
    (#(filter odd? %)))


(filter odd? (reverse (sort [20 884 912 49 234 590401 19285 95014 5085 203985 203958 23])))

;;;;;;;;;;;;;;;;;;;
;; homoiconicity ;;
;;;;;;;;;;;;;;;;;;;
; (eval (seq (assoc (vec '(def x 2)) 1 'y)))