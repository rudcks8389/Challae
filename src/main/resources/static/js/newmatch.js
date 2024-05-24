/*
 * 캔버스에 전략판 그리기
 *
 * */
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
      alert('최대 6개까지 추가할 수 있습니다'); // 임시 alert
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

    console.log(`드랍 좌표 체크: (${x}, ${y})`);
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

// // 등록 버튼 누를 때 캔버스 상태 저장
// function captureCanvas() {
//   let canvas = document.querySelector('#soccer-board');
//   let canvasData = canvas.toDataURL('image/jpeg',0.1); // 캔버스의 이미지 데이터를 Base64로 인코딩
//   document.querySelector('#canvasData').value = canvasData; // hidden 필드에 이미지 데이터 설정
//
//   console.log(canvasData);
//
// }

/* ------------------------------------- */
/*
 * form 태그 안에 있는 등록 버튼을 누르면 캔버스 그림 상태를 서버에 전달해 저장
 *
 * */
document.querySelector('#createForm').addEventListener('submit', function(event) {
  event.preventDefault();
  saveCanvas();
});

function saveCanvas() {
  const canvas = document.querySelector('#soccer-board');
  canvas.toBlob(function(blob) {
    const formData = new FormData();

    // 유니크한 파일 이름 생성
    const uniqueFileName = 'canvas_' + new Date().getTime() + '.png';
    formData.append('canvasImage', blob, uniqueFileName);

    fetch('/club/uploadCanvas', {
      method: 'POST',
      body: formData
    }).then(response => {
      if (response.ok) {
        response.json().then(data => {
          document.querySelector('#canvasData').value = data.filePath;
          alert('JS단 캔버스 저장 완료');
          // 폼을 자동 제출
          document.querySelector('#createForm').submit();
        });
      } else {
        alert('JS단 캔버스 저장 실패');
      }
    }).catch(error => {
      console.error('Error:', error);
    });
  }, 'image/png');
}

/* ------------------------------------- */
// textarea 글자 수 제한
$('#match-notice').keyup(function (e) {
  let content = $(this).val();

  // 글자수 세기
  if (content.length == 0 || content == '') {
    $('.textCount').text('0자');
  } else {
    $('.textCount').text(content.length + '자');
  }

  // 글자수 제한
  if (content.length > 100) {
    // 100자 부터는 타이핑 되지 않도록
    $(this).val($(this).val().substring(0, 100));
    // 100자 넘으면 알림창 뜨도록
    alert('글자수는 100자까지 입력 가능합니다.');
  };
});


