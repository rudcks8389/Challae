var fields = '[[${fields}]]';
var today = new Date();
var currentMonth = today.getMonth();  // 현재 월
var currentYear = today.getFullYear();

// fieldOpentime과 fieldClosetime을 Thymeleaf로 전달받아 JavaScript 변수에 할당
var fieldOpentime = '[[${fieldDetail2.fieldOpentime}]]'; // 초기값은 오전 9시, 실제 값으로 대체됩니다.
var fieldClosetime = '[[${fieldDetail2.fieldClosetime}]]'; // 초기값은 오후 6시, 실제 값으로 대체됩니다.
var fieldDayprice = '[[${fieldDetail2.fieldDayprice}]]';
var fieldEndprice = '[[${fieldDetail2.fieldEndprice}]]';

var selectedTimes = [];

function buildCalendar(){
    var row = null;
    var cnt = 0;
    var calendarTable = document.getElementById("calendar");
    var calendarTableTitle = document.getElementById("calendarTitle");
    calendarTableTitle.innerHTML = today.getFullYear()+"년"+(today.getMonth()+1)+"월";

    var firstDate = new Date(today.getFullYear(), today.getMonth(), 1);
    var lastDate = new Date(today.getFullYear(), today.getMonth()+1, 0);
    while(calendarTable.rows.length > 2){
        calendarTable.deleteRow(calendarTable.rows.length -1);
    }

    row = calendarTable.insertRow();

    for(i = 0; i < firstDate.getDay(); i++){
        cell = row.insertCell();
        cnt += 1;
    }

    for (i = 1; i <= lastDate.getDate(); i++) {
        if (cnt % 7 === 0) {
            row = calendarTable.insertRow();
        }

        cell = row.insertCell();
        cnt += 1;

        cell.setAttribute('id', i);
        cell.innerHTML = i;
        cell.style.textAlign = "center";

        cell.onclick = function(){
            var clickedYear = today.getFullYear();
            var clickedMonth = ( 1 + today.getMonth() );
            var clickedDate = this.getAttribute('id');

            clickedDate = clickedDate >= 10 ? clickedDate : '0' + clickedDate;
            clickedMonth = clickedMonth >= 10 ? clickedMonth : '0' + clickedMonth;
            var clickedYMD = clickedYear + "-" + clickedMonth + "-" + clickedDate;

            document.getElementById("selectedDate").innerText = clickedYMD;
            document.getElementById("selectedDate2").innerText = clickedYMD;

            var clickedDay = new Date(clickedYear, clickedMonth - 1, clickedDate).getDay();

            // 주말인 경우
            if (clickedDay == 0 || clickedDay == 6) {
                document.getElementById("priceBottom").innerText = fieldEndprice + "원"; // 주말 가격 표시
            } else {
                document.getElementById("priceBottom").innerText = fieldDayprice + "원"; // 주중 가격 표시
            }
        }
        if (cnt % 7 == 1) {
            cell.innerHTML = "<span style='color: red;'>" + i + "</span>";
        }
        if (cnt % 7 == 0){
            cell.innerHTML = "<span style='color: blue;'>" + i + "</span>";
            row = calendar.insertRow();
        }
    }
    if(cnt % 7 != 0){
        for(var i = 0; i < 7 - (cnt % 7); i++){
            var cell = row.insertCell();
        }
    }
}
function prevCalendar(){
    if(today.getFullYear() > currentYear || (today.getFullYear() === currentYear && today.getMonth() > currentMonth)){
        today = new Date(today.getFullYear(), today.getMonth()-1, today.getDate());
        buildCalendar();
        buildTimeTable();
    }
}
function nextCalendar(){
    if(today.getFullYear() < currentYear || (today.getFullYear() === currentYear && today.getMonth() < currentMonth + 1)){
        today = new Date(today.getFullYear(), today.getMonth()+1, today.getDate());
        buildCalendar();
        buildTimeTable();
    }
}
function buildTimeTable() {
    var timeTable = document.getElementById("timeTable")
    // 기존 테이블 내용 초기화
    while (timeTable.rows.length > 0) {
        timeTable.deleteRow(timeTable.rows.length - 1);
    }
    // 시간을 1시간 간격으로 추가
    var openHour = parseInt(fieldOpentime.split(':')[0]);
    var closeHour = parseInt(fieldClosetime.split(':')[0]);
    for (var hour = openHour; hour < closeHour; hour++) {
        var row = timeTable.insertRow();
        var cell = row.insertCell();
        var timeText = (hour < 10 ? "0" + hour : hour) + ":00-" + ((hour + 1 < 10 ? "0" + (hour + 1) : hour + 1) + ":00");
        cell.innerHTML = timeText;
        cell.style.textAlign = "center";
        cell.style.cursor = "pointer";
        // 클릭 이벤트 추가
        cell.onclick = function () {
            toggleTimeSelection(this);
        };
    }
}

