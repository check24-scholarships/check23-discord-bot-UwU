const PREFIX = "/memes"
const express = require("express")
const sharp = require("sharp")
const { query } = require("./database")
const { v4: uuid } = require('uuid')
const fs = require("fs")
const app = express()
const env = require("dotenv").config().parsed

console.log(env.port)
app.listen(env.port)
app.use(require("cors")({
    origin: "*"
}))
app.use(express.json({ limit: "50mb" }))
app.use(PREFIX + "/meme", express.static("./upload"))
const saveImage = (toSave, data) => {
    const buffer = Buffer.from(toSave, "base64");
    sharp(buffer).resize(data.wres, data.hres, {
        withoutEnlargement: true, fit: "inside"
    }).jpeg({ quality: data.quality }).toFile(data.path);
}
app.get(PREFIX + "/coffee", (req, res) => { res.status(418).send() })
app.post(PREFIX + '/meme', async (req, res) => {
    try {
        const id = uuid()
        const { image, title } = req.body
        saveImage(image, {
            path: `./upload/${id}.jpg`,
            quality: 80,
            wres: 500,
            hres: 500,
        })
        await query("INSERT INTO memes (uuid, title) VALUES (?, ?)", [id, title])
        res.status(200).send()
    } catch (e) {
        res.status(500).send()
        console.log(e)
    }
});
app.get(PREFIX + "/meme", async (req, res) => {
    try {
        const maxid = (await query(`SELECT MAX(id) AS max FROM memes`)).rows[0].max
        const meme = (await query(`SELECT * FROM memes WHERE id > ?`,
            [Math.floor(Math.random() * (maxid))])).rows[0]
        res.status(200).send(meme)
    } catch (e) {
        res.status(500).send()
        console.log(e)
    }
})
app.delete(PREFIX + "/meme/:id", async (req, res) => {
    try {
        const id = req.params.id
        const uuid = (await query(`SELECT uuid FROM memes WHERE id = ?`, [id])).rows[0]?.uuid
        if (uuid === undefined || fs.existsSync(`./upload/${uuid}.jpg`)) res.status(404).send()
        fs.unlinkSync(`./upload/${uuid}.jpg`)
        await query(`DELETE FROM memes WHERE id = ?`, [id])
        res.status(204).send()
    } catch (e) {
        res.status(500).send()
        console.log(e)
    }
})