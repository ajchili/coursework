const text = document.getElementById("text");
const expression = document.getElementById("expression");
const result = document.getElementById("result");
let flags = ["g"];

const runExpression = () => {
  if (!text.value.length) return;
  if (!expression.value.length) return (result.innerText = text.value);
  try {
    let regExp = new RegExp(expression.value, flags.join(""));
    let values = text.value.match(regExp) || [];
    let currentString = text.value;
    result.innerHTML = "";
    values.forEach(value => {
      let index = currentString.indexOf(value);
      result.innerHTML += `${currentString.substring(
        0,
        index
      )}<span class="match">${value}</span>`;
      currentString = currentString.substring(index + value.length);
    });
    result.innerHTML += currentString;
    while (result.innerHTML.includes("\n")) {
      result.innerHTML = result.innerHTML.replace("\n", "<br />");
    }
  } catch (err) {
    result.innerText = "Invalid Expression!";
    console.error(err);
  }
};

const renderFlags = () => {
  document.getElementById("flags").innerText = flags.join(", ");
};

text.onkeydown = text.onkeyup = runExpression;
expression.onkeydown = expression.onkeyup = runExpression;

document.getElementById("close-flags-menu").onclick = e => {
  e.preventDefault();
  document.getElementById("flags-menu").style.display = "none";
};

document.getElementById("flags").oncontextmenu = e => {
  e.preventDefault();
  document.getElementById("flags-menu").style.display = "inherit";
};

let inputs = document.getElementsByTagName("input");
for (let i = 0; i < inputs.length; i++) {
  inputs[i].onchange = e => {
    if (e.target.checked) flags.push(e.target.value);
    else flags = flags.filter(flag => flag !== e.target.value);
    renderFlags();
    runExpression();
  };
}

renderFlags();
text.innerHTML =
  "RegExr was created by gskinner.com, and is proudly hosted by Media Temple.\n\nEdit the Expression & Text to see matches. Roll over matches or the expression for details. PCRE & Javascript flavors of RegEx are supported.\n\nThe side bar includes a Cheatsheet, full Reference, and Help. You can also Save & Share with the Community, and view patterns you create or favorite in My Patterns.\n\nExplore results with the Tools below. Replace & List output custom results. Details lists capture groups. Explain describes your expression in plain English.";
expression.innerHTML = "([A-Z])\\w+";
runExpression();