function toggleTimeSelection(cell) {
    var timeText = cell.innerText;
    var index = selectedTimes.indexOf(timeText);
    if (index > -1) {
        // 이미 선택된 시간일 때 더블 클릭하면 선택 취소
        if (cell.classList.contains("selected")) {
            selectedTimes.splice(index, 1);
            cell.style.backgroundColor = ""; // 선택 해제 시 배경색 초기화
            cell.classList.remove("selected");
        } else {
            // 더블 클릭 시 선택 취소
            cancelTimeSelection(timeText);
        }
    } else {
        selectedTimes.push(timeText);
        selectedTimes.sort();
        if (selectedTimes.length > 1 && !isConsecutive(selectedTimes)) {
            Swal.fire({
                icon: 'warning',
                title: '연속된 시간을 선택해주세요.',
                text: '선택한 시간은 연속적이어야 합니다.',
                confirmButtonText: '확인'
            }).then(() => {
                selectedTimes.pop(); // 연속되지 않으면 마지막 선택을 취소
                updateSelectedTimes(); // 업데이트된 시간 다시 표시
            });
        } else {
            cell.style.backgroundColor = "lightblue"; // 선택 시 배경색 변경
            cell.classList.add("selected");
        }
    }
    updateSelectedTimes();
}

function updateSelectedTimes() {
    var timeBottom = document.getElementById("timeBottom");
    timeBottom.innerText = selectedTimes.join(", ");

    // 총 결제금액 업데이트
    var priceBottom = document.getElementById("priceBottom");
    var clickedDate = document.getElementById("selectedDate").innerText;
    var clickedDay = new Date(clickedDate).getDay();
    var pricePerHour = (clickedDay == 0 || clickedDay == 6) ? fieldEndprice : fieldDayprice;
    var totalPrice = pricePerHour * selectedTimes.length;
    priceBottom.innerText = totalPrice + "원";
}
// 시간 선택 취소
function cancelTimeSelection(timeText) {
    var timeTable = document.getElementById("timeTable");
    for (var i = 0; i < timeTable.rows.length; i++) {
        var cells = timeTable.rows[i].cells;
        for (var j = 0; j < cells.length; j++) {
            if (cells[j].innerText === timeText) {
                cells[j].style.backgroundColor = ""; // 선택한 시간의 배경색 초기화
                toggleTimeSelection(cells[j]); // 선택 취소
                return;
            }
        }
    }
}
function isConsecutive(times) {
    times.sort();
    for (var i = 1; i < times.length; i++) {
        var prevHour = parseInt(times[i - 1].split(":")[0]);
        var currHour = parseInt(times[i].split(":")[0]);
        if (currHour !== prevHour + 1) {
            return false;
        }
    }
    return true;
}

buildCalendar();
buildTimeTable();

function requestPay(resNum) {
    IMP.init('imp66680553'); //iamport 대신 자신의 "가맹점 식별코드"를 사용
    var selectedDate = document.getElementById("selectedDate").innerText;
    var totalPrice = document.getElementById("priceBottom").innerText;
    var buyerName = '사용자 이름'; // 사용자 이름
    var buyerTel = '010-1234-5678'; // 사용자 전화번호

    IMP.request_pay({
        pg: "html5_inicis",
        pay_method: "card",
        merchant_uid: 'merchant_' + new Date().getTime(),
        name: '대관 예약 및 결제', // 변경된 결제 품목 이름
        amount: parseInt(totalPrice), // 변경된 결제 총 금액
        buyer_name: buyerName,
        buyer_tel: buyerTel,
        custom_data: {
            selected_date: selectedDate // 여기서 selectedDate 전달
        }
    }, function (rsp) { // callback
        console.log(rsp);
        if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            alert(msg);
            // 서버에 예약 번호 전달
            location.href = `/field/payfinish/${resNum}`;
        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
            alert(msg);
        }
    });
    console.log("결제 API 호출");
}

function handleReservationAndPayment() {
    // 예약 정보를 GET 요청으로 서버에 전송
    var resResDate = document.getElementById('selectedDate2').innerText; // 예약 날짜
    var resPrice = parseInt(document.getElementById('priceBottom').innerText); // 예약 가격
    var resTime = document.getElementById('timeBottom').innerText; // 예약 시간
    var resMemo = document.querySelector('textarea[name="r_memo"]').value; // 메모 내용
    var memberNum = 1; // 멤버 번호

    var url = `/field/saveReservation?resResDate=${resResDate}&resPrice=${resPrice}&resTime=${resTime}&resMemo=${resMemo}`;

    // 예약 정보를 저장하는 GET 요청을 보내고 결제 API 호출
    fetch(url)
        .then(response => response.json())
        .then(reservation => {
            //console.log('서버로 부터 수신한 예약 정보 : ' + reservation);
            //console.dir(reservation);
            // 결제 API 호출
            requestPay(reservation.resNum);
        })
        .catch(error => {
            console.error('Error saving reservation:', error);
            // 화면에 예약 오류메시지 출력
            // 결제 API 호출 (실패해도 결제는 시도)
            //requestPay();
        });
}