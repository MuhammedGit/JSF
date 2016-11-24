
$(document).ready(function(){
    $(".containerBox").click(function(){
    	var id=$(this).attr('id');
    	var idcontent= id.concat("content");
        $('#'+idcontent).toggle();
    
    });
  
});
function someFunction(element) {
	  
}