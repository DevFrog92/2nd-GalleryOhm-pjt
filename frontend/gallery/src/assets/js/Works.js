import router from '../../router'
const init = () => {
  
    console.log('실행한다.')

    const itemList = document.querySelector('.item-lists');

    function clickHandler(e) {
      console.log(e.target)
      if (e.target.classList.contains('grid__body')) {
        console.log(e.target.dataset.value);
        router.push({
          name: 'WorkDetailFirstPage',
          params: {
            work_id: e.target.dataset.value
          }
        });
      }
    }


    itemList.addEventListener('click', clickHandler);



  // let check;  
  let input = document.querySelector('.search__input');
  let text = document.querySelector('.search__p');
  let search = document.querySelector('.search__bar');
  let trigger = true;

  input.addEventListener('click', () => {
    if (trigger) {
      search.classList.add('active');

      const check = () => {
        let cur, oldVal;
        cur = input.value;

        if (cur !== oldVal) {
          text.innerHTML = input.value;
        }

        return oldVal = cur;
      };

      setInterval(check, .1);
    }
  });

  input.addEventListener('blur', () => {
    trigger = false;
    input.style.pointerEvents = 'none';

    input.value = '';
    text.innerHTML = '';
    search.classList.add('remove');

    setTimeout(() => {
      search.classList.add('done');

      setTimeout(() => {
        search.classList.remove('active', 'remove');

        setTimeout(() => {
          search.classList.remove('done');
          trigger = true;
          input.style.pointerEvents = 'all';
        }, 100);
      }, 100);
    }, 800);
  });

}

export default {
  init
};