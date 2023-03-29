package commands

import (
	"check23slackbot/memes"
	"github.com/slack-go/slack"
)

func HandleSlashCommand(command slack.SlashCommand, client *slack.Client) error {
	switch command.Command {
	case "/meme":
		return handleMemeCommand(command, client)
	}

	return nil
}

func handleMemeCommand(command slack.SlashCommand, client *slack.Client) error {

	meme := memes.Meme{}
	meme.GetMeme()
	attachment := slack.Attachment{}
	attachment.ImageURL = meme.GetImageUrl()
	attachment.Title = meme.Title
	attachment.TitleLink = meme.GetImageUrl()

	_, _, err := client.PostMessage(command.ChannelID, slack.MsgOptionText("Here is your meme", false), slack.MsgOptionAttachments(attachment))

	return err
}
