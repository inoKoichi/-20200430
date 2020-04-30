// jquery
document.write(
  '<script type="text/javascript" src="/js/jquery/jquery-3.3.1.min.js"></script>'
);
// jquery ui
document.write(
  '<script type="text/javascript" src="/js/jquery/jquery-ui.min.js"></script>'
);
// bootstrap
document.write(
  '<script type="text/javascript" src="/js/bootstrap.min.js"></script>'
);

function isSp() {
  var ua = navigator.userAgent;
  if (
    ua.indexOf("iPhone") > 0 ||
    (ua.indexOf("Android") > 0 && ua.indexOf("Mobile") > 0)
  ) {
    return true;
  } else if (ua.indexOf("iPad") > 0 || ua.indexOf("Android") > 0) {
    return false;
  } else {
    return false;
  }
}
