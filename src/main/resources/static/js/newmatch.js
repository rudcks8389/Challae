/* ------------------------------------- */
/*
 * 캔버스에 전략판 그리기 관련 모든 코드
 *
 * */
document.addEventListener('DOMContentLoaded', () => {
    const draggable = document.querySelectorAll('.draggable');
    const canvas = document.querySelector('#soccer-board');
    const clearButton = document.querySelector('#clear-button');
    const ctx = canvas.getContext('2d');

    const img = new Image();
    img.src = '../img/jersey.png';

    const backgroundImg = new Image();
    backgroundImg.src = '../img/soccer_board.png';

    canvas.width = 670;
    canvas.height = 550;

    const elements = [];
    const curves = [];
    let isDrawing = false;
    let currentCurve = [];
    let loadedImage = null;

    // 캔버스에서 우클릭 메뉴창 금지
    canvas.addEventListener("contextmenu", e => e.preventDefault());

    backgroundImg.onload = () => {
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

    clearButton.addEventListener('click', () => {
        elements.length = 0;
        curves.length = 0;
        loadedImage = null;
        redrawCanvas();
    });

    canvas.addEventListener('dragover', (event) => {
        event.preventDefault();
    });

    canvas.addEventListener('drop', (event) => {
        event.preventDefault();

        if (elements.length >= 6) {
            Swal.fire({
                icon: 'error',
                title: '더 이상 추가할 수 없습니다.︎',
                text: '총 6개까지 추가 가능합니다.',
            });
            return;
        }

        const rect = canvas.getBoundingClientRect();
        const x = (event.clientX - rect.left) * (canvas.width / rect.width);
        const y = (event.clientY - rect.top) * (canvas.height / rect.height);
        const text = event.dataTransfer.getData('text/plain');

        const drawImageWithText = () => {
            ctx.drawImage(img, x - 50, y - 50, 100, 100);
            ctx.fillStyle = 'black';
            ctx.font = 'bold 16px Arial';
            ctx.fillText(text, x - 20, y + 60);
            elements.push({ x: x - 50, y: y - 50, width: 100, height: 100, img, text });
        };

        if (img.complete) {
            drawImageWithText();
        } else {
            img.onload = drawImageWithText;
        }

    });

    canvas.addEventListener('mousedown', (event) => {
        isDrawing = true;
        currentCurve = [];
        const rect = canvas.getBoundingClientRect();
        const x = (event.clientX - rect.left) * (canvas.width / rect.width);
        const y = (event.clientY - rect.top) * (canvas.height / rect.height);
        currentCurve.push({ x, y });
    });

    canvas.addEventListener('mousemove', (event) => {
        if (!isDrawing) return;

        const rect = canvas.getBoundingClientRect();
        const x = (event.clientX - rect.left) * (canvas.width / rect.width);
        const y = (event.clientY - rect.top) * (canvas.height / rect.height);
        currentCurve.push({ x, y });
        redrawCanvas();
    });

    canvas.addEventListener('mouseup', () => {
        if (!isDrawing) return;
        isDrawing = false;
        curves.push([...currentCurve]);
        currentCurve = [];
        redrawCanvas();
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
                return;
            }
        }

        for (let i = curves.length - 1; i >= 0; i--) {
            const curve = curves[i];
            for (let j = 1; j < curve.length; j++) {
                const p1 = curve[j - 1];
                const p2 = curve[j];
                const distance = Math.sqrt((p2.x - p1.x) ** 2 + (p2.y - p1.y) ** 2);
                if (distance < 10 && Math.abs((p2.y - p1.y) * x - (p2.x - p1.x) * y + p2.x * p1.y - p2.y * p1.x) / distance < 5) {
                    curves.splice(i, 1);
                    redrawCanvas();
                    return;
                }
            }
        }
    });

    function redrawCanvas() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        if (loadedImage) {
            ctx.drawImage(loadedImage, 0, 0, canvas.width, canvas.height);
        } else {
            ctx.drawImage(backgroundImg, 0, 0, canvas.width, canvas.height);
        }

        elements.forEach(element => {
            ctx.drawImage(element.img, element.x, element.y, 100, 100);
            ctx.fillText(element.text, element.x + 30, element.y + 110);
        });

        curves.forEach(curve => {
            if (curve.length < 2) return;
            ctx.beginPath();
            ctx.moveTo(curve[0].x, curve[0].y);
            for (let i = 1; i < curve.length; i++) {
                ctx.lineTo(curve[i].x, curve[i].y);
            }
            ctx.stroke();
        });

        if (isDrawing && currentCurve.length >= 2) {
            ctx.strokeStyle = '#FF0000';
            ctx.lineWidth = 3;
            ctx.beginPath();
            ctx.moveTo(currentCurve[0].x, currentCurve[0].y);
            for (let i = 1; i < currentCurve.length; i++) {
                ctx.lineTo(currentCurve[i].x, currentCurve[i].y);
            }
            ctx.stroke();
        }
    }

    /**
     * 프리셋 불러오기 버튼 관련 이벤트
     */
    document.querySelector('#loadButton').addEventListener('click', function () {
        const selectedOption = document.querySelector('input[name="option"]:checked');
        if (!selectedOption) {
            Swal.fire({
                icon: 'warning',
                title: '✔︎',
                text: '불러올 프리셋 조건을 선택해주세요',
            });
            return;
        }

        const type = selectedOption.value;

        fetch(`/club/loadCanvas?type=${type}`, {
            method: 'GET'
        })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert(data.error);
                    return;
                }
                loadedImage = new Image();
                loadedImage.onload = function () {
                    ctx.clearRect(0, 0, canvas.width, canvas.height);
                    ctx.drawImage(loadedImage, 0, 0, canvas.width, canvas.height);

                    elements.length = 0;
                    curves.length = 0;

                    if (data.elements) {
                        data.elements.forEach(e => {
                            elements.push({ x: e.x, y: e.y, width: 100, height: 100, img: img, text: e.text });
                        });
                    }

                    redrawCanvas();
                    document.querySelector('#presetName').value = data.presetName;
                };
                loadedImage.src = `/upload/preset/${data.filePath}`;
            })
            .catch(error => console.error('Error:', error));
    });
});




