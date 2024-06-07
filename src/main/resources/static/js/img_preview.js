document.getElementById('fileInput').addEventListener('change', function(event) {
    const file = event.target.files[0];
    if (file && file.type.startsWith('image/')) {
        const reader = new FileReader();
        reader.onload = function(e) {
            const img = document.createElement('img');
            img.src = e.target.result;
            const uploadBtn = document.getElementById('uploadBtn');
            // Remove any existing images inside the button
            const existingImg = uploadBtn.querySelector('img');
            if (existingImg) {
                uploadBtn.removeChild(existingImg);
            }
            // Hide the text inside the button
            const spanText = uploadBtn.querySelector('span');
            if (spanText) {
                spanText.style.display = 'none';
            }
            uploadBtn.appendChild(img);
        };
        reader.readAsDataURL(file);
    }
});