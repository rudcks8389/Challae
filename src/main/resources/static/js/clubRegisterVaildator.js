const httpRequest = function (url) {
    return fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        });
};
/** 이벤트 타겟에 이벤트 핸들러 연결(등록) */
const eventRegister = function () {
    document.clubRegisterForm.addEventListener("submit", handleSubmitButton);
    document.querySelector("[name='clubName']").addEventListener("input", handleChangeInput);

    document.querySelectorAll("form input").forEach(function (input) {
        input.addEventListener("click", handleClickInput);
    });
};

/** 아이디 중복체크 관련 처리 */
const handleChangeInput = async function (event) {
    let clubName = event.target.value;
    const url = `/club/idcheck/${clubName}`;
    let resultMessage = null;

    try {
        if (clubName.length >= 2 && clubName.length <= 20) {
            const object = await httpRequest(url);
            if (object.result) {
                resultMessage = `<p style="color: blue">${object.message}</p>`;
            } else {
                resultMessage = `<p style="color: red">${object.message}</p>`;
            }
        } else {
            resultMessage = `<p style="color: red">클럽명은 2자 이상 20자 이내 입니다.</p>`;
        }
    } catch (error) {
        resultMessage = `<p style="color: red">클럽명 중복 확인에 실패했습니다.</p>`;
    }

    showIdResult(resultMessage);
};

/** 아이디 입력 관련 메시지 */
const showIdResult = function (message) {
    const resultView = document.querySelector("#checkedClubName");
    console.log(resultView);
    if (resultView) {
        resultView.innerHTML = message;
    }
};

/** 회원가입 이벤트 처리 */
const handleSubmitButton = function (event) {
    event.preventDefault();

    // 기존 에러 메시지 제거
    document.querySelectorAll('form p.text-danger').forEach(p => p.remove());

    const form = event.target;
    const nameInput = form.querySelector("[name='clubName']");
    const phoneInput = form.querySelector("[name='clubPhone']");
    const infoInput = form.querySelector("[name='clubInfo']");

    if (Validator.isEmpty(nameInput.value)) {
        showErrorMessage(nameInput, "클럽명을 입력하여 주세요.");
        return;
    }
    if (Validator.isEmpty(phoneInput.value)) {
        showErrorMessage(phoneInput, "연락처를 입력하여 주세요.");
        return;
    }
    if (/[^0-9]/.test(phoneInput.value)) { // 숫자가 아닌 값이 포함된 경우
        showErrorMessage(phoneInput, "숫자만 입력해주세요.");
        return;
    }
    if (Validator.isEmpty(infoInput.value)) {
        showErrorMessage(infoInput, "소개를 입력하여 주세요.");
        return;
    }

    form.submit();
};

/** 검증 오류 메시지 출력 */
const showErrorMessage = function (input, message) {
    // 기존 에러 메시지 제거
    const existingError = input.nextElementSibling;
    if (existingError && existingError.tagName === 'P') {
        existingError.remove();
    }

    const errorElement = document.createElement('p');
    errorElement.style.color = 'red';
    errorElement.className = 'text-danger';
    errorElement.innerText = message;
    input.insertAdjacentElement('afterend', errorElement);
    input.focus();
};

/** 아이디 필드 클릭 이벤트 처리 */
const handleClickInput = function (event) {
    // 기존 에러 메시지 제거
    const nextElement = event.target.nextElementSibling;
    if (nextElement && nextElement.tagName === 'P') {
        nextElement.remove();
    }

    // 다른 필드를 클릭했을 때 checkedId 내용을 지우지 않도록 함
    if (event.target.name !== 'clubName') {
        return;
    }
};

/** entry point */
function main() {
    eventRegister();
}
main();