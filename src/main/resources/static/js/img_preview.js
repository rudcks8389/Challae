document.getElementById('file').addEventListener('change', function(event) {
    const file = event.target.files[0];
    const reader = new FileReader();

    if (file) {
        reader.onload = function(e) {
            const img = document.getElementById('preview');
            img.src = e.target.result;
            img.style.display = 'block'; // 이미지를 보이도록 설정

            const uploadName = document.querySelector('.upload_name');
            uploadName.value = file.name; // 파일 이름을 input에 표시
        };

        reader.readAsDataURL(file);
    }
});

// '파일찾기' 라벨을 클릭 시 기본 동작 막기
document.querySelector('label[for="file"]').addEventListener('click', function(event) {
    event.preventDefault();

    // 파일 입력을 클릭하여 파일 선택 창이 나타나도록 함
    document.getElementById('file').click();
});
