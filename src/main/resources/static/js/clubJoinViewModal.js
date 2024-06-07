// 모달 열기
let modal = document.getElementById("joinRequestModal");
let btn = document.getElementById("myBtn");
btn.onclick = function () {
    modal.style.display = "block";
    fetchJoinRequests();
}

// 닫기 버튼으로 모달 닫기
let span = document.getElementsByClassName("close")[0];
span.onclick = function () {
    modal.style.display = "none";
}

// 모달 바깥 영역 클릭 시 모달 닫기
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function fetchJoinRequests() {
    const clubNum = document.querySelector("#clubNum").innerText;
    fetch(`/club/joinList?clubNum=${clubNum}`, {
        method: 'GET',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse JSON response
        })
        .then(data => {
            updateModalContent(data);
            let table = document.getElementById("joinRequestsTable");

            let tr = ``;
            data.forEach(request => {
                if (request.joinStatus === '대기') {
                    tr += `<tr>`;
                    tr += `<td><img src="/upload/profile/${request.joinMemberPhoto}" style="width: 100px; height: 100px;"></td>`;
                    tr += `<td>${request.joinMemberName}</td>`;
                    tr += `<td>${request.joinMemberPhone}</td>`;
                    tr += `<td>${request.joinMemberEmail}</td>`;
                    tr += `<td>${request.joinMemberLevel}</td>`;
                    tr += `<td>${request.joinMemberInfo}</td>`;
                    tr += `<td>${request.joinStatus}</td>`;
                    tr += `<td><button class="approve-btn" data-join-num="${request.joinNum}">승인</button></td>`;
                    tr += `<td><button class="refuse-btn" data-join-num="${request.joinNum}">거절</button></td>`;
                    tr += `</tr>`;
                }
            });

            table.innerHTML = tr;

            // 승인 및 거절 버튼에 이벤트 리스너 추가
            document.querySelectorAll('.approve-btn').forEach(button => {
                button.addEventListener('click', () => handleJoinRequest(button.dataset.joinNum, 'approve'));
            });

            document.querySelectorAll('.refuse-btn').forEach(button => {
                button.addEventListener('click', () => handleJoinRequest(button.dataset.joinNum, 'refuse'));
            });
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}

function handleJoinRequest(joinNum, action) {
    let url = '';
    if (action === 'approve') {
        url = `/club/joinApprove?joinNum=${joinNum}`;
    } else if (action === 'refuse') {
        url = `/club/joinRefuse?joinNum=${joinNum}`;
    }

    fetch(url, {
        method: 'POST',
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(message => {
            fetchJoinRequests(); // 목록 갱신
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}
