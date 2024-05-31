function confirmKick(form) {
    Swal.fire({
        title: '정말로 추방하시겠습니까?',
        text: "이 작업은 되돌릴 수 없습니다!",
        icon: 'warning',
        showCancelButton: true,
        background : 'black',
        color: 'white',
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '네, 추방합니다!',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
            form.submit(); // 폼 제출
            Swal.fire({
                title :'선수 추방이 완료되었습니다.',
                icon : 'success'
            });
        }
    });
}