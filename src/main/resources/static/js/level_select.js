document.querySelector('.level_select').addEventListener('change', function() {
  let selectedOption = this.value;
  document.querySelector('.level_input').value = selectedOption;
});
