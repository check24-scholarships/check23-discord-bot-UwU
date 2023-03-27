const getMeme = async () =>{
    const meme = await (await fetch("https://check23.lcarilla.de/memes/meme")).json()
    document.querySelector(".memearea").innerHTML += `
    <div class="meme" title="${meme.title}">
        <img src="https://check23.lcarilla.de/memes/meme/${meme.uuid}.jpg">
    </div>
    `
}
const handleInfiniteScroll = () => {
    window.removeEventListener("scroll", handleInfiniteScroll);
    setTimeout(() => {
      if ((window.innerHeight + window.pageYOffset + 100) >= document.body.offsetHeight) for (let i = 0; i < 5; i++) getMeme()
      window.addEventListener("scroll", handleInfiniteScroll)
    }, 500);
  };
window.addEventListener("scroll", handleInfiniteScroll);
(async () => { while (document.body.scrollHeight <= window.innerHeight) await getMeme();})()