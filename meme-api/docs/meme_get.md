# Get a random a meme UwU

**URL** : `/memes/meme`

**Method** : `GET`

**Request example**

`GET /memes/meme`

## Success Response

**Code** : `200 OK`

The response body will be the json encoded data about the meme

```json
{ 
    "id": "some_int",
    "uuid": "some_uuid",
    "title": "some_title",
    "created": "UNIX_TIMESTAMP()"
}
```

The meme image is statically served:

`/memes/meme/:uuid.jpg`