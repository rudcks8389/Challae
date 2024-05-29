const date = new Date();

// 임의의 경기 일정 데이터
// const schedules = {
//     '2024-05': [
//         {date: '2024-05-03', time: '17:00', fieldName: '하리구장', clubNum:'101'},
//         {date: '2024-05-15', time: '14:00', fieldName: '공릉구장', clubNum:'101'},
//         {date: '2024-05-27', time: '11:00', fieldName: '상봉구장', clubNum:'101'},
//     ],
//     '2024-06': [
//         {date: '2024-06-03', time: '17:00', fieldName: '하리구장', clubNum:'101'},
//         {date: '2024-06-15', time: '14:00', fieldName: '공릉구장', clubNum:'102'},
//         {date: '2024-06-27', time: '11:00', fieldName: '상봉구장', clubNum:'101'},
//     ],
//     '2024-07': [
//         {date: '2024-07-03', time: '17:00', fieldName: '하리구장', clubNum:'101'},
//         {date: '2024-07-15', time: '14:00', fieldName: '공릉구장', clubNum:'101'},
//         {date: '2024-07-27', time: '11:00', fieldName: '상봉구장', clubNum:'101'},
//     ],
//     '2023-05': [
//         {date: '2023-05-03', time: '17:00', fieldName: '하리구장', clubNum:'101'},
//         {date: '2023-05-15', time: '14:00', fieldName: '공릉구장', clubNum:'101'},
//         {date: '2023-05-27', time: '11:00', fieldName: '상봉구장', clubNum:'102'},
//     ],
//     '2025-05': [
//         {date: '2025-05-03', time: '17:00', fieldName: '하리구장', clubNum:'101'},
//         {date: '2025-05-15', time: '14:00', fieldName: '공릉구장', clubNum:'101'},
//         {date: '2025-05-27', time: '11:00', fieldName: '상봉구장', clubNum:'101'},
//     ],
//
//
//     // 여기에 더 많은 데이터 추가 가능
// };

const renderMonth = async () => {

    const nowYear = date.getFullYear();
    const nowMonth = date.getMonth() + 1;
    // const nowDay = date.getDay(); 날짜는 현재 사용 x
    console.log(nowYear);
    console.log(nowMonth);

    document.querySelector(".cal-year-month").textContent = `${nowYear}년 ${nowMonth}월`;

    // 경기 일정 렌더링
    const scheduleKey = `${nowYear}-${String(nowMonth).padStart(2, '0')}`;


    let monthSchedules = [];
    try{
        // fetch 요청을 사용하여 서버로부터 데이터 가져오기
        const response = await fetch(`/api/schedules/${scheduleKey}`);
        if(response.ok){
            monthSchedules = await response.json(); // 서버로부터 받은 JSON 데이터를 파싱
        } else {
            // 서버로부터 정상적인 응답을 받지 못했을 경우
            console.error("서버로부터 데이터를 가져오는 데 실패했습니다.");
        }
    } catch (error) {
        // 요청 과정에서 에러 발생
        console.error("데이터를 가져오는 과정에서 에러가 발생했습니다:", error);
    }




    const schedulesContainer = document.querySelector(".team-match-schedule-detail");
    schedulesContainer.innerHTML = ''; // 이전 일정 삭제


    monthSchedules.forEach(schedule => {
        const matchInfo = document.createElement("div");
        matchInfo.classList.add("match-Info")

        const ulInfo = document.createElement("ul");
        matchInfo.appendChild(ulInfo)

        // 경기일자

        const dateLi = document.createElement("li");
        dateLi.textContent = schedule.date;
        dateLi.classList.add("date-li"); //클래스 지정함
        ulInfo.appendChild(dateLi);

        //필드 이름
        const fieldLi = document.createElement("li");
        fieldLi.textContent = schedule.fieldName;
        fieldLi.classList.add("field-li");
        ulInfo.appendChild(fieldLi);

        //경기시간
        const timeLi = document.createElement("li");
        timeLi.textContent = schedule.time;
        timeLi.classList.add("time-li");
        ulInfo.appendChild(timeLi);


        schedulesContainer.appendChild(matchInfo);

    });

}

const prevMonth = () => {
    date.setMonth(date.getMonth() - 1);
    renderMonth();
};

const nextMonth = () => {
    date.setMonth(date.getMonth() + 1);
    renderMonth();
};

const goTodayMonth = () => {
    const oriMonth = date.setMonth(new Date().getMonth());
    renderMonth();
};

renderMonth();






