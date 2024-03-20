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
  counterDisplay.textContent = `Right position counter: ${counter}`;
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
  // Getting all items except currently dragging and making array of them
  let siblings = [...sortableList.querySelectorAll(".item:not(.dragging)")];
  // Finding the sibling after which the dragging item should be placed
  let nextSibling = siblings.find(sibling => {
      return e.clientX <= sibling.offsetLeft + sibling.offsetWidth / 2;
  });
  // Inserting the dragging item before the found sibling
  sortableList.insertBefore(draggingItem, nextSibling);
}

sortableList.addEventListener("dragover", initSortableRowList);
sortableList.addEventListener("dragenter", e => e.preventDefault());

