// passwordUpdate.js
$(document).ready(function() {
    $('#updatePasswordButton').click(function() {
        var email = $('#newEmail').val();
        var passwd = $('#password').val();
        var repasswd = $('#repasswd').val();

        if (passwd !== repasswd) {
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }

        $.ajax({
            url: '/member/update',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                email: email,
                passwd: passwd
            }),
            success: function(response) {
                alert('회원 정보가 성공적으로 변경되었습니다.');
                // 필요시 추가 작업
            },
            error: function(xhr, status, error) {
                alert('회원 정보 변경에 실패했습니다.');
            }
        });
    });
});
