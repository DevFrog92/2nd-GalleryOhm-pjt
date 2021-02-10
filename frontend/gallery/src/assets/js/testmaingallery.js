const init = () => {
  // var el = document.getElementById("all_gallery_wrapper");
  var imgCon = document.getElementById("image-container");
  var imgCon1 = document.getElementById("image-container1");
  var imgCon2 = document.getElementById("image-container2");
  var imgCon3 = document.getElementById("image-container3");
  var isScrolling = true;
  var pos = 1;
  var checkScrollSpeed = (function(settings) {
    settings = settings || {};

    var lastPos,
      newPos,
      timer,
      delta,
      delay = settings.delay || 50;

    function clear() {
      lastPos = null;
      delta = 0;
    }

    clear();

    return function() {
      newPos = window.scrollY;
      if (lastPos != null) {
        delta = newPos - lastPos;
      }
      lastPos = newPos;
      clearTimeout(timer);
      timer = setTimeout(clear, delay);
      return delta;
    };
  })();
  imgCon1.style.transform = "translate(0px, -3100px)";
  setInterval(() => {
    if (isScrolling || pos === 0 || checkScrollSpeed() === 0) {
      for (var i = 1; i <= 12; i++) {
        document.getElementById("image" + i).style.transform = "skewY(0deg)";
        document.getElementById("image-" + i).style.transform = "skewY(0deg)";
        document.getElementById("image_" + i).style.transform = "skewY(0deg)";
        document.getElementById("image__" + i).style.transform = "skewY(0deg)";
      }
    }
  }, 600);

  window.onscroll = function() {
    isScrolling = false;
    pos = checkScrollSpeed();
    let wpos = window.scrollY;
    let wpos1 = window.scrollY - 3211;
    console.log(window.scrollY);
    imgCon.style.transform = "translate(0px, -" + wpos + "px)";
    imgCon1.style.transform = "translate(0px, " + wpos1 + "px)";
    imgCon2.style.transform = "translate(0px, -" + wpos + "px)";
    imgCon3.style.transform = "translate(0px, " + wpos1 + "px)";
    if (pos > 45) {
      pos = 45;
    } else if (pos < -45) {
      pos = -45;
    }
    for (var i = 1; i <= 12; i++) {
      document.getElementById("image" + i).style.transform =
        "skewY(" + pos + "deg)";
      document.getElementById("image-" + i).style.transform =
        "skewY(" + -pos + "deg)";
        document.getElementById("image_" + i).style.transform =
        "skewY(" + pos + "deg)";
        document.getElementById("image__" + i).style.transform =
          "skewY(" + -pos + "deg)";
      if (pos === 1 || pos === -1 || wpos === 0 || wpos === 1475) {
        document.getElementById("image" + i).style.transform = "skewY(0deg)";
        document.getElementById("image-" + i).style.transform = "skewY(0deg)";
        document.getElementById("image_" + i).style.transform = "skewY(0deg)";
        document.getElementById("image__" + i).style.transform = "skewY(0deg)";
      }
    }
  };
}

export default {init};