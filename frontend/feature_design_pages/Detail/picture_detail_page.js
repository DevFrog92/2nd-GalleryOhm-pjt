const ROOT = document.documentElement;
const MIN = 10;
const THRESHOLD = innerHeight * (1.2 - 0.225);
const update = e => {
  const scroll = Math.floor(scrollY / innerHeight * 100);
  if (scrollY > THRESHOLD) {
    const progress = Math.floor((scrollY - THRESHOLD) / (document.body.scrollHeight - innerHeight - THRESHOLD) * 100);
    ROOT.style.setProperty('--content', progress);
  }
  ROOT.style.setProperty('--scroll', Math.max(0, Math.min(scroll, 100 - MIN)));
};
window.addEventListener('scroll', update);



//좋아요버튼 js------------------------------------------


var check_status = false;
var like_cnt = document.querySelector("#like-cnt");
var like_parent = document.querySelector(".like-container");

var burst = new mojs.Burst({
  parent: like_parent,
  radius:   { 20: 60 },
  count: 15,
  angle:{0:30},
  children: {
    delay: 250,
    duration: 700,
    radius:{10: 0},
    fill:   [ ' #ffe140' ],
    easing: mojs.easing.bezier(.08,.69,.39,.97)
  }
});

like_cnt.addEventListener("click", () => {
    var t1 = new TimelineLite();
  var t2 = new TimelineLite();
  if(!check_status){
    t1.set(like_cnt, {scale:0});
    t1.set('.like-btn', {scale: 0});
    t1.to(like_cnt, 0.6, {scale:1, background: '#ffe140',ease: Expo.easeOut});
    t2.to('.like-btn', 0.65, {scale: 1, ease: Elastic.easeOut.config(1, 0.3)}, '+=0.2');
//    t1.timeScale(5);
    check_status=true;
    //circleShape.replay();
    burst.replay();
  }
  else{
    t1.to(like_cnt, 1, {scale:1})
      .to(like_cnt, 1, {scale:1, background: '#cdcdcd', ease: Power4.easeOut});
    t1.timeScale(7);
    check_status=false;
  }
  
});