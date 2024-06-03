/**
 * Datepicker 관련 JS
 *
 */
$(function () {
  /**
   * 달력 날짜 선택에 관한 옵션
   * @type {*|jQuery}
   */
  let dpDate = $('.datepicker').datepicker({
    autoclose: true,
    clearBtn: true,
    format: 'yyyy-mm-dd',
    startDate: new Date(),
  });

  dpDate.datepicker('setDate', new Date())

  let pickedDate = $('#reservationDate').val();
  $('#pickedDate').html(pickedDate);

  /**
   * 유저가 선택한 날짜를 화면에 보여주기
   */
  $('#reservationDate').on('change', function () {
    let pickedDate = $('#reservationDate').val();
    $('#pickedDate').html(pickedDate);
  });

});
