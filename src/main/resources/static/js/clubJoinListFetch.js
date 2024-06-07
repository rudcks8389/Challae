fetch('/club/joinList', {
    method: 'GET',
})
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); // Parse JSON response
    })
    .then(data => {
        // 함수를 호출하여 모달 내용을 업데이트
        updateModalContent(data);
    })
    .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
    });

function updateModalContent(data) {
    // 모달 내용을 업데이트하는 코드

    // 받아온 데이터를 이용하여 테이블을 생성하고 모달 내부에 추가하는 방법을 구현
}