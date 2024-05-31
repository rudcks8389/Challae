var today = new Date();
function buildCalendar(){
    var row = null
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

    row = calendarTable.insertRow();

    for(i = 1; i <= lastDate.getDate(); i++){
        cell = row.insertCell();
        cnt += 1;

        cell.setAttribute('id', i);
        cell.innerHTML = i;
        cell.align = "center";

        cell.onclick = function(){
            clickedYear = today.getFullYear();
            clickedMonth = ( 1 + today.getMonth() );
            clickedDate = this.getAttribute('id');

            clickedDate = clickedDate >= 10 ? clickedDate : '0' + clickedDate;
            clickedMonth = clickedMonth >= 10 ? clickedMonth : '0' + clickedMonth;
            clickedYMD = clickedYear + "-" + clickedMonth + "-" + clickedDate;

            opener.document.getElementById("date").value = clickedYMD;
            self.close();
        }

        if (cnt % 7 == 1) {
            cell.innerHTML = "<font color=#F79DC2>" + i + "</font>";
        }

        if (cnt % 7 == 0){
            cell.innerHTML = "<font color=skyblue>" + i + "</font>";
            row = calendar.insertRow();
        }
    }

    if(cnt % 7 != 0){
        for(i = 0; i < 7 - (cnt % 7); i++){
            cell = row.insertCell();
        }
    }
}

function prevCalendar(){
    today = new Date(today.getFullYear(), today.getMonth()-1, today.getDate());
    buildCalendar();
}

function nextCalendar(){
    today = new Date(today.getFullYear(), today.getMonth()+1, today.getDate());
    buildCalendar();
}