const date = new Date();
const  clubNum  = "101";

const renderMonth = async () => {

    const nowYear = date.getFullYear();
    const nowMonth = date.getMonth() + 1;
    // const nowDay = date.getDay(); 날짜는 현재 사용 x
    console.log(nowYear);
    console.log(nowMonth);

    document.querySelector(".cal-year-month").textContent = `${nowYear}년 ${nowMonth}월`;

    // const scheduleKey = `${nowYear}-${String(nowMonth).padStart(2, '0')}`;


    // 경기 일정 렌더링
    let schedules = [];
    try{
        // fetch 요청을 사용하여 서버로부터 데이터 가져오기
        const response = await fetch(`/api/schedules/${clubNum}`);
        if(response.ok){
            schedules = await response.json(); // 서버로부터 받은 JSON 데이터를 파싱
        } else {
            // 서버로부터 정상적인 응답을 받지 못했을 경우
            console.error("서버로부터 데이터를 가져오는 데 실패했습니다.");
            console.error(`http 상태코드 : ${response.status}`);
        }
    } catch (error) {
        // 요청 과정에서 에러 발생
        console.error("데이터를 가져오는 과정에서 에러가 발생했습니다:", error);
    }

    // 현재 선택한 달에 해당하는 경기 일정만 필터링
    const scheduleKey = `${nowYear}-${String(nowMonth).padStart(2, '0')}`;
    const monthSchedules = schedules.filter(schedule => {
        const scheduleDate = new Date(schedule.matchDate);
        return scheduleDate.getFullYear() === nowYear && (scheduleDate.getMonth() + 1) === nowMonth;
    });



    const schedulesContainer = document.querySelector(".team-match-schedule-detail");
    schedulesContainer.innerHTML = ''; // 이전 일정 삭제


    monthSchedules.forEach(schedule => {
        const matchInfo = document.createElement("div");
        matchInfo.classList.add("match-Info")

        const ulInfo = document.createElement("ul");
        matchInfo.appendChild(ulInfo)

        // 경기일자에서 일(day) 부분만 추출하여 표시
        const dateParts = schedule.matchDate.split('-'); // "2024-05-24" -> ["2024", "05", "24"]
        const dayOnly = dateParts[2]; // 일 부분만 추출
        const dateLi = document.createElement("li");
        dateLi.textContent = "< "+dayOnly+"일 >"; // 수정된 부분: 전체 날짜 대신 일(day) 부분만 렌더링
        dateLi.classList.add("date-li"); //클래스 지정함
        ulInfo.appendChild(dateLi);

        //필드 이름
        const fieldLi = document.createElement("li");
        fieldLi.textContent = schedule.fieldName;
        fieldLi.classList.add("field-li");
        ulInfo.appendChild(fieldLi);

        //경기시간
        const timeLi = document.createElement("li");
        timeLi.textContent = schedule.matchTime;
        timeLi.classList.add("time-li");
        ulInfo.appendChild(timeLi);

        const infoLi = document.createElement("li");
        infoLi.classList.add("info-li");
        ulInfo.appendChild(infoLi);

        const goInfoA = document.createElement("a");
        goInfoA.textContent = "상세정보";
        goInfoA.href=`matchView?matchNum=${schedule.matchNum}`;
        infoLi.appendChild(goInfoA);


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
   date.setMonth(new Date().getMonth());
    renderMonth();
};

renderMonth();






