(ns kees.c08.main
  (:require [reagent.dom :as rdom]
            [re-frame.core :as re-frame]
            [kees.c08.rf :as rf :refer [>evt >evt-now]]
            [kees.c08.dev :as dev]
            [kees.c08.views :as views]))

(defn dev-setup []
  (when dev/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")
        style-el (.getElementById js/document "page-style")]
    (rdom/unmount-component-at-node style-el)
    (rdom/render [views/page-style] style-el)
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/root-panel] root-el)))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn init []
  (>evt-now [::rf/boot])
  (dev-setup)
  (mount-root))
