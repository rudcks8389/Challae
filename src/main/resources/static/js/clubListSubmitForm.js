function submitForm() {
    let searchType = document.getElementById("search_select").value;
    let searchValue = document.getElementById("search_input").value;

    // 현재 URL 가져오기
    let currentUrl = window.location.href;
    // 쿼리 문자열 추가
    let queryString = "";

    if (searchType && searchValue) {
        queryString = "?searchType=" + encodeURIComponent(searchType) + "&searchValue=" + encodeURIComponent(searchValue);
    } else if (searchValue) {
        queryString = "?searchValue=" + encodeURIComponent(searchValue);
    }

    // 변경된 URL 생성
    let newUrl = currentUrl.split('?')[0] + queryString;

    // 새로고침
    window.location.href = newUrl;

    return false;  // 폼의 기본 동작을 막기 위해 false 반환
}
