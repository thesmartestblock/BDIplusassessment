let arr = [];
let html = "";

main();

async function main() {
  await fetch("http://localhost:8181/v1/alltask")
    .then((response) => response.json())
    .then((data) =>
      data.forEach((k) => {
        addCityToDOM(k.id, k.title, k.description, k.date, k.owner);
      })
    );
}

function addCityToDOM(id, title, description, date, owner) {
  // TODO: MODULE_CITIES
  // 1. Populate the City details and insert those details into the DOM
  let Element = document.createElement("div");
  Element.className = "x";

  let innerHTML = `
      <div class="tile">
      <p><span class="title">${title}</span> <br> 
      agenda: ${description} <br>
      date: ${date} <br>
      owner: ${owner}<br>
      </p>
      <button id=${id} onclick='edit("${id}", "${title}", "${description}", "${date}", "${owner}")'>Edit</button>
      <button id=${id} onclick='deleteTask("${id}")'>Delete</button>
      </div>
      `;
  Element.innerHTML = innerHTML;
  document.getElementById("data").appendChild(Element);
}

// console.log(document.getElementById(id));

function edit(id, title, description, date, owner) {
  document
    .getElementById(id)
    .addEventListener("click", formDOM(id, title, description, date, owner));
}

function formDOM(id, title, description, date, owner) {
  let t = prompt("Edit title ", title);
  let des = prompt("Edit description ", description);
  let d = prompt("Edit date ", date);
  let o = prompt("Edit owner ", owner);
  console.log(t + des + d + o);
  updateTask(id, t, des, d, o);
}

async function updateTask(id, title, description, date, owner) {
  try {
    await fetch("http://localhost:8181/v1/task/"+id, {
    method: "PUT",
    body: JSON.stringify({
      "title": title,
      "description": description,
      "date": date,
      "owner": owner
    }),
    headers:{
      "Content-Type": "application/json"
    }
  })
} catch (error) {
  console.log(error)
}

window.location.reload();
}

async function addTask(){
  let t = prompt("Edit title ");
  let des = prompt("Edit description ");
  let d = prompt("Edit date ");
  let o = prompt("Edit owner ");
  try {
    await fetch("http://localhost:8181/v1/task", {
      method: "POST",
      body: JSON.stringify({
        "title": t,
        "description": des,
        "date": d,
        "owner": o
      }),
      headers:{
        "Content-Type": "application/json"
      }
    })
  } catch (error) {
    console.log(error)
  }
  
  window.location.reload();
}
function deleteTask(id) {
  document
    .getElementById(id)
    .addEventListener("click", delTask(id));
}

async function delTask(id){
  await fetch("http://localhost:8181/v1/task/"+id, {
    method: "DELETE"})
    window.location.reload();
}