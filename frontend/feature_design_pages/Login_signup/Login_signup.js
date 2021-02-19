const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const signUpContainer = document.getElementById('singup_container');

signUpButton.addEventListener('click', () => {
  signUpContainer.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
  signUpContainer.classList.remove("right-panel-active");
});


var modal = document.querySelector(".find_modal");
var trigger = document.querySelector(".trigger");
var closeButton = document.querySelector(".close_button");
var cancelButton = document.querySelector(".find_cancel");

trigger.addEventListener("click", toggleModal);
closeButton.addEventListener("click", toggleModal);
cancelButton.addEventListener("click", toggleModal);
window.addEventListener("click", windowOnClick);

function toggleModal(event) {
  event.preventDefault();
  modal.classList.toggle("show-modal");
}

function windowOnClick(event) {
  if (event.target === modal) {
    toggleModal();
  }
}


var modalPw = document.querySelector(".find_modal_pw");
var triggerPw = document.querySelector(".trigger_pw");
var closeButtonPw = document.querySelector(".close_button_pw");
var cancelButtonPw = document.querySelector(".cancel_pw");


triggerPw.addEventListener("click", toggleModalPw);
closeButtonPw.addEventListener("click", toggleModalPw);
cancelButtonPw.addEventListener("click", toggleModalPw);
window.addEventListener("click", windowOnClickPw);

function toggleModalPw(event) {
  event.preventDefault();
  modalPw.classList.toggle("show_modal_pw");
}

function windowOnClickPw(event) {
  if (event.target === modalPw) {
    toggleModalPw();
  }
}

//terms모달 js------------------------------------------------

var termsModal = document.querySelector(".terms_modal");
var triggerTerms = document.querySelector(".triggerTerms");
var closeButtonTerms = document.querySelector(".close_button_terms");


triggerTerms.addEventListener("click", toggleModalTerms);
closeButtonTerms.addEventListener("click", toggleModalTerms);
window.addEventListener("click", windowOnClickTerms);

function toggleModalTerms(event) {
  event.preventDefault();
  termsModal.classList.toggle("show_modal_terms");
}

function windowOnClickTerms(event) {
  if (event.target === termsModal) {
    toggleModalTerms();
  }
}