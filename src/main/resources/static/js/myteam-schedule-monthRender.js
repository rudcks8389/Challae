/**
 * myteam 페이지에 사용될 월간 일정표 (클라이언트 사이드 랜더링)
 */

const date = new Date();
const  clubNum  = "101";

const renderMonth = async () => {

    const nowYear = date.getFullYear();
    const nowMonth = date.getMonth() + 1;

    document.querySelector(".cal-year-month").textContent = `${nowYear}년 ${nowMonth}월`;



    /** 경기 일정 렌더링**/
    let schedules = [];
    try{
        /** fetch 요청을 사용하여 서버로부터 데이터 가져오기**/
        const response = await fetch(`/api/schedules/${clubNum}`);
        if(response.ok){
            schedules = await response.json(); /**서버로부터 받은 JSON 데이터를 파싱**/

        } else {
            console.error("서버로부터 데이터를 가져오는 데 실패했습니다.");
            console.error(`http 상태코드 : ${response.status}`);
        }
    } catch (error) {
        /** 에러 발생 시 **/
        console.error("데이터를 가져오는 과정에서 에러가 발생했습니다:", error);
    }

    /** 현재 선택한 달에 해당하는 경기 일정만 필터링**/
    const scheduleKey = `${nowYear}-${String(nowMonth).padStart(2, '0')}`;
    const monthSchedules = schedules.filter(schedule => {
        const scheduleDate = new Date(schedule.matchDate);
        return scheduleDate.getFullYear() === nowYear && (scheduleDate.getMonth() + 1) === nowMonth;
    });



    const schedulesContainer = document.querySelector(".team-match-schedule-detail");
    schedulesContainer.innerHTML = ''; /** 이전 일정 초기화 **/


    monthSchedules.forEach(schedule => {
        const matchInfo = document.createElement("div");
        matchInfo.classList.add("match-Info")

        const ulInfo = document.createElement("ul");
        matchInfo.appendChild(ulInfo)

        /** 경기일자에서 일자부분만 추출 **/
        const dateParts = schedule.matchDate.split('-');
        const dayOnly = dateParts[2];
        const dateLi = document.createElement("li");
        dateLi.textContent = dayOnly+" 일";
        dateLi.classList.add("date-li");
        ulInfo.appendChild(dateLi);

        //필드 이름
        const fieldLi = document.createElement("li");
        const fieldName = schedule.fieldName;
        fieldLi.textContent = "''"+fieldName+"''"
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
/** 이전 달 **/
const prevMonth = () => {
    date.setMonth(date.getMonth() - 1);
    renderMonth();
};

/** 다음 달 **/
const nextMonth = () => {
    date.setMonth(date.getMonth() + 1);
    renderMonth();
};

/** 당일로 가는 기능 **/
const goTodayMonth = () => {
   date.setMonth(new Date().getMonth());
    renderMonth();
};

renderMonth();






