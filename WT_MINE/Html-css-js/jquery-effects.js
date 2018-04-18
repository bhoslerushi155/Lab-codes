$(document).ready(function(){
    $("#first-element").focus();
    $("#signup").hide();
    
    $("#res-button").click(function(){
        $("#signup").slideUp();
        $("#res-form").slideDown();
       
    });
     $("#sign-button").click(function(){
        $("#res-form").slideUp();
        $("#signup").slideDown();
    });
    
    $("form:first").focusin(function(){
        
    });
    
    $("#reset-button").click(function(){
        $(".clear").val("");
    });
    
   
    
});