-- :name create-comment! :! :n
-- :doc creates a comment
INSERT INTO comments
(comment)
VALUES (:comment)

-- :name get-comment :? :1
-- :doc retrieve a comment with the given id.
SELECT * FROM comments
WHERE id = :id

