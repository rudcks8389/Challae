$(function () {


  let dpDate = $('.datepicker').datepicker({
    autoclose: true,
    clearBtn: true,
    format: 'yyyy-mm-dd',
    startDate: new Date(),
  });

  dpDate.datepicker('setDate', new Date())

  $('#reservationDate').on('change', function () {
    let pickedDate = $('input').val();
    $('#pickedDate').html(pickedDate);
    console.log('선택한 날짜 : ' + pickedDate);
  });

});
