let weatherIcon = {
    '01': 'fas fa-sun',
    '02': 'fas fa-cloud-sun',
    '03': 'fas fa-cloud',
    '04': 'fas fa-cloud-meatball',
    '09': 'fas fa-cloud-sun-rain',
    '10': 'fas fa-cloud-showers-heavy',
    '11': 'fas fa-poo-storm',
    '13': 'far fa-snowflake',
    '50': 'fas fa-smog'
  };
  
  
  // 날씨 api - 서울
  let apiURI = "http://api.openweathermap.org/data/2.5/weather?q=" + 'seoul' + "&appid=" + "668b90bcd34b242250627b3085b7a977";
  $.ajax({
    url: apiURI,
    dataType: "json",
    type: "GET",
    async: "false",
    success: function (resp) {
  
      let $Icon = (resp.weather[0].icon).substr(0, 2);
      let $weather_description = resp.weather[0].main;
      let $Temp = Math.floor(resp.main.temp - 273.15) + '°';
      let $humidity = '습도&nbsp;&nbsp;&nbsp;&nbsp;' + resp.main.humidity + ' %';
      let $wind = '바람&nbsp;&nbsp;&nbsp;&nbsp;' + resp.wind.speed + ' m/s';
      let $city = '서울';
      let $cloud = '구름&nbsp;&nbsp;&nbsp;&nbsp;' + resp.clouds.all + "%";
      let $temp_min = '최저 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(resp.main.temp_min - 273.15) + '°';
      let $temp_max = '최고 온도&nbsp;&nbsp;&nbsp;&nbsp;' + Math.floor(resp.main.temp_max - 273.15) + '°';
      // let today = new Date();
  
  
  
      $('.weather_icon').append('<i class="' + weatherIcon[$Icon] + ' fa-5x" style="height : 150px; width : 150px;"></i>');
      $('.weather_description').prepend($weather_description);
      $('.current_temp').prepend($Temp);
      $('.humidity').prepend($humidity);
      $('.wind').prepend($wind);
      $('.city').append($city);
      $('.cloud').append($cloud);
      $('.temp_min').append($temp_min);
      $('.temp_max').append($temp_max);
      // $('.today').append(today);
  
    }
  })
  