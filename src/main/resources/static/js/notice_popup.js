function modal(id) {
    let zIndex = 9999;
    let modal = document.getElementById(id);

    // 모달 div 뒤에 희끄무레한 레이어
    let bg = document.createElement('div');
    bg.style.cssText = `
    position: fixed;
    z-index: ${zIndex};
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.4);
  `;
    document.body.append(bg);

    // 닫기 버튼 처리, 시꺼먼 레이어와 모달 div 지우기
    modal.querySelector('.modal_close_btn').addEventListener('click', function() {
        bg.remove();
        modal.style.display = 'none';
    });

    modal.style.cssText = `
    position: fixed;
    display: block;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
    z-index: ${zIndex + 1};
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  `;
}

// 각 공지사항 클릭 이벤트 추가
document.querySelectorAll('.notice_item').forEach(item => {
    item.addEventListener('click', function() {
        let noticeId = this.id.split('_')[1];
        modal(`notice_modal_${noticeId}`);
    });
});
