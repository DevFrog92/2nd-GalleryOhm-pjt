const init = () => {
  

var clickmessage = "그림에는 마우스 오른쪽 버튼이 금지되었어요"

function disableclick(e) {
  if (document.all) {
    if (event.button == 2 || event.button == 3) {
      if (event.srcElement.tagName == "IMG") {
        alert(clickmessage);
        return false;
      }
    }
  } else if (document.layers) {
    if (e.which == 3) {
      alert(clickmessage);
      return false;
    }
  } else if (document.getElementById) {
    if (e.which == 3 && e.target.tagName == "IMG") {
      alert(clickmessage)
      return false
    }
  }
}

(function associateimages() {
  for (let i = 0; i < document.images.length; i++)
    document.images[i].onmousedown = disableclick;
}
)();
}

export default {init};