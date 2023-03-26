# Publish a meme OwO

**URL** : `/memes/meme`

**Method** : `POST`

**Data constraints**

request body must contain a base 64 encoded image and a title (max 50mb)

**Request example**

```json
{ 
    "image": "somebase64",
    "title": "check23 UwU"
}
```

## Success Response

**Condition** : If image has been uploaded

**Code** : `200 OK`

## Error Responses

**Condition** : If requests body is incomplete or too large

**Code** : `400 BAD REQUEST`