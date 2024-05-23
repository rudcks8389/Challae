document.addEventListener('DOMContentLoaded', () => {
  const draggable = document.querySelectorAll('.draggable');
  const canvas = document.querySelector('#soccer-board');
  const ctx = canvas.getContext('2d');

  const img = new Image();
  img.src = '../img/jersey.png';

  const backgroundImg = new Image();
  backgroundImg.src = '../img/soccer_board.png';

  canvas.width = 670;
  canvas.height = 550;

  const elements = [];

  backgroundImg.onload = () => {
    // 배경 이미지를 캔버스에 그리기
    ctx.drawImage(backgroundImg, 0, 0, canvas.width, canvas.height);
  };

  draggable.forEach(draggable => {
    draggable.addEventListener('dragstart', (event) => {
      const text = event.target.innerText;
      event.dataTransfer.setData('text/plain', text);

      const dragCanvas = document.createElement('canvas');
      const dragCtx = dragCanvas.getContext('2d');
      dragCanvas.width = 100;
      dragCanvas.height = 50;
      dragCtx.fillStyle = 'black';
      dragCtx.fillRect(0, 0, dragCanvas.width, dragCanvas.height);
      dragCtx.drawImage(img, 0, 0, 20, 20);
      dragCtx.fillStyle = 'blue';
      dragCtx.fillText(text, 30, 30);
      event.dataTransfer.setDragImage(dragCanvas, 50, 25);
    });
  });

  canvas.addEventListener('dragover', (event) => {
    event.preventDefault();
  });

  canvas.addEventListener('drop', (event) => {
    event.preventDefault();

    if (elements.length >= 6) {
      alert('더 이상 요소를 추가할 수 없습니다. 최대 6개까지 추가할 수 있습니다.'); // 임시 alert
      return;
    }

    const rect = canvas.getBoundingClientRect();
    const x = (event.clientX - rect.left) * (canvas.width / rect.width);
    const y = (event.clientY - rect.top) * (canvas.height / rect.height);
    const text = event.dataTransfer.getData('text/plain');

    const drawImageWithText = () => {
      ctx.drawImage(img, x - 50, y - 50, 100, 100); // 100x100 크기로 그리기
      ctx.fillStyle = 'black';
      ctx.font = 'bold 16px Arial';
      ctx.fillText(text, x - 20, y + 60); // 이미지 아래에 텍스트 그리기
      elements.push({x: x - 50, y: y - 50, width: 100, height: 100, img, text});
    };

    if (img.complete) {
      drawImageWithText();
    } else {
      img.onload = drawImageWithText;
    }

    console.log(`Drop position: (${x}, ${y})`);
  });

  canvas.addEventListener('dblclick', (event) => {
    const rect = canvas.getBoundingClientRect();
    const x = (event.clientX - rect.left) * (canvas.width / rect.width);
    const y = (event.clientY - rect.top) * (canvas.height / rect.height);

    for (let i = elements.length - 1; i >= 0; i--) {
      const element = elements[i];
      if (x >= element.x && x <= element.x + element.width && y >= element.y && y <= element.y + element.height) {
        elements.splice(i, 1);
        redrawCanvas();
        break;
      }
    }
  });

  function redrawCanvas() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.drawImage(backgroundImg, 0, 0, canvas.width, canvas.height); // 배경 이미지 다시 그리기
    elements.forEach(element => {
      ctx.drawImage(element.img, element.x, element.y, 100, 100);
      ctx.fillText(element.text, element.x + 30, element.y + 110); // 이미지 아래에 텍스트 다시 그리기
    });
  }
});

/* ------------------------------------- */

// 등록 버튼 누를 때 캔버스 상태 저장
function captureCanvas() {
  let canvas = document.querySelector('#soccer-board');
  let canvasData = canvas.toDataURL('image/jpeg',0.1); // 캔버스의 이미지 데이터를 Base64로 인코딩
  document.querySelector('#canvasData').value = canvasData; // hidden 필드에 이미지 데이터 설정

  console.log(canvasData);

}

/* ------------------------------------- */
const canvas = document.getElementById('soccer-board');
const ctx = canvas.getContext('2d');
const savedStates = {};

// Save the canvas state
function saveImage() {
  const selectedOption = document.querySelector('input[name="option"]:checked');
  if (selectedOption) {
    const dataURL = canvas.toDataURL();
    savedStates[selectedOption.value] = dataURL;
    alert(`Canvas saved for option ${selectedOption.value}`);
  } else {
    alert("Please select an option before saving.");
  }
}

// Load the canvas state
document.querySelectorAll('input[name="option"]').forEach((radio) => {
  radio.addEventListener('change', function () {
    if (savedStates[this.value]) {
      const img = new Image();
      img.src = savedStates[this.value];
      img.onload = function () {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
      };
    }
  });
});

// Save button event listener
document.getElementById('saveButton').addEventListener('click', saveImage);

// Example drawing function (for demonstration purposes)
function drawSomething() {
  ctx.fillStyle = "red";
  ctx.fillRect(50, 50, 100, 100);
}

// Call the draw function for demonstration
drawSomething();



