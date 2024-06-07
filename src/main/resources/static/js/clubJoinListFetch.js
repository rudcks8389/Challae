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
}