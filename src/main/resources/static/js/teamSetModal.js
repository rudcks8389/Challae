const modal = document.querySelector('.team-set-background');
const modalOpenBtn = document.querySelector('.team-set-btn');
const modalCloseBtn = document.querySelector('.team-set-close-btn');

modalOpenBtn.addEventListener('click',() =>{
    modal.classList.remove('hidden');
});

modalCloseBtn.addEventListener('click',() =>{
    modal.classList.add('hidden');
});