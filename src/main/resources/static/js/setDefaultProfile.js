function setDefaultImage(event) {
    const fileInput = document.getElementById('file');
    const defaultImageInput = document.getElementById('defaultImage');

    if (!fileInput.value) {
        // 사용자 이미지가 선택되지 않았을 경우 기본 이미지 URL을 프로필 이미지 필드에 설정
        const formData = new FormData(event.target);
        formData.append('profileImage', defaultImageInput.value);
    }
}