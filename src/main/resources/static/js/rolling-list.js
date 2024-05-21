let roller = document.querySelector('section article .rolling-list');
roller.id = 'roller1'; // 아이디 부여

let clone = roller.cloneNode(true) 
// cloneNode : 노드 복제. 기본값은 false. 자식 노드까지 복제를 원하면 true 사용
clone.id = 'roller2';
document.querySelector('section .rolling-wrap').appendChild(clone); // wrap 하위 자식으로 부착

document.querySelector('#roller1').style.left = '0px';
document.querySelector('#roller2').style.left = document.querySelector('section article .rolling-list ul').offsetWidth + 'px';
// offsetWidth : 요소의 크기 확인( margin을 제외한 padding값, border값까지 계산한 값)

roller.classList.add('original');
clone.classList.add('clone');
