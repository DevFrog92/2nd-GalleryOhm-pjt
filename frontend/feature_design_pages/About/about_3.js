const divs = document.querySelectorAll('.menu div');
const lis = document.querySelectorAll('.about_con li');

divs.forEach(target => {
  target.addEventListener('click',function(e){
    checkDivIndex(divs,e.target.dataset.value);
    checkLiIndex(lis,e.target.dataset.value);
  })
})


const checkDivIndex = (divs,index) =>{
  for(let item of divs ){
    if(item.classList.contains('active')){
        item.classList.remove('active');
        divs[index-1].classList.add('active');
    }
  }
}

const checkLiIndex = (lis,index) =>{
  for(let item of lis ){
    if(item.classList.contains('active')){
        item.classList.remove('active');
        lis[index-1].classList.add('active');
    }
  }
}