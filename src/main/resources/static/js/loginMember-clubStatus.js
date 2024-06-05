/**
 * loginMember 의 클럽 상태에 따른 sweetalert2 창 구현
 * @param event
 */
function fetchClubStatus(event) {
    console.log('패치 url 호출함');
    /** 페이지 이동 막는 부분**/
    event.preventDefault();

    /** 백엔드 포인트 **/
    fetch('/club/status')
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => {

                    if (response.status === 403) {
                        throw new Error("클럽 상태: 승인되지 않음");
                    } else if (response.status === 400) {
                        throw new Error("클럽에 가입되어 있지 않음");
                    } else if (response.status === 401) {
                        throw new Error(text);
                    } else {
                        throw new Error('Network response was not ok');
                    }
                });
            }
            return response.text();
        })
        .then(data => {
            if (data === "클럽 상태: 승인") {
                window.location.href = '/club/myteam';

            } else if (data === "클럽 상태: 승인되지 않음") {
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
                /** 그외 **/
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