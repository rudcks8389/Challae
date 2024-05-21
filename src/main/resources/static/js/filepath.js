document.getElementById('file').addEventListener('change', function() {
  let fileName = this.files[0].name;
  document.querySelector('.upload_name').value = fileName;
});