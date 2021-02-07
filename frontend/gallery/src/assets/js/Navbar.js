const init = ()=>{

  var nav = document.getElementById('nav');
  var navlinks = nav.getElementsByTagName('router-link');
  
  function toggleNav() {
      (nav.classList.contains('active')) ? nav.classList.remove('active') : nav.classList.add('active');
    }
  
  document.getElementById('nav-icon').addEventListener('click', function(e) {
      e.preventDefault();
      toggleNav();
  });
  
  for(var i = 0; i < navlinks.length; i++) {
      navlinks[i].addEventListener('click', function() {
        toggleNav();
    });
  }
  }
  
  export default {init};