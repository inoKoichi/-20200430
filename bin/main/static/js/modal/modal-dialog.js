$(function() {
  $("#btn_success")
    .unbind()
    .click(function() {
      var $rentalId = $("#rentalId").val();
      // モーダルを閉じる
      $("#exampleModal").modal("hide");
    });
});
