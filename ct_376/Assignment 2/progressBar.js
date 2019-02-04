let progressBars = document.getElementsByClassName("progressBar");
const height = document.body.scrollHeight - window.innerHeight;

if (progressBars.length) {
	let progressBar = progressBars[0];
	document.onscroll = () => {
		let yOffset = window.pageYOffset;
		let position = yOffset / height * 100;
		progressBar.style.width = `${position}%`;
	};
} else throw new Error("No Progress Bar present!");