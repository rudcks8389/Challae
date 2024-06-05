/**
 * 화면에 모달창 띄우는 처리
 */
function modal(id) {
    let zIndex = 9999;
    let modal = document.getElementById(id);
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

    /**
     * 닫기 버튼 처리, 모달 지우기
     */
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

/**
 * 각 공지사항 클릭 이벤트 추가하기
 */
document.querySelectorAll('.notice_item').forEach(item => {
    item.addEventListener('click', function() {
        let noticeId = this.id.split('_')[1];
        modal(`notice_modal_${noticeId}`);
    });
});
