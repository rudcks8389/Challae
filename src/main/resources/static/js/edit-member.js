/**
 * 토글에서 회원 정보 수정 창 처리
 */
document.addEventListener('DOMContentLoaded', function() {
    const editButton = document.getElementById('editButton');
    const editForm = document.getElementById('editForm');

    editButton.addEventListener('click', function() {
        if (editForm.style.display === 'none' || editForm.style.display === '') {
            editForm.style.display = 'block';
        } else {
            editForm.style.display = 'none';
        }
    });
});

/**
 * 토글에서 회원 정보 수정하기
 */
function updateMemberInfo() {
    const form = document.getElementById('editForm');
    const newEmail = form.querySelector('#newEmail').value;
    const password = form.querySelector('#password').value;
    const repasswd = form.querySelector('#repasswd').value;

    // 비밀번호와 비밀번호 재입력이 일치하는지 확인
    if (password !== repasswd) {
        document.getElementById('updateMessage').innerText = '비밀번호와 재입력이 일치하지 않습니다.';
        form.reset();
        return;
    }

    const formData = new FormData(form);

    fetch('/member/update', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (response.ok) {
                document.getElementById('updateMessage').innerText = '회원 정보가 성공적으로 수정되었습니다.';
                form.reset();
            } else {
                document.getElementById('updateMessage').innerText = '회원 정보 수정에 실패했습니다.';
            }
            return response.text();
        })
}