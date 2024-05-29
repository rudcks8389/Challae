const httpRequest = function (url) {
    return fetch(url).then(response => response.json());
};

/** 이벤트타겟에 이벤트 핸들러 연결(등록) */
const eventRegister = function () {
    document.registerForm.addEventListener("submit", handleSubmitButton);
    document.querySelector("#id").addEventListener("input", handleChangeInput);

    document.querySelectorAll("form input").forEach(function (input) {
        input.addEventListener("click", handleClickInput);
    });
};

// 아이디 중복 체크 이벤트 처리
const handleChangeInput = async function (event) {
    let inputId = event.target.value;
    const url = `/member/idcheck/${inputId}`;
    let resultMessage = null;

    if (inputId.length >= 6 && inputId.length <= 10) {
        const object = await httpRequest(url);
        if (object.result) {
            resultMessage = `<span style="color: blue">${object.message}</span>`;
        } else {
            resultMessage = `<span style="color: red">${object.message}</span>`;
        }
    } else {
        resultMessage = `<span style="color: red">아이디는 6자 이상 10자 이하여야 합니다.</span>`;
    }
    showIdResult(resultMessage);
};

// 아이디 입력 관련 메시지 출력
const showIdResult = function (message) {
    const resultView = document.querySelector("#checkedId");
    if (resultView) {
        resultView.innerHTML = message;
    }
};

// 회원 가입 이벤트 처리
const handleSubmitButton = function (event) {
    event.preventDefault();

    const form = event.target;
    const nameInput = form.querySelector("[name='name']");
    const idInput = form.querySelector("[name='id']");
    const passwdInput = form.querySelector("[name='passwd']");
    const rePasswdInput = form.querySelector("[name='rePasswd']");
    const emailInput = form.querySelector("[name='email']");

    if (Validator.isEmpty(nameInput.value)) {
        showErrorMessage(nameInput, "이름을 입력하여 주세요.");
        return;
    }
    if (Validator.isEmpty(idInput.value)) {
        showErrorMessage(idInput, "아이디를 입력하여 주세요.");
        return;
    }
    if (Validator.isEmpty(emailInput.value)) {
        showErrorMessage(emailInput, "이메일을 입력하여 주세요.");
        return;
    }
    if (Validator.isEmpty(passwdInput.value)) {
        showErrorMessage(passwdInput, "비밀번호를 입력하여 주세요.");
        return;
    }
    if (passwdInput.value !== rePasswdInput.value) {
        showErrorMessage(rePasswdInput, "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return;
    }

    form.submit();
};

// 검증 오류 메시지 출력
const showErrorMessage = function (input, message) {
    const errorElement = document.createElement('p');
    errorElement.style.color = 'red';
    errorElement.innerText = message;
    input.insertAdjacentElement('afterend', errorElement);
    input.focus();
};

// 아이디 필드 클릭 이벤트 처리
const handleClickInput = function (event) {
    const nextElement = event.target.nextElementSibling;
    if (nextElement && nextElement.tagName === 'P') {
        nextElement.remove();
    }
};

/** entry point */
function main() {
    eventRegister();
}
main();
