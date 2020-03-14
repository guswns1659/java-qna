$(".answer-write input[type='submit']").on('click', addAnswer);

function addAnswer(e) {
  e.preventDefault();
  console.log("click me");

  var queryString = $(".answer-write").serialize();
  console.log("queryString : " + queryString);

  var url = $(".answer-write").attr("action");
  console.log("url : " + url);

  $.ajax({
    type : 'post',
    url : url,
    data : queryString,
    dataType : 'json',
    error : onError,
    success : onSuccess
  })
  
  function onError() {
    
  }
  
  function onSuccess(data, status) {
    console.log(data);
    var answerTemplate = $("#answerTemplate").html();
    var template = Handlebars.compile(answerTemplate);
    // var template = answerTemplatetemplate.format(data.writer.name, data.formattedCreatedDate,
    //     data.contents, data.id, data.id);
    var html = template(data);
    // 여기가 문제!
    $(".qna-comment-slipp-articles").prepend(html);
  }
}


String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};
