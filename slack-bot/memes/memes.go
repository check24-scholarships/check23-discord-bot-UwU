package memes

import (
	"encoding/json"
	"io"
	"log"
	"net/http"
	"os"
)

type Meme struct {
	Id     int    `json:"id"`
	Uuid   string `json:"uuid"`
	Title  string `json:"title"`
	Upload int    `json:"upload"`
}

func (m *Meme) GetMeme() {
	url := os.Getenv("MEME_API") + "memes/"
	request, err := http.NewRequest("GET", url+"meme", nil)
	if err != nil {
		log.Fatal(err)
	}

	response, err := http.DefaultClient.Do(request)

	if err != nil {
		log.Fatal(err)
	}

	defer response.Body.Close()

	body, err := io.ReadAll(response.Body)

	if err != nil {
		log.Fatal(err)
	}
	log.Println(string(body))
	json.Unmarshal(body, &m)

}

func (m *Meme) GetImageUrl() string {
	url := os.Getenv("MEME_API") + "memes/"
	return url + "meme/" + m.Uuid + ".jpg"
}
