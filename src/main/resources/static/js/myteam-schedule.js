
const date = new Date();

const renderMonth = () => {
  
  const nowYear = date.getFullYear();
  const nowMonth = date.getMonth() + 1;
  const nowDay = date.getDay();
  console.log(nowYear);
    console.log(nowMonth);

  document.querySelector(".cal-year-month").textContent = `${nowYear}년 ${nowMonth}월`;



}
renderMonth();

const prevMonth = () => {
    date.setMonth(date.getMonth()- 1);
    renderMonth();
};

const nextMonth = () =>{
    date.setMonth(date.getMonth() + 1);
    renderMonth();
};

const goToday = () => {
    const oriMonth = date.setMonth(new Date().getMonth());
    renderMonth();
};


  // // 이전달로 이동
  // $('.go-prev').on('click', function() {
  //     prevMonth = new Date(nowYear, nowMonth - 1);
  // });
  //
  // // 다음달로 이동
  // $('.go-next').on('click', function() {
  //     thisMonth = new Date(nowYear, nowMonth + 1, 1);
  //     renderCalender(thisMonth);
  // });