/* ------------------------------------- */
/*
 * form 태그 안에 있는 등록 버튼을 누르면 캔버스 그림 상태를 서버에 전달해 저장
 *
 * */
document.querySelector('#createForm').addEventListener('submit', function (event) {
    event.preventDefault();
    saveCanvas();
});

// 클라이언트 사이드 렌더링
function saveCanvas() {
    const selectBox = document.querySelector('#fieldSelect');
    const reservationDate = document.querySelector('#reservationDate');
    const reservationTime = document.querySelector('#reservationTime');

    // select 박스가 선택되지 않은 경우 경고 메시지 표시
    if (selectBox.selectedIndex === 0) {
        Swal.fire({
            icon: 'warning',
            title: '경고',
            text: '[필수] 구장을 선택해주세요.',
        });
        selectBox.focus();
        return;
    }
    // 등록 날짜가 없거나 공백이면 경고
    if (!reservationDate.value.trim()) {
        Swal.fire({
            icon: 'warning',
            title: '경고',
            text: '[필수] 날짜를 선택해주세요.',
        });
        reservationDate.focus();
        return;
    }
    // 등록 시간이 없거나 공백이면 경고
    if (!reservationTime.value.trim()) {
        Swal.fire({
            icon: 'warning',
            title: '경고',
            text: '[필수] 상세시간을 입력해주세요.',
        });
        reservationTime.focus();
        return;
    }

    const canvas = document.querySelector('#soccer-board');
    canvas.toBlob(function (blob) {
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
                    Swal.fire({
                        icon: 'success',
                        title: '등록 완료',
                        text: '일정이 등록되었습니다.',
                    });
                    // 폼을 자동 제출
                    document.querySelector('#createForm').submit();
                });
            } else {
                Swal.fire({
                    icon: 'warning',                         // Alert 타입
                    title: '등록 실패',         // Alert 제목
                    text: '저장에 실패했습니다. 다시 시도해주세요.',  // Alert 내용
                });
            }
        }).catch(error => {
            console.error('Error:', error);
        });
    }, 'image/png');
}

/* ------------------------------------- */
/*
 * 전달사항 textArea 글자수 제한
 *
 * */
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
        Swal.fire({
            icon: 'warning',                         // Alert 타입
            title: '경고',         // Alert 제목
            text: '글자수는 100자까지 입력 가능합니다.',  // Alert 내용
        });
    }
});

/* ------------------------------------- */

/*
 * 프리셋 저장하기
 *
 * */
