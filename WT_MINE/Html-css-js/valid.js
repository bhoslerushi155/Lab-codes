function validate(){
    var name=document.form.name.value;
    var email=document.form.email.value;
    var number=document.form.mob.value;
    var web=document.form.website.value;
   
    var nameReg=/^[a-zA-Z]+$/;
    var mobReg=/^[0-9]{10}$/;
    var emailReg=/^[a-zA-Z]+[@][a-zA-Z]{2,5}[.][a-zA-Z]{2,5}$/;
    var webReg=/^[a-zA-Z]+[.][a-zA-Z]{2,5}$/;
    
    if(!name.match(nameReg)){
       document.form.name.focus();
       alert("name field is not correct");  
       return false;
    }
    if(!email.match(emailReg)){
       document.form.email.focus();
       alert("email field is not correct");  
       return false;
    }
    if(!number.match(mobReg)){
       document.form.mob.focus();
       alert("number field is not correct");  
       return false;
    }
    if(!web.match(webReg)){
       document.form.website.focus();
       alert("website field is not correct");  
       return false;
    }
    
    return ture;
}