Implement a HTTP/JSON web service for managing achievements for a pre-existing set of games.
Achievements API is used to configure achievements for a specific game by exposing all Achievement-related CRUD operations:
● Create Achievement ​should allow adding a new achievement. It should take all of the relevant parameters in the request and persist this information in the database.
● Get All Game Achievements ​should return all achievements for the supplied game Id. Order of returned achievements is determined by the ​Display Order attribute (smaller value means achievement is displayed first).
● Get Achievement​ returns a single achievement for the supplied achievement id
● Update Achievement ​should allow updating an existing achievement. It should take all of the relevant parameters in the request and persist this information in
the database.
● Delete Achievement ​deletes a single achievement from the database by the
supplied achievement id.