function submitPresetForm() {
    const form = document.querySelector('#presetForm');
    const formData = new FormData(form);
    const selectedOption = formData.get('option');
    const presetName = formData.get('presetName');

    // 라디오 버튼이 선택되지 않았거나 presetName 이 입력되지 않은 경우 경고 메시지 표시
    if (!selectedOption) {
        Swal.fire({
            icon: 'warning',
            title: '✔︎',
            text: '프리셋 조건을 선택해주세요',
        });
        return; // submitPresetForm 종료
    }

    if (!presetName) {
        Swal.fire({
            icon: 'warning',
            title: '✔︎',
            text: '프리셋 이름을 입력해주세요',
        });
        return;
    }

    // SweetAlert 대화 상자를 통해 확인 버튼을 누르면 함수 실행
    Swal.fire({
        title: '프리셋을 저장하시겠습니까?',
        text: "저장 시 기존의 프리셋은 사라집니다. 프리셋을 저장하시려면 확인을 누르세요.",
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '확인',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
            const canvas = document.querySelector('#soccer-board');
            canvas.toBlob(function (blob) {
                // 유니크한 파일 이름 생성 (예: "canvas_1653079812345.png")
                const uniqueFileName = 'preset_' + new Date().getTime() + '.png';
                formData.append('canvasImage', blob, uniqueFileName);

                fetch('/club/createMatchBoard', {
                    method: 'POST',
                    body: formData
                }).then(response => {
                    if (response.ok) {
                        Swal.fire(
                            '프리셋 저장 완료',
                            '저장완료',
                            'success'
                        );
                    } else {
                        Swal.fire(
                            '프리셋 저장 실패',
                            '저장실패',
                            'error'
                        );
                    }
                }).catch(error => {
                    console.error('Error:', error);
                    Swal.fire(
                        '오류 발생',
                        '프리셋 저장 중 오류가 발생했습니다.',
                        'error'
                    );
                });
            }, 'image/png');
        }
    });
}


/* ------------------------------------- */
/*
 * 프리셋 불러오기
 *
 * */
// document.querySelector('#loadButton').addEventListener('click', function () {
//     const selectedOption = document.querySelector('input[name="option"]:checked');
//     if (!selectedOption) {
//         alert("라디오 버튼을 선택해주세요.");
//         return;
//     }
//
//     const type = selectedOption.value;
//
//     fetch(`/club/loadCanvas?type=${type}`, {
//         method: 'GET'
//     })
//         .then(response => response.json())
//         .then(data => {
//             if (data.error) {
//                 alert(data.error);
//                 return;
//             }
//             const img = new Image();
//             img.onload = function() {
//                 const canvas = document.querySelector('#soccer-board');
//                 const ctx = canvas.getContext('2d');
//                 ctx.clearRect(0, 0, canvas.width, canvas.height);
//                 ctx.drawImage(img, 0, 0);
//             };
//             img.src = `/upload/preset/${data.filePath}`;  // 이미지 파일 경로 설정
//             document.querySelector('#presetName').value = data.presetName;
//         })
//         .catch(error => console.error('Error:', error));
// });

/* ------------------------------------- */
/*
 *  프리셋 수정 테스트
 *
 * */
// document.querySelector('#loadButton').addEventListener('click', function () {
//     const selectedOption = document.querySelector('input[name="option"]:checked');
//     if (!selectedOption) {
//         alert("라디오 버튼을 선택해주세요.");
//         return;
//     }
//
//     const type = selectedOption.value;
//
//     fetch(`/club/loadCanvas?type=${type}`, {
//         method: 'GET'
//     })
//         .then(response => response.json())
//         .then(data => {
//             if (data.error) {
//                 alert(data.error);
//                 return;
//             }
//             const img = new Image();
//             img.onload = function () {
//                 const canvas = document.querySelector('#soccer-board');
//                 const ctx = canvas.getContext('2d');
//                 ctx.clearRect(0, 0, canvas.width, canvas.height); // 캔버스 초기화
//                 ctx.drawImage(img, 0, 0); // 이미지 그리기
//
//                 document.querySelector('#presetName').value = data.presetName;
//             };
//             img.src = `/upload/preset/${data.filePath}`;  // 이미지 파일 경로 설정
//         })
//         .catch(error => console.error('Error:', error));
// });

/* ------------------------------------- */
/**
 * sweet alert 적용하기 전의 submitPreset
 */
// function submitPresetForm() {
//     const form = document.querySelector('#presetForm');
//     const formData = new FormData(form);
//     const selectedOption = formData.get('option');
//     const presetName = formData.get('presetName');
//
//     // 라디오 버튼이 선택되지 않았거나 presetName 이 입력되지 않은 경우 경고 메시지 표시
//     if (!selectedOption) {
//         alert('옵션을 선택해주세요.');
//         return; // submitPresetForm 종료
//     }
//
//     if (!presetName) {
//         alert('프리셋 이름을 입력해주세요.');
//         return;
//     }
//
//     const canvas = document.querySelector('#soccer-board');
//     canvas.toBlob(function (blob) {
//         // 유니크한 파일 이름 생성 (예: "canvas_1653079812345.png")
//         const uniqueFileName = 'preset_' + new Date().getTime() + '.png';
//         formData.append('canvasImage', blob, uniqueFileName);
//
//         fetch('/club/createMatchBoard', {
//             method: 'POST',
//             body: formData
//         }).then(response => {
//             if (response.ok) {
//                 alert('프리셋 저장 완료');
//             } else {
//                 alert('프리셋 저장 실패');
//             }
//         }).catch(error => {
//             console.error('Error:', error);
//         });
//     }, 'image/png');
// }