function openSidebar() {
  document.getElementById("sidebar").style.left = "0";
  checkOrder();
}

function closeSidebar() {
  document.getElementById("sidebar").style.left = "-300px";
}


function checkOrder() {
  const items = sortableList.querySelectorAll(".item");
  let counter = 0;
  items.forEach((item, index) => {
    console.log(`Item ${index + 1}, ID: ${item.id}`);
    if (index + 1 == item.id) {
      counter++;
    }
  });
  console.log(counter);

  const counterDisplay = document.querySelector("#counterDisplay");

  // if counter equals 5 lock the list

  if (counter == 5) {
    counterDisplay.textContent = "You have guessed the right order!";
    sortableList.removeEventListener("dragover", initSortableRowList);
    items.forEach(item => {
      item.setAttribute("draggable", "false");
    });
  } else {
    counterDisplay.textContent = `Right position counter: ${counter}`;
  }
}

const sortableList = document.querySelector(".sortable-list");
const items = sortableList.querySelectorAll(".item");

items.forEach(item => {
  item.addEventListener("dragstart", () => {
      setTimeout(() => item.classList.add("dragging"), 0);
  });
  item.addEventListener("dragend", () => item.classList.remove("dragging"));
});

const initSortableRowList = (e) => {
  e.preventDefault();
  const draggingItem = document.querySelector(".dragging");
  let siblings = [...sortableList.querySelectorAll(".item:not(.dragging)")];
  let nextSibling = siblings.find(sibling => {
      return e.clientX <= sibling.offsetLeft + sibling.offsetWidth / 2;
  });
  sortableList.insertBefore(draggingItem, nextSibling);
  checkOrder();
}

sortableList.addEventListener("dragover", initSortableRowList);
sortableList.addEventListener("dragenter", e => e.preventDefault());

