# Delete a meme OwO

**URL** : `/memes/meme/:id`

**Method** : `DELETE`

**Data constraints**

Request URL must contain the id of the meme

**Request example**

`DELETE /memes/meme/23`

## Success Response

**Condition** : If meme has been deleted.

**Code** : `204 NO CONTENT`

## Error Responses

**Condition** : If meme could not be found

**Code** : `404 NOT FOUND`