function fetchClubStatus(event) {
    console.log('패치 url 호출함');
    event.preventDefault(); // 기본 동작(페이지 이동) 막기

    fetch('/club/status') // 백엔드 엔드포인트 URL로 수정
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => {
                    console.log("Error Response Data:", text); // 오류 응답 데이터 로그
                    if (response.status === 403) {
                        // 403 상태 코드일 때 "클럽 상태: 승인되지 않음" 메시지 반환
                        throw new Error("클럽 상태: 승인되지 않음");
                    } else if (response.status === 400) {
                        throw new Error("클럽에 가입되어 있지 않음");
                    } else if (response.status === 401) {
                        throw new Error(text); // 세션 만료 또는 로그인 필요 메시지
                    } else {
                        throw new Error('Network response was not ok');
                    }
                });
            }
            return response.text();
        })
        .then(data => {
            console.log("Received data:", data); // 받은 데이터를 콘솔에 출력
            if (data === "클럽 상태: 승인") {
                // 승인된 경우 내 팀 보기 페이지로 이동
                window.location.href = '/club/myteam';
            } else if (data === "클럽 상태: 승인되지 않음") {
                // 클럽 상태가 승인되지 않은 경우
                Swal.fire({
                    title: '접근 권한 없음',
                    text: '해당 페이지에 접근하려면 승인된 상태여야 합니다.',
                    icon: 'warning',
                    confirmButtonText: '확인'
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = '/';
                    }
                });
            } else if (data === "클럽에 가입되지 않음") {
                // 클럽에 가입되어 있지 않은 경우
                Swal.fire({
                    title: '클럽 가입 필요',
                    text: '클럽에 가입되어 있지 않습니다. 클럽 리스트 페이지로 이동합니다.',
                    icon: 'info',
                    confirmButtonText: '확인'
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = '/club/list';
                    }
                });
            } else {
                // 그 외의 경우
                Swal.fire({
                    title: '알 수 없는 상태',
                    text: '알 수 없는 상태입니다: ' + data,
                    icon: 'error',
                    confirmButtonText: '확인'
                });
            }
        })
        .catch(error => {
            console.error('Error:', error);
            Swal.fire({
                title: '오류',
                text: error.message,
                icon: 'error',
                confirmButtonText: '확인'
            });
        });
}